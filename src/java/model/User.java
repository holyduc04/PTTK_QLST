package model;

// Dựa trên lớp User trong Sơ đồ lớp (Diagram) 
public class User {
    private int id; 
    private String user; 
    private String password; 
    private String name; 
    private String role; // 'Customer', 'ManagementStaff', 

    public User() {}

    // Constructor dùng để đóng gói thông tin sau khi đăng nhập thành công (không bao gồm password)
    public User(int id, String user, String name, String role) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.role = role;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    // Bạn có thể bổ sung các thuộc tính khác như phone, email, v.v. nếu cần
}