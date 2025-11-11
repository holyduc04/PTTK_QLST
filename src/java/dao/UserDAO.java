package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

public class UserDAO {
    
    public User checkLogin(String userID, String password) {
        // Truy vấn dựa trên bảng tblUser trong Sơ đồ CSDL [cite: 22]
        String SQL = "SELECT id, user, name, role FROM tblUser WHERE user = ? AND password = ?";
        User user = null;
        
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL)) {
            
            ps.setString(1, userID);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Đăng nhập thành công, đóng gói thông tin User
                    user = new User(
                        rs.getInt("id"),
                        rs.getString("user"),
                        rs.getString("name"),
                        rs.getString("role") // Phân biệt vai trò
                    );
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi checkLogin: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }
}