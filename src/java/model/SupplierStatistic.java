package model;

public class SupplierStatistic {
    private int id; 
    private String name; 
    private int importCount; 
    private int totalQuantity; 
    private float totalMoney; 

    public SupplierStatistic() {}
    
    public SupplierStatistic(int id, String name, int importCount, int totalQuantity, float totalMoney) {
        this.id = id;
        this.name = name;
        this.importCount = importCount;
        this.totalQuantity = totalQuantity;
        this.totalMoney = totalMoney;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getImportCount() { return importCount; }
    public int getTotalQuantity() { return totalQuantity; }
    public float getTotalMoney() { return totalMoney; }
    // ...
}