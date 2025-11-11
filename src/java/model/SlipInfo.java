package model;

import java.sql.Date;

public class SlipInfo {
    private String warehouseStaffName;
    private String warehouseStaffCode;
    private String supplierName;
    private float totalPrice;
    private Date date;

    public SlipInfo(String warehouseStaffName, String warehouseStaffCode, String supplierName, float totalPrice, Date date) {
        this.warehouseStaffName = warehouseStaffName;
        this.warehouseStaffCode = warehouseStaffCode;
        this.supplierName = supplierName;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    // Getters
    public String getWarehouseStaffName() { return warehouseStaffName; }
    public String getWarehouseStaffCode() { return warehouseStaffCode; }
    public String getSupplierName() { return supplierName; }
    public float getTotalPrice() { return totalPrice; }
    public Date getDate() { return date; }
    
    
}