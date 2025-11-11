package model;

import java.sql.Date;

public class ImportSlip {
    private int id;
    private Date date;
    private float totalPrice;
    private String supplierName; 
    private String warehouseStaffName; 

    public ImportSlip() {}
    
    public ImportSlip(int id, Date date, float totalPrice, String supplierName, String warehouseStaffName) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
        this.supplierName = supplierName;
        this.warehouseStaffName = warehouseStaffName;
    }

    // Getters
    public int getId() { return id; }
    public Date getDate() { return date; }
    public float getTotalPrice() { return totalPrice; }
    public String getSupplierName() { return supplierName; }
    public String getWarehouseStaffName() { return warehouseStaffName; }
    // ...
}