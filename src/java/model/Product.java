package model;

public class Product {
    private int id; 
    private String code;         // THUỘC TÍNH MÃ MH
    private String name; 
    private String supplierName; // Tên nhà cung cấp
    private float salePrice; 
    private String description; 
    private int quantity; 

    public Product() {}
    
    // Constructor dùng cho Danh sách Mặt hàng
    public Product(int id, String code, String name, String supplierName, float salePrice, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.supplierName = supplierName;
        this.salePrice = salePrice;
        this.description = description;
    }
    
    // Constructor dùng cho Chi tiết Mặt hàng (có thêm quantity)
    public Product(int id, String code, String name, String supplierName, float salePrice, String description, int quantity) {
        this(id, code, name, supplierName, salePrice, description);
        this.quantity = quantity;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCode() { return code; } // GETTER CHO MÃ MH
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public float getSalePrice() { return salePrice; }
    public void setSalePrice(float salePrice) { this.salePrice = salePrice; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}