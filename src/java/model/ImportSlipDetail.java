package model;

public class ImportSlipDetail {
    private int id;
    private int quantity;
    private float importPrice;
    private float subTotal; 
    private String productName; 
    private String productUnit; 
    private String productCode; // <-- THUỘC TÍNH MỚI

    public ImportSlipDetail() {}

    // Constructor đã cập nhật
    public ImportSlipDetail(int id, int quantity, float importPrice, String productName, String productUnit, String productCode) {
        this.id = id;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.productName = productName;
        this.productUnit = productUnit;
        this.productCode = productCode;
        this.subTotal = quantity * importPrice;
    }

    // Getters
    public int getId() { return id; }
    public int getQuantity() { return quantity; }
    public float getImportPrice() { return importPrice; }
    public String getProductName() { return productName; }
    public String getProductUnit() { return productUnit; }
    public float getSubTotal() { return subTotal; }
    public String getProductCode() { return productCode; } // <-- GETTER MỚI
    
    // Setters (Nếu bạn không dùng, có thể bỏ qua)
    // public void set...
}