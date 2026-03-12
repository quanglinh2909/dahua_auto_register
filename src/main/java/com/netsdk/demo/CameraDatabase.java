package com.netsdk.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CameraDatabase - SQLite database for camera management
 */
public class CameraDatabase {
    
    private static final String DB_PATH = "cameras.db";
    private static Connection connection;
    
    /**
     * Camera entity
     */
    public static class Camera {
        public int id;
        public String deviceId;
        public String username;
        public String password;
        public String description;
        public boolean enabled;
        public String createdAt;
        public String updatedAt;
        
        public Camera() {}
        
        public Camera(String deviceId, String username, String password, String description) {
            this.deviceId = deviceId;
            this.username = username;
            this.password = password;
            this.description = description;
            this.enabled = true;
        }
        
        public String toJson() {
            return String.format(
                "{\"id\":%d,\"deviceId\":\"%s\",\"username\":\"%s\",\"description\":\"%s\",\"enabled\":%b,\"createdAt\":\"%s\",\"updatedAt\":\"%s\"}",
                id, escapeJson(deviceId), escapeJson(username), escapeJson(description), enabled,
                createdAt != null ? createdAt : "", updatedAt != null ? updatedAt : ""
            );
        }
        
        private String escapeJson(String s) {
            if (s == null) return "";
            return s.replace("\\", "\\\\").replace("\"", "\\\"");
        }
    }
    
    /**
     * Initialize database connection and create table
     */
    public static synchronized void init() throws SQLException {
        if (connection != null) return;
        
        try {
            Class.forName("org.sqlite.JDBC");
            // Use absolute path in user's home directory to persist data
            String dbPath = System.getProperty("user.home") + "/.dahua_cameras.db";
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            connection.setAutoCommit(true);  // Ensure data is committed immediately
            createTable();
            System.out.println("[Database] SQLite initialized: " + dbPath);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found. Add sqlite-jdbc to classpath.");
        }
    }
    
    private static void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS cameras (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "device_id TEXT UNIQUE NOT NULL," +
            "username TEXT NOT NULL DEFAULT 'admin'," +
            "password TEXT NOT NULL," +
            "description TEXT," +
            "enabled INTEGER DEFAULT 1," +
            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
            "updated_at DATETIME DEFAULT CURRENT_TIMESTAMP" +
        ")";
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }
    
    /**
     * Get all cameras
     */
    public static List<Camera> getAllCameras() throws SQLException {
        List<Camera> cameras = new ArrayList<>();
        String sql = "SELECT * FROM cameras ORDER BY id";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cameras.add(mapCamera(rs));
            }
        }
        return cameras;
    }
    
    /**
     * Get enabled cameras only
     */
    public static List<Camera> getEnabledCameras() throws SQLException {
        List<Camera> cameras = new ArrayList<>();
        String sql = "SELECT * FROM cameras WHERE enabled = 1 ORDER BY id";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cameras.add(mapCamera(rs));
            }
        }
        return cameras;
    }
    
    /**
     * Get camera by ID
     */
    public static Camera getById(int id) throws SQLException {
        String sql = "SELECT * FROM cameras WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapCamera(rs);
                }
            }
        }
        return null;
    }
    
    /**
     * Get camera by device ID
     */
    public static Camera getByDeviceId(String deviceId) throws SQLException {
        String sql = "SELECT * FROM cameras WHERE device_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, deviceId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapCamera(rs);
                }
            }
        }
        return null;
    }
    
    /**
     * Add new camera
     */
    public static Camera addCamera(Camera camera) throws SQLException {
        String sql = "INSERT INTO cameras (device_id, username, password, description, enabled) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, camera.deviceId);
            pstmt.setString(2, camera.username);
            pstmt.setString(3, camera.password);
            pstmt.setString(4, camera.description);
            pstmt.setInt(5, camera.enabled ? 1 : 0);
            
            pstmt.executeUpdate();
        }
        
        // Get last inserted ID using SQLite's last_insert_rowid()
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
            if (rs.next()) {
                camera.id = rs.getInt(1);
            }
        }
        
        return getById(camera.id);
    }
    
    /**
     * Update camera
     */
    public static Camera updateCamera(int id, Camera camera) throws SQLException {
        String sql = "UPDATE cameras SET device_id = ?, username = ?, password = ?, description = ?, enabled = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, camera.deviceId);
            pstmt.setString(2, camera.username);
            pstmt.setString(3, camera.password);
            pstmt.setString(4, camera.description);
            pstmt.setInt(5, camera.enabled ? 1 : 0);
            pstmt.setInt(6, id);
            
            pstmt.executeUpdate();
        }
        return getById(id);
    }
    
    /**
     * Delete camera
     */
    public static boolean deleteCamera(int id) throws SQLException {
        String sql = "DELETE FROM cameras WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Map ResultSet to Camera
     */
    private static Camera mapCamera(ResultSet rs) throws SQLException {
        Camera c = new Camera();
        c.id = rs.getInt("id");
        c.deviceId = rs.getString("device_id");
        c.username = rs.getString("username");
        c.password = rs.getString("password");
        c.description = rs.getString("description");
        c.enabled = rs.getInt("enabled") == 1;
        c.createdAt = rs.getString("created_at");
        c.updatedAt = rs.getString("updated_at");
        return c;
    }
    
    /**
     * Close connection
     */
    public static void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("[Database] Connection closed");
            } catch (SQLException e) {}
        }
    }
    
    /**
     * Convert list of cameras to JSON array
     */
    public static String toJsonArray(List<Camera> cameras) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < cameras.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(cameras.get(i).toJson());
        }
        sb.append("]");
        return sb.toString();
    }
}
