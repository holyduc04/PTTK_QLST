package model;

public class Supplier {
    private int id;
    private String name;
    private String phone;
    private String address;
    private String email;

    public Supplier() {}
    
    public Supplier(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Getters and Setters (Chỉ include những cái cần thiết cho việc code DAO)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    // ...
}