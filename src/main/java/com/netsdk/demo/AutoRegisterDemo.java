package com.netsdk.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.netsdk.demo.module.AutoRegisterModule;
import com.netsdk.demo.module.LoginModule;
import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.NetSDKLib.LLong;
import com.netsdk.lib.ToolKits;

import com.sun.jna.Pointer;

/**
 * Auto Register Demo - Chức năng đăng ký chủ động
 * 
 * Mô tả: Demo này khởi động một server lắng nghe, chờ các thiết bị (camera) 
 * chủ động kết nối đến và đăng nhập vào hệ thống.
 * 
 * Cách chạy: ./run.sh com.netsdk.demo.AutoRegisterDemo
 */
public class AutoRegisterDemo {
    
    // ============================================================
    // CLASS LƯU THÔNG TIN CAMERA
    // ============================================================
    public static class CameraCredentials {
        public String deviceId;
        public String username;
        public String password;
        public String description;
        
        public CameraCredentials(String deviceId, String username, String password, String description) {
            this.deviceId = deviceId;
            this.username = username;
            this.password = password;
            this.description = description;
        }
        
        public CameraCredentials(String deviceId, String username, String password) {
            this(deviceId, username, password, "");
        }
    }
    
    // ============================================================
    // DANH SÁCH CAMERA - THÊM CÁC CAMERA CỦA BẠN VÀO ĐÂY
    // ============================================================
    // Key: Device ID hoặc IP (để tra cứu khi camera kết nối)
    private static Map<String, CameraCredentials> cameraList = new HashMap<>();
    
    static {
        // *** THÊM CÁC CAMERA CỦA BẠN VÀO ĐÂY ***
        // Format: addCamera(deviceId/IP, username, password, description)
        
        // addCamera("camera01", "admin", "Oryza123", "Camera cổng chính");
        // addCamera("cameraksp", "admin", "Oryza@123", "Camera sảnh");
        // addCamera("9D0B457PAG925CA", "admin", "Oryza123", "Camera theo IP");
        
        // Thêm camera khác...
        // addCamera("camera03", "admin", "pass123", "Camera bãi xe");
    }
    
    private static void addCamera(String key, String username, String password, String description) {
        cameraList.put(key, new CameraCredentials(key, username, password, description));
    }
    
    private static void addCamera(String key, String username, String password) {
        cameraList.put(key, new CameraCredentials(key, username, password));
    }
    
    // Lưu trữ các thiết bị đã kết nối: deviceId -> loginHandle
    
    // Lưu trữ các thiết bị đã kết nối: deviceId -> loginHandle
    private static Map<String, LLong> connectedDevices = new HashMap<>();
    
    // Server settings
    private static String serverIP = "0.0.0.0";  // Lắng nghe tất cả interface
    private static int serverPort = 9500;         // Port lắng nghe camera
    private static int apiPort = 8087;            // Port cho REST API
    private static CameraApiServer apiServer;
    
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("      DAHUA NET SDK - AUTO REGISTER DEMO");
        System.out.println("=".repeat(60));
        
        // Đọc tham số từ command line nếu có
        if (args.length >= 1) {
            serverIP = args[0];
        }
        if (args.length >= 2) {
            serverPort = Integer.parseInt(args[1]);
        }
        if (args.length >= 3) {
            apiPort = Integer.parseInt(args[2]);
        }
        
        // 1. Khởi tạo Database
        System.out.println("\n[1] Initializing Database...");
        try {
            CameraDatabase.init();
            int cameraCount = CameraDatabase.getAllCameras().size();
            System.out.println("    Database ready. Cameras in DB: " + cameraCount);
        } catch (Exception e) {
            System.err.println("    Database error: " + e.getMessage());
            System.out.println("    WARNING: Using in-memory camera list only.");
        }
        
        // 2. Khởi tạo SDK
        System.out.println("\n[2] Initializing SDK...");
        if (!LoginModule.init(DisConnectCallback.getInstance(), ReConnectCallback.getInstance())) {
            System.err.println("Failed to initialize SDK!");
            return;
        }
        System.out.println("    SDK initialized successfully.");
        
        // 3. Khởi động REST API Server
        System.out.println("\n[3] Starting REST API Server...");
        try {
            apiServer = new CameraApiServer(apiPort);
            apiServer.start();
        } catch (Exception e) {
            System.err.println("    API Server error: " + e.getMessage());
        }
        
        // 4. Khởi động server lắng nghe camera
        System.out.println("\n[4] Starting camera listen server...");
        System.out.printf("    Server IP: %s, Port: %d%n", serverIP, serverPort);
        
        if (!AutoRegisterModule.startServer(serverIP, serverPort, ServiceCallback.getInstance())) {
            System.err.println("Failed to start server!");
            LoginModule.cleanup();
            return;
        }
        System.out.println("    Server started! Waiting for devices to connect...");
        
        // 5. Chờ thiết bị kết nối và xử lý lệnh từ console
        System.out.println("\n[5] Commands:");
        System.out.println("    'list'           - List connected devices");
        System.out.println("    'p2p <SN>'       - Connect camera via P2P (lookup DB)");
        System.out.println("    'p2p <SN> <u> <p>' - Connect P2P with credentials");
        System.out.println("    'stream <id>'    - Start RTSP stream for device");
        System.out.println("    'stop <id>'      - Stop RTSP stream for device");
        System.out.println("    'streams'        - List active RTSP streams");
        System.out.println("    'cameras'        - List cameras in database");
        System.out.println("    'quit'           - Stop server and exit");
        System.out.println("-".repeat(60));
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.print("\n> ");
            String commandLine = scanner.nextLine().trim();
            String[] parts = commandLine.split("\\s+", 2);
            String command = parts[0].toLowerCase();
            String arg = parts.length > 1 ? parts[1] : "";
            
            switch (command) {
                case "list":
                    listConnectedDevices();
                    break;
                case "p2p":
                    handleP2PCommand(arg);
                    break;
                case "stream":
                    startRtspStream(arg);
                    break;
                case "stop":
                    stopRtspStream(arg);
                    break;
                case "streams":
                    listActiveStreams();
                    break;
                case "cameras":
                    listCamerasInDatabase();
                    break;
                case "quit":
                case "exit":
                case "q":
                    running = false;
                    break;
                default:
                    if (!command.isEmpty()) {
                        System.out.println("Unknown command: " + command);
                        System.out.println("\nAvailable commands: list, p2p <SN>, stream <id>, stop <id>, streams, quit");
                    }
                    break;
            }
        }
        
        // 4. Dọn dẹp và thoát
        System.out.println("\n[6] Cleaning up...");
        
        // Dừng tất cả streams
        StreamToRTSP.stopAllStreams();
        
        // Đăng xuất tất cả thiết bị
        for (Map.Entry<String, LLong> entry : connectedDevices.entrySet()) {
            LoginModule.netsdk.CLIENT_Logout(entry.getValue());
            System.out.println("    Logged out device: " + entry.getKey());
        }
        connectedDevices.clear();
        
        // Dừng server
        AutoRegisterModule.stopServer();
        
        // Dừng API Server
        if (apiServer != null) {
            apiServer.stop();
        }
        
        // Đóng Database
        CameraDatabase.close();
        
        // Cleanup SDK
        LoginModule.cleanup();
        
        System.out.println("    Done. Goodbye!");
        scanner.close();
    }
    
    /**
     * In danh sách các thiết bị đã kết nối
     */
    private static void listConnectedDevices() {
        if (connectedDevices.isEmpty()) {
            System.out.println("No devices connected yet.");
            return;
        }
        
        System.out.println("\nConnected Devices:");
        System.out.println("-".repeat(50));
        int index = 1;
        for (Map.Entry<String, LLong> entry : connectedDevices.entrySet()) {
            System.out.printf("  %d. DeviceID: %s, Handle: %d%n", 
                index++, entry.getKey(), entry.getValue().longValue());
        }
        System.out.println("-".repeat(50));
        System.out.println("Total: " + connectedDevices.size() + " device(s)");
    }
    
    /**
     * Xử lý lệnh P2P từ console
     * Cú pháp: p2p <serialNumber> [username] [password]
     */
    private static void handleP2PCommand(String arg) {
        if (arg.isEmpty()) {
            System.out.println("Usage:");
            System.out.println("  p2p <serialNumber>              - Connect using DB credentials");
            System.out.println("  p2p <serialNumber> <user> <pass> - Connect with explicit credentials");
            System.out.println("Example: p2p 9D0B457PAG925CA");
            return;
        }
        
        String[] p2pParts = arg.split("\\s+");
        String serialNumber = p2pParts[0];
        String username = null;
        String password = null;
        
        if (p2pParts.length >= 3) {
            // Explicit credentials
            username = p2pParts[1];
            password = p2pParts[2];
        } else {
            // Lookup from database
            CameraCredentials creds = getCredentials(serialNumber, null);
            if (creds == null) {
                System.out.println("Camera not found in database: " + serialNumber);
                System.out.println("Use: p2p <SN> <username> <password>");
                return;
            }
            username = creds.username;
            password = creds.password;
        }
        
        connectP2P(serialNumber, username, password);
    }
    
    /**
     * Kết nối camera qua P2P bằng Serial Number
     * Được gọi từ console command hoặc API server
     */
    public static String connectP2P(String serialNumber, String username, String password) {
        // Kiểm tra đã kết nối chưa
        if (connectedDevices.containsKey(serialNumber)) {
            System.out.println("[P2P] Device already connected: " + serialNumber);
            return "Already connected";
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("[P2P] Connecting to device: " + serialNumber);
        System.out.printf("[P2P] Username: %s%n", username);
        System.out.println("[P2P] Attempting P2P login...");
        
        LLong loginHandle = AutoRegisterModule.loginP2P(serialNumber, username, password);
        
        if (loginHandle.longValue() != 0) {
            System.out.println("[P2P] ✓ LOGIN SUCCESS!");
            System.out.printf("[P2P] Login Handle: %d%n", loginHandle.longValue());
            
            // Lưu vào danh sách
            connectedDevices.put(serialNumber, loginHandle);
            
            // In thông tin thiết bị
            NetSDKLib.NET_DEVICEINFO_Ex deviceInfo = AutoRegisterModule.m_stDeviceInfo;
            System.out.println("[P2P] Device Info:");
            System.out.printf("  - Serial: %s%n", new String(deviceInfo.sSerialNumber).trim());
            System.out.printf("  - Channels: %d%n", deviceInfo.byChanNum);
            System.out.println("=".repeat(50));
            
            // Auto-stream
            final String snFinal = serialNumber;
            final LLong handleFinal = loginHandle;
            Thread autoStreamThread = new Thread(() -> {
                System.out.println("\n[P2P-AUTO-STREAM] Starting RTSP stream...");
                try {
                    Thread.sleep(3000);
                    String rtspUrl = StreamToRTSP.startStream(snFinal, handleFinal, 0);
                    if (rtspUrl != null) {
                        System.out.println("[P2P-AUTO-STREAM] ✓ Stream started: " + rtspUrl);
                    } else {
                        System.out.println("[P2P-AUTO-STREAM] ✗ Failed to start stream");
                    }
                } catch (Exception e) {
                    System.err.println("[P2P-AUTO-STREAM] Error: " + e.getMessage());
                }
            });
            autoStreamThread.setDaemon(true);
            autoStreamThread.start();
            
            return "Connected successfully";
        } else {
            System.err.println("[P2P] ✗ LOGIN FAILED!");
            System.out.println("=".repeat(50));
            return null;
        }
    }
    
    /**
     * Bắt đầu RTSP stream cho camera
     */
    private static void startRtspStream(String deviceId) {
        if (deviceId.isEmpty()) {
            System.out.println("Usage: stream <deviceId>");
            System.out.println("Example: stream 9D0B457PAG925CA");
            return;
        }
        
        // Tìm login handle của device
        LLong loginHandle = null;
        for (Map.Entry<String, LLong> entry : connectedDevices.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(deviceId) || 
                entry.getKey().contains(deviceId)) {
                loginHandle = entry.getValue();
                deviceId = entry.getKey();  // Sử dụng key chính xác
                break;
            }
        }
        
        if (loginHandle == null) {
            System.out.println("Device not found: " + deviceId);
            System.out.println("Use 'list' to see connected devices.");
            return;
        }
        
        // Bắt đầu stream
        String rtspUrl = StreamToRTSP.startStream(deviceId, loginHandle, 0);
        if (rtspUrl != null) {
            System.out.println("\n>>> Stream is now available at: " + rtspUrl);
            System.out.println(">>> Test with: ffplay " + rtspUrl);
        }
    }
    
    /**
     * Dừng RTSP stream cho camera
     */
    private static void stopRtspStream(String deviceId) {
        if (deviceId.isEmpty()) {
            System.out.println("Usage: stop <deviceId>");
            return;
        }
        
        // Tìm deviceId phù hợp trong active streams
        for (String key : StreamToRTSP.getActiveStreams().keySet()) {
            if (key.equalsIgnoreCase(deviceId) || key.contains(deviceId)) {
                StreamToRTSP.stopStream(key);
                return;
            }
        }
        
        System.out.println("No active stream for device: " + deviceId);
    }
    
    /**
     * Liệt kê các stream đang hoạt động
     */
    private static void listActiveStreams() {
        Map<String, StreamToRTSP.StreamInfo> streams = StreamToRTSP.getActiveStreams();
        
        if (streams.isEmpty()) {
            System.out.println("No active RTSP streams.");
            return;
        }
        
        System.out.println("\nActive RTSP Streams:");
        System.out.println("-".repeat(60));
        int index = 1;
        for (Map.Entry<String, StreamToRTSP.StreamInfo> entry : streams.entrySet()) {
            StreamToRTSP.StreamInfo info = entry.getValue();
            long durationSec = (System.currentTimeMillis() - info.startTime) / 1000;
            System.out.printf("  %d. Device: %s%n", index++, entry.getKey());
            System.out.printf("     RTSP URL: %s%n", info.rtspUrl);
            System.out.printf("     Duration: %d sec, Data: %.2f MB%n", 
                durationSec, info.bytesWritten / (1024.0 * 1024.0));
        }
        System.out.println("-".repeat(60));
    }
    
    /**
     * Ngắt kết nối và logout camera (được gọi khi xóa camera qua API)
     */
    public static void disconnectCamera(String deviceId) {
        LLong loginHandle = connectedDevices.get(deviceId);
        if (loginHandle != null) {
            System.out.println("[DISCONNECT] Logging out camera: " + deviceId);
            
            // 1. Stop stream first
            StreamToRTSP.stopStream(deviceId);
            
            // 2. Logout from device
            LoginModule.netsdk.CLIENT_Logout(loginHandle);
            
            // 3. Remove from connected devices
            connectedDevices.remove(deviceId);
            
            System.out.println("[DISCONNECT] Camera disconnected successfully: " + deviceId);
        } else {
            System.out.println("[DISCONNECT] Camera not connected: " + deviceId);
        }
    }
    
    /**
     * Lấy loginHandle của camera đã kết nối (được gọi từ API server)
     */
    public static LLong getLoginHandle(String deviceId) {
        return connectedDevices.get(deviceId);
    }
    
    /**
     * Lấy danh sách deviceId đang kết nối (online)
     */
    public static java.util.Set<String> getConnectedDeviceIds() {
        return new java.util.HashSet<>(connectedDevices.keySet());
    }
    
    /**
     * Liệt kê các camera trong database
     */
    private static void listCamerasInDatabase() {
        try {
            java.util.List<CameraDatabase.Camera> cameras = CameraDatabase.getAllCameras();
            
            if (cameras.isEmpty()) {
                System.out.println("No cameras in database.");
                System.out.println("Use REST API to add cameras: POST http://localhost:8080/api/cameras");
                return;
            }
            
            System.out.println("\nCameras in Database:");
            System.out.println("-".repeat(80));
            System.out.printf("  %-5s %-20s %-15s %-30s %-8s%n", "ID", "Device ID", "Username", "Description", "Enabled");
            System.out.println("-".repeat(80));
            
            for (CameraDatabase.Camera cam : cameras) {
                System.out.printf("  %-5d %-20s %-15s %-30s %-8s%n",
                    cam.id, cam.deviceId, cam.username, 
                    cam.description != null ? cam.description : "", 
                    cam.enabled ? "Yes" : "No");
            }
            System.out.println("-".repeat(80));
            System.out.println("Total: " + cameras.size() + " camera(s)");
            
        } catch (Exception e) {
            System.err.println("Error listing cameras: " + e.getMessage());
        }
    }
    
    /**
     * Lấy thông tin đăng nhập cho camera dựa trên deviceId hoặc IP
     * Chỉ trả về credentials nếu camera có trong database hoặc in-memory list
     * Trả về null nếu không tìm thấy
     */
    private static CameraCredentials getCredentials(String deviceId, String ip) {
        // 1. Tìm trong database trước
        try {
            CameraDatabase.Camera dbCam = null;
            if (deviceId != null && !deviceId.isEmpty()) {
                dbCam = CameraDatabase.getByDeviceId(deviceId);
            }
            if (dbCam == null && ip != null) {
                dbCam = CameraDatabase.getByDeviceId(ip);
            }
            if (dbCam != null && dbCam.enabled) {
                return new CameraCredentials(dbCam.deviceId, dbCam.username, dbCam.password, dbCam.description);
            }
        } catch (Exception e) {
            // Database error, continue to in-memory list
        }
        
        // 2. Tìm trong in-memory list
        if (deviceId != null && !deviceId.isEmpty() && cameraList.containsKey(deviceId)) {
            return cameraList.get(deviceId);
        }
        if (ip != null && cameraList.containsKey(ip)) {
            return cameraList.get(ip);
        }
        
        // 3. Không tìm thấy - trả về null
        return null;
    }
    
    /**
     * Callback xử lý khi có thiết bị đăng ký
     */
    private static class ServiceCallback implements NetSDKLib.fServiceCallBack {
        private static ServiceCallback instance = new ServiceCallback();
        
        public static ServiceCallback getInstance() {
            return instance;
        }
        
        @Override
        public int invoke(LLong lHandle, String pIp, int wPort, int lCommand, Pointer pParam, int dwParamLen, Pointer dwUserData) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("[SERVICE CALLBACK] Received connection!");
            System.out.printf("  Handle: %d%n", lHandle.longValue());
            System.out.printf("  IP: %s, Port: %d%n", pIp, wPort);
            System.out.printf("  Command: %d (0=Register, 1=Disconnect)%n", lCommand);
            System.out.printf("  ParamLen: %d%n", dwParamLen);
            
            // Lấy device ID từ pParam nếu có
            String deviceId = "";
            if (pParam != null && dwParamLen > 0) {
                byte[] deviceIdBytes = pParam.getByteArray(0, Math.min(dwParamLen, 256));
                deviceId = new String(deviceIdBytes).trim();
                // Loại bỏ ký tự null
                int nullIndex = deviceId.indexOf('\0');
                if (nullIndex >= 0) {
                    deviceId = deviceId.substring(0, nullIndex);
                }
                System.out.printf("  Device ID from param: '%s'%n", deviceId);
            }
            
            // Key để tra cứu
            String key = deviceId.isEmpty() ? pIp : deviceId;
            
            // Kiểm tra xem đã kết nối chưa
            if (connectedDevices.containsKey(key)) {
                // Kiểm tra xem stream có đang hoạt động không (data đang chảy)
                StreamToRTSP.StreamInfo streamInfo = StreamToRTSP.getActiveStreams().get(key);
                boolean streamStale = false;
                if (streamInfo != null) {
                    // Nếu bytes không tăng trong 30 giây, coi là stale
                    long bytesNow = streamInfo.bytesWritten;
                    // Đơn giản: nếu lCommand = -1 thì là reconnect thực sự
                    if (lCommand == -1) {
                        System.out.println("  Device reconnecting, restarting stream...");
                        StreamToRTSP.stopStream(key);
                        connectedDevices.remove(key);
                        // Tiếp tục xuống để login lại
                    } else {
                        System.out.println("  Device already connected (heartbeat).");
                        System.out.println("=".repeat(50));
                        return 0;
                    }
                } else {
                    System.out.println("  Device connected but no active stream, restarting...");
                    connectedDevices.remove(key);
                    // Tiếp tục xuống để login lại
                }
            }
            
            // Thử đăng nhập nếu có device ID (bất kể Command là gì)
            if (!deviceId.isEmpty()) {
                CameraCredentials creds = getCredentials(deviceId, pIp);
                
                // Nếu không tìm thấy credentials, từ chối kết nối
                if (creds == null) {
                    System.out.println("  ✗ REJECTED: Camera not found in database!");
                    System.out.println("    Add camera via API: POST http://localhost:8080/api/cameras");
                    System.out.println("=".repeat(50));
                    return 0;
                }
                
                System.out.printf("  Using credentials: user='%s', desc='%s'%n", creds.username, creds.description);
                System.out.println("  Attempting to login...");
                
                // Đăng nhập thiết bị
                LLong loginHandle = AutoRegisterModule.login(
                    pIp, 
                    wPort, 
                    creds.username, 
                    creds.password, 
                    deviceId
                );
                
                if (loginHandle.longValue() != 0) {
                    System.out.println("  ✓ LOGIN SUCCESS!");
                    System.out.printf("  Login Handle: %d%n", loginHandle.longValue());
                    
                    // Lưu vào danh sách
                    connectedDevices.put(key, loginHandle);
                    
                    // In thông tin thiết bị
                    NetSDKLib.NET_DEVICEINFO_Ex deviceInfo = AutoRegisterModule.m_stDeviceInfo;
                    System.out.println("  Device Info:");
                    System.out.printf("    - Serial: %s%n", new String(deviceInfo.sSerialNumber).trim());
                    System.out.printf("    - Channels: %d%n", deviceInfo.byChanNum);
                    System.out.printf("    - Alarm In: %d, Alarm Out: %d%n", 
                        deviceInfo.byAlarmInPortNum, deviceInfo.byAlarmOutPortNum);
                    System.out.println("==================================================");
            
                    // AUTO-STREAM: Tự động bắt đầu stream RTSP khi camera login thành công
                    // Chạy trong thread riêng để tránh blocking callback và socket issues
                    final String deviceIdFinal = deviceId;
                    final LLong loginHandleFinal = loginHandle;
                    Thread autoStreamThread = new Thread(() -> {
                        System.out.println("\n[AUTO-STREAM] Starting RTSP stream automatically...");
                        try {
                            Thread.sleep(3000); // Đợi login ổn định (3 giây)
                            String rtspUrl = StreamToRTSP.startStream(deviceIdFinal, loginHandleFinal, 0);
                            if (rtspUrl != null) {
                                System.out.println("[AUTO-STREAM] ✓ Stream started automatically!");
                                System.out.println("[AUTO-STREAM] RTSP URL: " + rtspUrl);
                                System.out.println("[AUTO-STREAM] Test with: ffplay " + rtspUrl);
                            } else {
                                System.out.println("[AUTO-STREAM] ✗ Failed to start stream automatically");
                            }
                        } catch (Exception e) {
                            System.err.println("[AUTO-STREAM] Error: " + e.getMessage());
                        }
                    });
                    autoStreamThread.setDaemon(true);
                    autoStreamThread.start();
                    System.out.println();
                } else {
                    System.err.println("  ✗ LOGIN FAILED! " + ToolKits.getErrorCodePrint());
                }
            } else {
                System.out.println("  No device ID provided, cannot login.");
            }
            
            System.out.println("=".repeat(50));
            return 0;
        }
    }
    
    /**
     * Callback xử lý khi mất kết nối với thiết bị
     */
    private static class DisConnectCallback implements NetSDKLib.fDisConnect {
        private static DisConnectCallback instance = new DisConnectCallback();
        
        public static DisConnectCallback getInstance() {
            return instance;
        }
        
        @Override
        public void invoke(LLong lLoginID, String pchDVRIP, int nDVRPort, Pointer dwUser) {
            System.out.printf("\n[DISCONNECT] Device disconnected: %s:%d (Handle: %d)%n", 
                pchDVRIP, nDVRPort, lLoginID.longValue());
            
            // Tìm và dừng stream của device này
            String deviceKey = null;
            for (Map.Entry<String, LLong> entry : connectedDevices.entrySet()) {
                if (entry.getValue().longValue() == lLoginID.longValue()) {
                    deviceKey = entry.getKey();
                    break;
                }
            }
            
            if (deviceKey != null) {
                System.out.println("[DISCONNECT] Stopping stream for: " + deviceKey);
                StreamToRTSP.stopStream(deviceKey);
                connectedDevices.remove(deviceKey);
                System.out.println("[DISCONNECT] Device removed, waiting for reconnect...");
            }
        }
    }
    
    /**
     * Callback xử lý khi kết nối lại thành công
     */
    private static class ReConnectCallback implements NetSDKLib.fHaveReConnect {
        private static ReConnectCallback instance = new ReConnectCallback();
        
        public static ReConnectCallback getInstance() {
            return instance;
        }
        
        @Override
        public void invoke(LLong lLoginID, String pchDVRIP, int nDVRPort, Pointer dwUser) {
            System.out.printf("\n[RECONNECT] Device reconnected: %s:%d (Handle: %d)%n", 
                pchDVRIP, nDVRPort, lLoginID.longValue());
        }
    }
}
