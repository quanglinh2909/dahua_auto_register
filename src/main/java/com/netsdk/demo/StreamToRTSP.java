package com.netsdk.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.netsdk.demo.module.LoginModule;
import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.NetSDKLib.LLong;
import com.netsdk.lib.ToolKits;

import com.sun.jna.Pointer;

/**
 * StreamToRTSP - PUSH MODE (FFmpeg → MediaMTX)
 * 
 * Changed from listen mode to push mode
 * FFmpeg actively connects to MediaMTX instead of waiting
 */
public class StreamToRTSP {
    
    private static Map<String, StreamInfo> activeStreams = new ConcurrentHashMap<>();
    
    private static String mediaMtxHost = "localhost";
    private static int rtspPort = 8554;
    private static String streamNamePrefix = "";
    private static String streamNameSuffix = "";
    
    private static final int MAX_RESTART_ATTEMPTS = 10;
    private static final int RESTART_DELAY_MS = 2000;
    
    private static final int QUEUE_SIZE = 500;
    private static final int QUEUE_PREBUFFER_TARGET = 100;
    private static final int QUEUE_LOW_WATERMARK = 50;
    private static final int QUEUE_HIGH_WATERMARK = 450;
    
    // Load .env file on class init
    static {
        loadEnv();
    }
    
    /**
     * Load configuration from .env file
     */
    private static void loadEnv() {
        // Try multiple locations for .env
        java.nio.file.Path cwd = java.nio.file.Paths.get(System.getProperty("user.dir"));
        java.nio.file.Path projectRoot = cwd.getParent();
        if (projectRoot == null) projectRoot = cwd;
        
        java.nio.file.Path[] candidates = {
            projectRoot.resolve(".env"),
            cwd.resolve(".env")
        };
        
        java.nio.file.Path envFile = null;
        for (java.nio.file.Path p : candidates) {
            if (java.nio.file.Files.exists(p)) {
                envFile = p;
                break;
            }
        }
        
        if (envFile == null) {
            System.out.println("[StreamToRTSP] No .env file found, using defaults");
            return;
        }
        
        try (java.io.BufferedReader reader = java.nio.file.Files.newBufferedReader(envFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                
                int eqIdx = line.indexOf('=');
                if (eqIdx <= 0) continue;
                
                String key = line.substring(0, eqIdx).trim();
                String value = line.substring(eqIdx + 1).trim();
                
                switch (key) {
                    case "MEDIA_MTX_HOST":
                        mediaMtxHost = value;
                        break;
                    case "RTSP_PORT":
                        try { rtspPort = Integer.parseInt(value); } catch (NumberFormatException e) {}
                        break;
                    case "STREAM_NAME_PREFIX":
                        streamNamePrefix = value;
                        break;
                    case "STREAM_NAME_SUFFIX":
                        streamNameSuffix = value;
                        break;
                }
            }
            System.out.printf("[StreamToRTSP] Loaded .env: host=%s, port=%d, prefix=%s, suffix=%s%n",
                mediaMtxHost, rtspPort, streamNamePrefix, streamNameSuffix);
        } catch (Exception e) {
            System.err.println("[StreamToRTSP] Error reading .env: " + e.getMessage());
        }
    }
    
    public static class StreamInfo {
        public String deviceId;
        public String streamName;
        public LLong loginHandle;
        public LLong realHandle;
        public Process ffmpegProcess;
        public OutputStream ffmpegInput;
        public String rtspUrl;
        public volatile boolean isRunning;
        public volatile boolean needRestart;
        public long bytesWritten;
        public long startTime;
        public volatile long lastDataTime;
        public RealDataCallback callback;
        public Thread mainThread;
        public Thread writerThread;
        public LinkedBlockingQueue<byte[]> dataQueue;
        public int restartCount;
        public int channel;
        public AtomicLong packetsReceived;
        public AtomicLong packetsDropped;
        public volatile int maxQueueDepth;
        public volatile int minQueueDepth;
        
        // Keyframe tracking
        public AtomicLong keyframesReceived;
        public volatile long lastKeyframeTime;
        public volatile boolean hasReceivedKeyframe;
        public volatile long recordingStartTime;
        
        // Performance
        public AtomicLong totalBytesReceived;
        public volatile long lastBitrateCheck;
        public volatile double currentBitrateMbps;
        
        // Flow control
        public volatile long packetsWrittenTotal;
        
        public StreamInfo(String deviceId, LLong loginHandle) {
            this.deviceId = deviceId;
            this.loginHandle = loginHandle;
            this.realHandle = new LLong(0);
            this.isRunning = false;
            this.needRestart = false;
            this.bytesWritten = 0;
            this.startTime = System.currentTimeMillis();
            this.lastDataTime = System.currentTimeMillis();
            this.dataQueue = new LinkedBlockingQueue<>(QUEUE_SIZE);
            this.restartCount = 0;
            this.channel = 0;
            this.packetsReceived = new AtomicLong(0);
            this.packetsDropped = new AtomicLong(0);
            this.maxQueueDepth = 0;
            this.minQueueDepth = QUEUE_SIZE;
            this.totalBytesReceived = new AtomicLong(0);
            this.lastBitrateCheck = System.currentTimeMillis();
            this.currentBitrateMbps = 0.0;
            this.keyframesReceived = new AtomicLong(0);
            this.lastKeyframeTime = System.currentTimeMillis();
            this.hasReceivedKeyframe = false;
            this.recordingStartTime = 0;
            this.packetsWrittenTotal = 0;
        }
    }
    
    public static String startStream(String deviceId, LLong loginHandle, int channel) {
        if (activeStreams.containsKey(deviceId)) {
            StreamInfo existing = activeStreams.get(deviceId);
            if (existing.isRunning) {
                System.out.println("[StreamToRTSP] Stream already running: " + deviceId);
                return existing.rtspUrl;
            } else {
                System.out.println("[StreamToRTSP] Cleaning up stopped stream...");
                stopStream(deviceId);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        }
        
        StreamInfo streamInfo = new StreamInfo(deviceId, loginHandle);
        String streamName = streamNamePrefix + deviceId + streamNameSuffix;
        streamInfo.streamName = streamName;
        streamInfo.channel = channel;
        streamInfo.rtspUrl = String.format("rtsp://%s:%d/%s", mediaMtxHost, rtspPort, streamName);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("[StreamToRTSP] PUSH MODE - FFmpeg connects to MediaMTX");
        System.out.println("  RTSP URL: " + streamInfo.rtspUrl);
        System.out.println("  Queue: " + QUEUE_SIZE + " packets (~17s)");
        System.out.println("  Prebuffer: " + QUEUE_PREBUFFER_TARGET + " packets (~3s)");
        System.out.println("=".repeat(60));
        
        streamInfo.isRunning = true;
        streamInfo.recordingStartTime = System.currentTimeMillis();
        activeStreams.put(deviceId, streamInfo);
        
        streamInfo.mainThread = new Thread(() -> {
            while (streamInfo.isRunning && streamInfo.restartCount < MAX_RESTART_ATTEMPTS) {
                try {
                    boolean success = runStreamSession(streamInfo);
                    
                    if (!streamInfo.isRunning) break;
                    
                    if (!success || streamInfo.needRestart) {
                        streamInfo.restartCount++;
                        streamInfo.needRestart = false;
                        
                        System.out.printf("[StreamToRTSP] 🔄 Restart %d/%d...%n",
                            streamInfo.restartCount, MAX_RESTART_ATTEMPTS);
                        
                        Thread.sleep(RESTART_DELAY_MS);
                    }
                    
                } catch (InterruptedException e) {
                    break;
                } catch (Exception e) {
                    System.err.println("[StreamToRTSP] Error: " + e.getMessage());
                    streamInfo.restartCount++;
                    try { Thread.sleep(RESTART_DELAY_MS); } catch (InterruptedException ie) { break; }
                }
            }
        }, "StreamMain-" + deviceId);
        streamInfo.mainThread.setDaemon(true);
        streamInfo.mainThread.start();
        
        startMonitorThread(streamInfo);
        
        return streamInfo.rtspUrl;
    }
    
    private static boolean runStreamSession(StreamInfo streamInfo) {
        try {
            streamInfo.dataQueue.clear();
            streamInfo.hasReceivedKeyframe = false;
            
            System.out.println("[StreamToRTSP] Starting realplay...");
            streamInfo.realHandle = LoginModule.netsdk.CLIENT_RealPlayEx(
                streamInfo.loginHandle, streamInfo.channel, null, 0
            );
            
            if (streamInfo.realHandle.longValue() == 0) {
                System.err.println("[StreamToRTSP] Realplay failed! " + ToolKits.getErrorCodePrint());
                return false;
            }
            
            streamInfo.callback = new RealDataCallback(streamInfo);
            if (!LoginModule.netsdk.CLIENT_SetRealDataCallBackEx(
                    streamInfo.realHandle, streamInfo.callback, null, 1)) {
                System.err.println("[StreamToRTSP] Callback failed!");
                stopRealplay(streamInfo);
                return false;
            }
            
            // Wait for keyframe
            System.out.println("[StreamToRTSP] Waiting for keyframe...");
            int attempts = 0;
            while (!streamInfo.hasReceivedKeyframe && attempts < 60) {
                Thread.sleep(500);
                attempts++;
            }
            
            if (!streamInfo.hasReceivedKeyframe) {
                System.err.println("[StreamToRTSP] No keyframe!");
                stopRealplay(streamInfo);
                return false;
            }
            
            // Prebuffer
            System.out.printf("[StreamToRTSP] ✓ Keyframe! Prebuffering to %d...%n", 
                QUEUE_PREBUFFER_TARGET);
            
            attempts = 0;
            while (streamInfo.dataQueue.size() < QUEUE_PREBUFFER_TARGET && attempts < 60) {
                int queueSize = streamInfo.dataQueue.size();
                if (attempts % 4 == 0) {
                    System.out.printf("[StreamToRTSP] Prebuffering: %d/%d...%n", 
                        queueSize, QUEUE_PREBUFFER_TARGET);
                }
                Thread.sleep(500);
                attempts++;
            }
            
            int finalQueueSize = streamInfo.dataQueue.size();
            System.out.printf("[StreamToRTSP] ✓ Prebuffered %d packets%n", finalQueueSize);
            
            if (finalQueueSize < 20) {
                System.err.println("[StreamToRTSP] Insufficient prebuffer!");
                stopRealplay(streamInfo);
                return false;
            }
            
            // Start FFmpeg - PUSH MODE (connect TO MediaMTX)
            if (!startFFmpegProcess(streamInfo)) {
                System.err.println("[StreamToRTSP] FFmpeg failed!");
                stopRealplay(streamInfo);
                return false;
            }
            
            System.out.println("[StreamToRTSP] ✓ Session started! Writing data...");
            
            streamInfo.restartCount = 0;
            
            streamInfo.writerThread = new Thread(() -> {
                runWriterLoopWithFlowControl(streamInfo);
            }, "StreamWriter-" + streamInfo.deviceId);
            streamInfo.writerThread.setDaemon(true);
            streamInfo.writerThread.start();
            
            try {
                streamInfo.writerThread.join();
            } catch (InterruptedException e) {}
            
            closeSessionGracefully(streamInfo);
            
            return !streamInfo.needRestart && streamInfo.isRunning;
            
        } catch (Exception e) {
            System.err.println("[StreamToRTSP] Session error: " + e.getMessage());
            closeSessionGracefully(streamInfo);
            return false;
        }
    }
    
    private static void runWriterLoopWithFlowControl(StreamInfo streamInfo) {
        int consecutiveErrors = 0;
        long packetsWritten = 0;
        long lastFlushTime = System.currentTimeMillis();
        int packetsSinceFlush = 0;
        
        final int FLUSH_PACKET_INTERVAL = 30;
        final int FLUSH_TIME_INTERVAL_MS = 1000;
        
        System.out.println("[StreamToRTSP] Writer loop started with pacing");
        
        while (streamInfo.isRunning && !streamInfo.needRestart
               && streamInfo.ffmpegProcess != null 
               && streamInfo.ffmpegProcess.isAlive()) {
            
            try {
                int queueSize = streamInfo.dataQueue.size();
                
                // Adaptive polling
                int pollTimeoutMs;
                if (queueSize < QUEUE_LOW_WATERMARK) {
                    pollTimeoutMs = 100;
                    if (queueSize < 10) {
                        pollTimeoutMs = 200;
                    }
                } else if (queueSize > QUEUE_HIGH_WATERMARK) {
                    pollTimeoutMs = 1;
                } else {
                    pollTimeoutMs = 20;
                }
                
                byte[] data = streamInfo.dataQueue.poll(pollTimeoutMs, TimeUnit.MILLISECONDS);
                
                if (data != null) {
                    streamInfo.ffmpegInput.write(data);
                    streamInfo.bytesWritten += data.length;
                    packetsWritten++;
                    streamInfo.packetsWrittenTotal++;
                    packetsSinceFlush++;
                    consecutiveErrors = 0;
                    
                    // Pacing: Add small delay when queue is very low to allow buffer recovery
                    // Reduced delays to improve data flow continuity
                    if (queueSize < QUEUE_LOW_WATERMARK) {
                        // Queue is low - small delay to let buffer recover
                        Thread.sleep(5);   // 5ms delay (was 15ms)
                    } else if (queueSize < 100) {
                        // Queue getting low - minimal delay
                        Thread.sleep(1);   // 1ms delay (was 5ms)
                    }
                    // else: no delay when queue is healthy
                    
                    long now = System.currentTimeMillis();
                    
                    boolean shouldFlush = packetsSinceFlush >= FLUSH_PACKET_INTERVAL
                        || (now - lastFlushTime) >= FLUSH_TIME_INTERVAL_MS
                        || queueSize < QUEUE_LOW_WATERMARK;
                    
                    if (shouldFlush) {
                        streamInfo.ffmpegInput.flush();
                        lastFlushTime = now;
                        packetsSinceFlush = 0;
                    }
                    
                } else {
                    if (packetsSinceFlush > 0) {
                        streamInfo.ffmpegInput.flush();
                        packetsSinceFlush = 0;
                        lastFlushTime = System.currentTimeMillis();
                    }
                }
                
            } catch (IOException e) {
                consecutiveErrors++;
                System.err.printf("[StreamToRTSP] Write error #%d: %s%n", consecutiveErrors, e.getMessage());
                
                if (consecutiveErrors > 3) {
                    System.err.println("[StreamToRTSP] Multiple errors, restarting...");
                    streamInfo.needRestart = true;
                    break;
                }
                
                try { Thread.sleep(100); } catch (InterruptedException ie) { break; }
                
            } catch (InterruptedException e) {
                break;
            }
        }
        
        try {
            if (streamInfo.ffmpegInput != null && packetsSinceFlush > 0) {
                streamInfo.ffmpegInput.flush();
            }
        } catch (IOException e) {}
        
        System.out.printf("[StreamToRTSP] Writer ended: %d packets%n", packetsWritten);
    }
    
    private static void closeSessionGracefully(StreamInfo streamInfo) {
        stopRealplay(streamInfo);
        
        int remaining = streamInfo.dataQueue.size();
        if (remaining > 0 && remaining < 200) {
            try { Thread.sleep(Math.min(remaining * 10, 3000)); } catch (InterruptedException e) {}
        }
        
        if (streamInfo.ffmpegInput != null) {
            try {
                streamInfo.ffmpegInput.flush();
                Thread.sleep(500);
                streamInfo.ffmpegInput.close();
            } catch (Exception e) {}
            streamInfo.ffmpegInput = null;
        }
        
        stopFFmpeg(streamInfo);
    }
    
    private static void stopRealplay(StreamInfo streamInfo) {
        if (streamInfo.realHandle.longValue() != 0) {
            LoginModule.netsdk.CLIENT_StopRealPlayEx(streamInfo.realHandle);
            streamInfo.realHandle.setValue(0);
        }
    }
    
    private static void stopFFmpeg(StreamInfo streamInfo) {
        if (streamInfo.ffmpegProcess != null) {
            try {
                boolean finished = streamInfo.ffmpegProcess.waitFor(3, TimeUnit.SECONDS);
                if (!finished) {
                    streamInfo.ffmpegProcess.destroyForcibly();
                }
            } catch (InterruptedException e) {
                streamInfo.ffmpegProcess.destroyForcibly();
            }
            streamInfo.ffmpegProcess = null;
        }
    }
    
    private static void startMonitorThread(StreamInfo streamInfo) {
        Thread monitorThread = new Thread(() -> {
            while (streamInfo.isRunning) {
                try {
                    Thread.sleep(5000);
                    
                    if (streamInfo.isRunning) {
                        long now = System.currentTimeMillis();
                        long durationSec = (now - streamInfo.recordingStartTime) / 1000;
                        double mbWritten = streamInfo.bytesWritten / (1024.0 * 1024.0);
                        long timeSinceData = now - streamInfo.lastDataTime;
                        int queueSize = streamInfo.dataQueue.size();
                        
                        if (queueSize > streamInfo.maxQueueDepth) {
                            streamInfo.maxQueueDepth = queueSize;
                        }
                        if (queueSize < streamInfo.minQueueDepth) {
                            streamInfo.minQueueDepth = queueSize;
                        }
                        
                        // Bitrate
                        if ((now - streamInfo.lastBitrateCheck) >= 5000) {
                            long bytesReceived = streamInfo.totalBytesReceived.get();
                            long timeDelta = now - streamInfo.lastBitrateCheck;
                            
                            if (timeDelta > 0) {
                                streamInfo.currentBitrateMbps = (bytesReceived * 8.0) / (timeDelta * 1000.0);
                                streamInfo.totalBytesReceived.set(0);
                                streamInfo.lastBitrateCheck = now;
                            }
                        }
                        
                        long packetsRx = streamInfo.packetsReceived.get();
                        long packetsDropped = streamInfo.packetsDropped.get();
                        long keyframes = streamInfo.keyframesReceived.get();
                        double dropRate = packetsRx > 0 ? (packetsDropped * 100.0 / packetsRx) : 0.0;
                        
                        if (timeSinceData > 15000) {
                            System.err.printf("[StreamToRTSP] ❌ NO DATA %.1fs!%n", timeSinceData / 1000.0);
                            streamInfo.needRestart = true;
                        } else {
                            String health = getHealthIndicator(queueSize, dropRate);
                            
                            System.out.printf("[StreamToRTSP] %s [%02d:%02d:%02d] %.2fMB %.2fMbps Q:%d/%d%s(min:%d,max:%d) I:%d Drop:%d W:%d R:%d%n", 
                                streamInfo.deviceId,
                                durationSec / 3600,
                                (durationSec % 3600) / 60,
                                durationSec % 60,
                                mbWritten,
                                streamInfo.currentBitrateMbps,
                                queueSize, 
                                QUEUE_SIZE,
                                health,
                                streamInfo.minQueueDepth,
                                streamInfo.maxQueueDepth,
                                keyframes,
                                packetsDropped,
                                streamInfo.packetsWrittenTotal,
                                streamInfo.restartCount);
                            
                            streamInfo.minQueueDepth = queueSize;
                        }
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
        }, "Monitor-" + streamInfo.deviceId);
        monitorThread.setDaemon(true);
        monitorThread.start();
    }
    
    private static String getHealthIndicator(int queueSize, double dropRate) {
        if (dropRate > 5.0 || queueSize > QUEUE_HIGH_WATERMARK) return "🔴";
        if (dropRate > 1.0 || queueSize > 350) return "🟡";
        if (queueSize < QUEUE_LOW_WATERMARK) return "🟠";
        return "🟢";
    }
    
    private static boolean startFFmpegProcess(StreamInfo streamInfo) {
        try {
            // ============================================================
            // PUSH MODE: FFmpeg connects TO MediaMTX (not listen mode)
            // OPTIMIZED: Better timestamp handling for recording
            // ============================================================
            ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg",
                "-hide_banner",
                "-loglevel", "warning",
                
                // INPUT - OPTIMIZED FOR TIMESTAMP ISSUES
                "-use_wallclock_as_timestamps", "1",     // Use wall clock instead of original pts
                "-fflags", "+genpts+discardcorrupt",     // Generate PTS + discard corrupt packets
                "-probesize", "10000000",                // Larger probe size for better detection
                "-analyzeduration", "10000000",          // Longer analyze duration
                
                "-f", "dhav",
                "-i", "pipe:0",
                
                // VIDEO - copy with extras
                "-c:v", "copy",
                "-bsf:v", "dump_extra",
                "-reset_timestamps", "1",                // Reset timestamps from 0
                
                // TIMESTAMP - Relaxed settings for recording
                "-avoid_negative_ts", "make_zero",
                
                // MUXER - Relaxed settings for better tolerance
                "-max_interleave_delta", "500000",       // 500ms max gap allowed
                "-max_muxing_queue_size", "4096",        // Larger mux queue
                
                // NO AUDIO
                "-an",
                
                // RTSP OUTPUT - PUSH MODE with larger buffer
                "-f", "rtsp",
                "-rtsp_transport", "tcp",
                "-buffer_size", "4096000",               // 4MB buffer
                
                // MediaMTX URL
                streamInfo.rtspUrl
            );
            
            pb.redirectErrorStream(true);
            
            System.out.println("[StreamToRTSP] Starting FFmpeg (push mode)...");
            System.out.println("[StreamToRTSP] Connecting to: " + streamInfo.rtspUrl);
            
            streamInfo.ffmpegProcess = pb.start();
            streamInfo.ffmpegInput = new java.io.BufferedOutputStream(
                streamInfo.ffmpegProcess.getOutputStream(), 262144);
            
            // Monitor FFmpeg output
            Thread outputReader = new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(
                        new InputStreamReader(streamInfo.ffmpegProcess.getInputStream()));
                    String line;
                    
                    while ((line = reader.readLine()) != null) {
                        // Filter spam
                        if (line.contains("Unknown type: B8") || 
                            line.contains("Last message repeated")) {
                            continue;
                        }
                        
                        // Log important messages
                        if (line.contains("Stream #") || 
                            line.contains("Opening") ||
                            line.contains("error") ||
                            line.contains("warning")) {
                            System.out.println("[FFmpeg] " + line);
                        }
                        
                        // Critical errors
                        if (line.contains("Conversion failed") ||
                            line.contains("Could not write header") ||
                            line.contains("Connection refused") ||
                            line.contains("Could not find codec")) {
                            System.err.println("[FFmpeg] CRITICAL: " + line);
                            streamInfo.needRestart = true;
                        }
                    }
                } catch (IOException e) {}
            }, "FFmpegOut-" + streamInfo.deviceId);
            outputReader.setDaemon(true);
            outputReader.start();
            
            // Wait for FFmpeg to connect
            Thread.sleep(2000);
            
            if (!streamInfo.ffmpegProcess.isAlive()) {
                System.err.println("[StreamToRTSP] FFmpeg died during startup!");
                System.err.println("[StreamToRTSP] Check if MediaMTX is running on " + mediaMtxHost + ":" + rtspPort);
                return false;
            }
            
            System.out.println("[StreamToRTSP] ✓ FFmpeg connected to MediaMTX!");
            return true;
            
        } catch (Exception e) {
            System.err.println("[StreamToRTSP] FFmpeg error: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean stopStream(String deviceId) {
        StreamInfo streamInfo = activeStreams.get(deviceId);
        if (streamInfo == null) return false;
        
        System.out.println("[StreamToRTSP] Stopping: " + deviceId);
        streamInfo.isRunning = false;
        streamInfo.needRestart = false;
        
        closeSessionGracefully(streamInfo);
        streamInfo.dataQueue.clear();
        
        if (streamInfo.writerThread != null && streamInfo.writerThread.isAlive()) {
            try { streamInfo.writerThread.join(3000); } catch (InterruptedException e) {}
        }
        
        if (streamInfo.mainThread != null && streamInfo.mainThread.isAlive()) {
            streamInfo.mainThread.interrupt();
            try { streamInfo.mainThread.join(5000); } catch (InterruptedException e) {}
        }
        
        activeStreams.remove(deviceId);
        
        long durationSec = (System.currentTimeMillis() - streamInfo.recordingStartTime) / 1000;
        System.out.printf("[StreamToRTSP] ✓ Stopped [%02d:%02d:%02d] Written:%d Keyframes:%d%n", 
            durationSec / 3600, (durationSec % 3600) / 60, durationSec % 60,
            streamInfo.packetsWrittenTotal,
            streamInfo.keyframesReceived.get());
        
        return true;
    }
    
    public static Map<String, StreamInfo> getActiveStreams() {
        return activeStreams;
    }
    
    public static void stopAllStreams() {
        for (String deviceId : activeStreams.keySet()) {
            stopStream(deviceId);
        }
    }
    
    public static void setMediaMtxHost(String host) {
        mediaMtxHost = host;
    }
    
    private static class RealDataCallback implements NetSDKLib.fRealDataCallBackEx {
        private StreamInfo streamInfo;
        private long lastWarningTime = 0;
        
        public RealDataCallback(StreamInfo streamInfo) {
            this.streamInfo = streamInfo;
        }
        
        @Override
        public void invoke(LLong lRealHandle, int dwDataType, Pointer pBuffer, 
                          int dwBufSize, int param, Pointer dwUser) {
            
            if (!streamInfo.isRunning) return;
            
            if (dwDataType == 0 && dwBufSize > 0) {
                try {
                    byte[] data = pBuffer.getByteArray(0, dwBufSize);
                    
                    // Keyframe detection
                    if (dwBufSize > 20000) {
                        streamInfo.keyframesReceived.incrementAndGet();
                        streamInfo.lastKeyframeTime = System.currentTimeMillis();
                        streamInfo.hasReceivedKeyframe = true;
                    }
                    
                    streamInfo.lastDataTime = System.currentTimeMillis();
                    streamInfo.packetsReceived.incrementAndGet();
                    streamInfo.totalBytesReceived.addAndGet(dwBufSize);
                    
                    if (!streamInfo.dataQueue.offer(data)) {
                        streamInfo.packetsDropped.incrementAndGet();
                        
                        long now = System.currentTimeMillis();
                        if ((now - lastWarningTime) > 5000) {
                            System.err.printf("[StreamToRTSP] ⚠️  Queue full! Dropped:%d%n", 
                                streamInfo.packetsDropped.get());
                            lastWarningTime = now;
                        }
                    }
                    
                } catch (Exception e) {}
            }
        }
    }
}