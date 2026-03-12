package com.netsdk.demo;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CameraApiServer - REST API for camera management
 * 
 * Endpoints:
 * - GET    /api/cameras          - List all cameras
 * - GET    /api/cameras/{id}     - Get camera by ID
 * - POST   /api/cameras          - Add new camera
 * - PUT    /api/cameras/{id}     - Update camera
 * - DELETE /api/cameras/{id}     - Delete camera
 * - GET    /api/streams          - List active streams
 * - POST   /api/streams/{deviceId}/start  - Start stream
 * - POST   /api/streams/{deviceId}/stop   - Stop stream
 */
public class CameraApiServer {
    
    private HttpServer server;
    private int port;
    
    public CameraApiServer(int port) {
        this.port = port;
    }
    
    /**
     * Start the API server
     */
    public void start() throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        
        // Camera CRUD endpoints
        server.createContext("/api/cameras", new CamerasHandler());
        
        // Stream control endpoints
        server.createContext("/api/streams", new StreamsHandler());
        
        // P2P connection endpoints
        server.createContext("/api/p2p", new P2PHandler());
        
        // Camera status (online/offline)
        server.createContext("/api/cameras/status", exchange -> {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            Set<String> connected = AutoRegisterDemo.getConnectedDeviceIds();
            StringBuilder json = new StringBuilder("{\"connectedDevices\":[");
            boolean first = true;
            for (String id : connected) {
                if (!first) json.append(",");
                first = false;
                json.append("\"" + escapeJson(id) + "\"");
            }
            json.append("]}");
            sendJson(exchange, 200, json.toString());
        });
        
        // Health check
        server.createContext("/api/health", exchange -> {
            sendJson(exchange, 200, "{\"status\":\"ok\"}");
        });
        
        // OpenAPI docs
        server.createContext("/api/docs", exchange -> {
            String openApiJson = getOpenApiSpec();
            sendJson(exchange, 200, openApiJson);
        });
        
        // Static file server (serves index.html)
        server.createContext("/", new StaticFileHandler());
        
        server.setExecutor(null);
        server.start();
        
        System.out.println("[API Server] Started on port " + port);
        System.out.println("[API Server] Web UI: http://localhost:" + port + "/");
        System.out.println("[API Server] Endpoints:");
        System.out.println("  GET    http://localhost:" + port + "/api/docs       - OpenAPI Spec");
        System.out.println("  GET    http://localhost:" + port + "/api/cameras");
        System.out.println("  GET    http://localhost:" + port + "/api/cameras/status");
        System.out.println("  POST   http://localhost:" + port + "/api/cameras");
        System.out.println("  PUT    http://localhost:" + port + "/api/cameras/{id}");
        System.out.println("  DELETE http://localhost:" + port + "/api/cameras/{id}");
        System.out.println("  GET    http://localhost:" + port + "/api/streams");
        System.out.println("  POST   http://localhost:" + port + "/api/streams/{deviceId}/start");
        System.out.println("  POST   http://localhost:" + port + "/api/streams/{deviceId}/stop");
        System.out.println("  POST   http://localhost:" + port + "/api/p2p/{serialNumber}/connect");
        System.out.println("  POST   http://localhost:" + port + "/api/p2p/{serialNumber}/disconnect");
    }
    
    /**
     * Generate OpenAPI 3.0 specification
     */
    private static String getOpenApiSpec() {
        return "{" +
            "\"openapi\":\"3.0.0\"," +
            "\"info\":{\"title\":\"Camera Management API\",\"version\":\"1.0.0\",\"description\":\"REST API for Dahua camera management and RTSP streaming\"}," +
            "\"servers\":[{\"url\":\"http://localhost:8080\"}]," +
            "\"paths\":{" +
                "\"/api/cameras\":{" +
                    "\"get\":{\"summary\":\"List all cameras\",\"tags\":[\"Cameras\"],\"responses\":{\"200\":{\"description\":\"Array of cameras\"}}}," +
                    "\"post\":{\"summary\":\"Add new camera\",\"tags\":[\"Cameras\"],\"requestBody\":{\"required\":true,\"content\":{\"application/json\":{\"schema\":{\"$ref\":\"#/components/schemas/CameraInput\"},\"example\":{\"deviceId\":\"9D0B457PAG925CA\",\"username\":\"admin\",\"password\":\"Oryza123\",\"description\":\"Camera test\",\"enabled\":true}}}},\"responses\":{\"201\":{\"description\":\"Camera created\"}}}" +
                "}," +
                "\"/api/cameras/{id}\":{" +
                    "\"get\":{\"summary\":\"Get camera by ID\",\"tags\":[\"Cameras\"],\"parameters\":[{\"name\":\"id\",\"in\":\"path\",\"required\":true,\"schema\":{\"type\":\"integer\"}}],\"responses\":{\"200\":{\"description\":\"Camera details\"},\"404\":{\"description\":\"Not found\"}}}," +
                    "\"put\":{\"summary\":\"Update camera\",\"tags\":[\"Cameras\"],\"parameters\":[{\"name\":\"id\",\"in\":\"path\",\"required\":true,\"schema\":{\"type\":\"integer\"}}],\"requestBody\":{\"content\":{\"application/json\":{\"schema\":{\"$ref\":\"#/components/schemas/CameraInput\"},\"example\":{\"deviceId\":\"9D0B457PAG925CA\",\"username\":\"admin\",\"password\":\"NewPassword123\",\"description\":\"Updated description\"}}}},\"responses\":{\"200\":{\"description\":\"Camera updated\"}}}," +
                    "\"delete\":{\"summary\":\"Delete camera\",\"tags\":[\"Cameras\"],\"parameters\":[{\"name\":\"id\",\"in\":\"path\",\"required\":true,\"schema\":{\"type\":\"integer\"}}],\"responses\":{\"200\":{\"description\":\"Camera deleted\"}}}" +
                "}," +
                "\"/api/streams\":{" +
                    "\"get\":{\"summary\":\"List active streams\",\"tags\":[\"Streams\"],\"responses\":{\"200\":{\"description\":\"Array of active streams\"}}}" +
                "}," +
                "\"/api/streams/{deviceId}/start\":{" +
                    "\"post\":{\"summary\":\"Start stream for device\",\"tags\":[\"Streams\"],\"parameters\":[{\"name\":\"deviceId\",\"in\":\"path\",\"required\":true,\"schema\":{\"type\":\"string\"},\"example\":\"9D0B457PAG925CA\"}],\"responses\":{\"200\":{\"description\":\"Stream started or already running\"}}}" +
                "}," +
                "\"/api/streams/{deviceId}/stop\":{" +
                    "\"post\":{\"summary\":\"Stop stream for device\",\"tags\":[\"Streams\"],\"parameters\":[{\"name\":\"deviceId\",\"in\":\"path\",\"required\":true,\"schema\":{\"type\":\"string\"},\"example\":\"9D0B457PAG925CA\"}],\"responses\":{\"200\":{\"description\":\"Stream stopped\"},\"404\":{\"description\":\"Stream not found\"}}}" +
                "}," +
                "\"/api/health\":{\"get\":{\"summary\":\"Health check\",\"tags\":[\"System\"],\"responses\":{\"200\":{\"description\":\"OK\"}}}}" +
            "}," +
            "\"components\":{\"schemas\":{" +
                "\"CameraInput\":{\"type\":\"object\",\"required\":[\"deviceId\",\"password\"],\"properties\":{\"deviceId\":{\"type\":\"string\",\"description\":\"Camera device ID\",\"example\":\"9D0B457PAG925CA\"},\"username\":{\"type\":\"string\",\"default\":\"admin\",\"example\":\"admin\"},\"password\":{\"type\":\"string\",\"description\":\"Camera password\",\"example\":\"Oryza123\"},\"description\":{\"type\":\"string\",\"example\":\"Camera test\"},\"enabled\":{\"type\":\"boolean\",\"default\":true}},\"example\":{\"deviceId\":\"9D0B457PAG925CA\",\"username\":\"admin\",\"password\":\"Oryza123\",\"description\":\"Camera test\",\"enabled\":true}}," +
                "\"Camera\":{\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"integer\"},\"deviceId\":{\"type\":\"string\"},\"username\":{\"type\":\"string\"},\"description\":{\"type\":\"string\"},\"enabled\":{\"type\":\"boolean\"},\"createdAt\":{\"type\":\"string\"},\"updatedAt\":{\"type\":\"string\"}}}," +
                "\"Stream\":{\"type\":\"object\",\"properties\":{\"deviceId\":{\"type\":\"string\"},\"rtspUrl\":{\"type\":\"string\"},\"isRunning\":{\"type\":\"boolean\"},\"bytesWritten\":{\"type\":\"integer\"},\"restarts\":{\"type\":\"integer\"}}}" +
            "}}" +
        "}";
    }
    
    /**
     * Stop the server
     */
    public void stop() {
        if (server != null) {
            server.stop(0);
            System.out.println("[API Server] Stopped");
        }
    }
    
    /**
     * Handler for /api/cameras endpoints
     */
    private static class CamerasHandler implements HttpHandler {
        private static final Pattern ID_PATTERN = Pattern.compile("/api/cameras/(\\d+)");
        
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            
            // Add CORS headers
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            
            if ("OPTIONS".equals(method)) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            
            try {
                Matcher matcher = ID_PATTERN.matcher(path);
                
                if (matcher.matches()) {
                    // /api/cameras/{id}
                    int id = Integer.parseInt(matcher.group(1));
                    handleCameraById(exchange, method, id);
                } else if ("/api/cameras".equals(path)) {
                    // /api/cameras
                    handleCamerasList(exchange, method);
                } else {
                    sendJson(exchange, 404, "{\"error\":\"Not found\"}");
                }
            } catch (Exception e) {
                sendJson(exchange, 500, "{\"error\":\"" + escapeJson(e.getMessage()) + "\"}");
            }
        }
        
        private void handleCamerasList(HttpExchange exchange, String method) throws Exception {
            if ("GET".equals(method)) {
                // List all cameras
                List<CameraDatabase.Camera> cameras = CameraDatabase.getAllCameras();
                sendJson(exchange, 200, CameraDatabase.toJsonArray(cameras));
                
            } else if ("POST".equals(method)) {
                // Add new camera
                String body = readBody(exchange);
                CameraDatabase.Camera camera = parseCamera(body);
                
                if (camera.deviceId == null || camera.deviceId.isEmpty()) {
                    sendJson(exchange, 400, "{\"error\":\"deviceId is required\"}");
                    return;
                }
                if (camera.password == null || camera.password.isEmpty()) {
                    sendJson(exchange, 400, "{\"error\":\"password is required\"}");
                    return;
                }
                
                // Check if deviceId already exists
                if (CameraDatabase.getByDeviceId(camera.deviceId) != null) {
                    sendJson(exchange, 409, "{\"error\":\"Camera with this deviceId already exists\"}");
                    return;
                }
                
                CameraDatabase.Camera created = CameraDatabase.addCamera(camera);
                sendJson(exchange, 201, created.toJson());
                
            } else {
                sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
            }
        }
        
        private void handleCameraById(HttpExchange exchange, String method, int id) throws Exception {
            if ("GET".equals(method)) {
                // Get camera by ID
                CameraDatabase.Camera camera = CameraDatabase.getById(id);
                if (camera == null) {
                    sendJson(exchange, 404, "{\"error\":\"Camera not found\"}");
                } else {
                    sendJson(exchange, 200, camera.toJson());
                }
                
            } else if ("PUT".equals(method)) {
                // Update camera
                CameraDatabase.Camera existing = CameraDatabase.getById(id);
                if (existing == null) {
                    sendJson(exchange, 404, "{\"error\":\"Camera not found\"}");
                    return;
                }
                
                String body = readBody(exchange);
                CameraDatabase.Camera camera = parseCamera(body);
                camera.deviceId = camera.deviceId != null ? camera.deviceId : existing.deviceId;
                camera.username = camera.username != null ? camera.username : existing.username;
                camera.password = camera.password != null ? camera.password : existing.password;
                camera.description = camera.description != null ? camera.description : existing.description;
                
                CameraDatabase.Camera updated = CameraDatabase.updateCamera(id, camera);
                sendJson(exchange, 200, updated.toJson());
                
            } else if ("DELETE".equals(method)) {
                // Delete camera
                CameraDatabase.Camera camera = CameraDatabase.getById(id);
                if (camera == null) {
                    sendJson(exchange, 404, "{\"error\":\"Camera not found\"}");
                    return;
                }
                
                // Disconnect camera (stop stream + logout + remove from connected devices)
                AutoRegisterDemo.disconnectCamera(camera.deviceId);
                
                // Delete from database
                CameraDatabase.deleteCamera(id);
                sendJson(exchange, 200, "{\"message\":\"Camera deleted and disconnected\"}");
                
            } else {
                sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
            }
        }
    }
    
    /**
     * Handler for /api/streams endpoints
     */
    private static class StreamsHandler implements HttpHandler {
        private static final Pattern START_PATTERN = Pattern.compile("/api/streams/([^/]+)/start");
        private static final Pattern STOP_PATTERN = Pattern.compile("/api/streams/([^/]+)/stop");
        
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            
            if ("OPTIONS".equals(method)) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            
            try {
                Matcher startMatcher = START_PATTERN.matcher(path);
                Matcher stopMatcher = STOP_PATTERN.matcher(path);
                
                if ("/api/streams".equals(path) && "GET".equals(method)) {
                    // List active streams
                    Map<String, StreamToRTSP.StreamInfo> streams = StreamToRTSP.getActiveStreams();
                    StringBuilder json = new StringBuilder("[");
                    boolean first = true;
                    for (Map.Entry<String, StreamToRTSP.StreamInfo> entry : streams.entrySet()) {
                        if (!first) json.append(",");
                        first = false;
                        StreamToRTSP.StreamInfo info = entry.getValue();
                        json.append(String.format(
                            "{\"deviceId\":\"%s\",\"rtspUrl\":\"%s\",\"isRunning\":%b,\"bytesWritten\":%d,\"restarts\":%d}",
                            entry.getKey(), info.rtspUrl, info.isRunning, info.bytesWritten, info.restartCount
                        ));
                    }
                    json.append("]");
                    sendJson(exchange, 200, json.toString());
                    
                } else if (startMatcher.matches() && "POST".equals(method)) {
                    // Start stream
                    String deviceId = startMatcher.group(1);
                    
                    // Check if camera exists in database
                    CameraDatabase.Camera camera = CameraDatabase.getByDeviceId(deviceId);
                    if (camera == null) {
                        sendJson(exchange, 404, "{\"error\":\"Camera not found in database\"}");
                        return;
                    }
                    
                    // Check if already streaming
                    if (StreamToRTSP.getActiveStreams().containsKey(deviceId)) {
                        StreamToRTSP.StreamInfo info = StreamToRTSP.getActiveStreams().get(deviceId);
                        sendJson(exchange, 200, String.format(
                            "{\"message\":\"Stream already running\",\"rtspUrl\":\"%s\"}", info.rtspUrl));
                        return;
                    }
                    
                    // Check if camera is connected and get loginHandle
                    com.netsdk.lib.NetSDKLib.LLong loginHandle = AutoRegisterDemo.getLoginHandle(deviceId);
                    if (loginHandle == null || loginHandle.longValue() == 0) {
                        sendJson(exchange, 404, "{\"error\":\"Camera not connected. Please wait for camera to connect.\"}");
                        return;
                    }
                    
                    // Start the stream
                    String rtspUrl = StreamToRTSP.startStream(deviceId, loginHandle, 0);
                    if (rtspUrl != null) {
                        sendJson(exchange, 200, String.format(
                            "{\"message\":\"Stream started\",\"rtspUrl\":\"%s\"}", rtspUrl));
                    } else {
                        sendJson(exchange, 500, "{\"error\":\"Failed to start stream\"}");
                    }
                    
                    
                } else if (stopMatcher.matches() && "POST".equals(method)) {
                    // Stop stream
                    String deviceId = stopMatcher.group(1);
                    
                    if (StreamToRTSP.stopStream(deviceId)) {
                        sendJson(exchange, 200, "{\"message\":\"Stream stopped\"}");
                    } else {
                        sendJson(exchange, 404, "{\"error\":\"Stream not found\"}");
                    }
                    
                } else {
                    sendJson(exchange, 404, "{\"error\":\"Not found\"}");
                }
            } catch (Exception e) {
                sendJson(exchange, 500, "{\"error\":\"" + escapeJson(e.getMessage()) + "\"}");
            }
        }
    }
    
    /**
     * Handler for /api/p2p endpoints
     * POST /api/p2p/{serialNumber}/connect    - Connect camera via P2P
     * POST /api/p2p/{serialNumber}/disconnect - Disconnect P2P camera
     */
    private static class P2PHandler implements HttpHandler {
        private static final Pattern CONNECT_PATTERN = Pattern.compile("/api/p2p/([^/]+)/connect");
        private static final Pattern DISCONNECT_PATTERN = Pattern.compile("/api/p2p/([^/]+)/disconnect");
        
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            
            if ("OPTIONS".equals(method)) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            
            try {
                Matcher connectMatcher = CONNECT_PATTERN.matcher(path);
                Matcher disconnectMatcher = DISCONNECT_PATTERN.matcher(path);
                
                if (connectMatcher.matches() && "POST".equals(method)) {
                    // Connect via P2P
                    String serialNumber = connectMatcher.group(1);
                    
                    // Check if already connected
                    com.netsdk.lib.NetSDKLib.LLong existingHandle = AutoRegisterDemo.getLoginHandle(serialNumber);
                    if (existingHandle != null && existingHandle.longValue() != 0) {
                        sendJson(exchange, 200, "{\"message\":\"Already connected\",\"serialNumber\":\"" + serialNumber + "\"}");
                        return;
                    }
                    
                    // Lookup credentials from database
                    CameraDatabase.Camera camera = CameraDatabase.getByDeviceId(serialNumber);
                    if (camera == null) {
                        sendJson(exchange, 404, "{\"error\":\"Camera not found in database. Add it first via POST /api/cameras\"}");
                        return;
                    }
                    
                    // Connect via P2P
                    String result = AutoRegisterDemo.connectP2P(serialNumber, camera.username, camera.password);
                    if (result != null) {
                        sendJson(exchange, 200, "{\"message\":\"P2P connected\",\"serialNumber\":\"" + serialNumber + "\"}");
                    } else {
                        sendJson(exchange, 500, "{\"error\":\"P2P login failed for " + serialNumber + "\"}");
                    }
                    
                } else if (disconnectMatcher.matches() && "POST".equals(method)) {
                    // Disconnect P2P
                    String serialNumber = disconnectMatcher.group(1);
                    AutoRegisterDemo.disconnectCamera(serialNumber);
                    sendJson(exchange, 200, "{\"message\":\"Disconnected\",\"serialNumber\":\"" + serialNumber + "\"}");
                    
                } else {
                    sendJson(exchange, 404, "{\"error\":\"Not found. Use POST /api/p2p/{serialNumber}/connect or /disconnect\"}");
                }
            } catch (Exception e) {
                sendJson(exchange, 500, "{\"error\":\"" + escapeJson(e.getMessage()) + "\"}");
            }
        }
    }
    
    /**
     * Handler for serving static files (index.html)
     */
    private static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            if ("/".equals(path) || "/index.html".equals(path)) {
                // Resolve project root: CWD is target/, go up to project root
                Path filePath = null;
                Path cwd = Paths.get(System.getProperty("user.dir"));
                Path projectRoot = cwd.getParent(); // target/ -> project root
                if (projectRoot == null) projectRoot = cwd;
                
                String[] candidates = {
                    "src/main/resources/static/index.html",
                    "resources/static/index.html",
                    "static/index.html"
                };
                
                // Try from project root first
                for (String c : candidates) {
                    Path p = projectRoot.resolve(c);
                    if (Files.exists(p)) {
                        filePath = p;
                        break;
                    }
                }
                // Fallback: try from CWD
                if (filePath == null) {
                    for (String c : candidates) {
                        Path p = cwd.resolve(c);
                        if (Files.exists(p)) {
                            filePath = p;
                            break;
                        }
                    }
                }
                
                if (filePath != null && Files.exists(filePath)) {
                    byte[] bytes = Files.readAllBytes(filePath);
                    exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                    exchange.sendResponseHeaders(200, bytes.length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(bytes);
                    }
                } else {
                    String msg = "index.html not found. Place it in src/main/resources/static/";
                    exchange.getResponseHeaders().set("Content-Type", "text/plain");
                    byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
                    exchange.sendResponseHeaders(404, bytes.length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(bytes);
                    }
                }
            } else {
                exchange.sendResponseHeaders(404, -1);
            }
        }
    }
    
    // Helper methods
    
    private static void sendJson(HttpExchange exchange, int code, String json) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
    
    private static String readBody(HttpExchange exchange) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
    }
    
    private static CameraDatabase.Camera parseCamera(String json) {
        CameraDatabase.Camera camera = new CameraDatabase.Camera();
        camera.enabled = true;  // Default
        
        // Simple JSON parsing (no external library)
        camera.deviceId = extractJsonString(json, "deviceId");
        camera.username = extractJsonString(json, "username");
        if (camera.username == null) camera.username = "admin";
        camera.password = extractJsonString(json, "password");
        camera.description = extractJsonString(json, "description");
        
        String enabled = extractJsonString(json, "enabled");
        if (enabled != null) {
            camera.enabled = "true".equalsIgnoreCase(enabled) || "1".equals(enabled);
        }
        
        return camera;
    }
    
    private static String extractJsonString(String json, String key) {
        // Pattern for "key":"value" or "key":value
        Pattern p = Pattern.compile("\"" + key + "\"\\s*:\\s*\"([^\"]*)\"|\"" + key + "\"\\s*:\\s*([^,}\\]]+)");
        Matcher m = p.matcher(json);
        if (m.find()) {
            return m.group(1) != null ? m.group(1) : m.group(2).trim();
        }
        return null;
    }
    
    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
