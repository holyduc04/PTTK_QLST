package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // Thông tin kết nối MySQL
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/qlstdb"; 
    private static final String USER = "root"; // Tên người dùng MySQL của bạn
    private static final String PASS = "123456"; // Mật khẩu MySQL của bạn

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // 1. Load Driver
        Class.forName(DRIVER);
        // 2. Tạo Connection
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    
    // Trong các dự án thực tế, bạn nên sử dụng Connection Pool (ví dụ: DBCP, C3P0)
    // thay vì DriverManager.getConnection() cho mỗi request.
}