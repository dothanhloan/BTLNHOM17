package vn.edu.tlu.dothithanhloan.btlnhom17.model;

public class Service {
    private int id;
    private String name;
    private String avatar; // Đường dẫn đến ảnh (Resource ID hoặc URL)
    private double priceUnit;
    private String unit; // e.g., "Túi", "Cốc"

    // Constructor đầy đủ thông tin
    public Service(int id, String name, String avatar, double priceUnit, String unit) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.priceUnit = priceUnit;
        this.unit = unit;
    }

    // Constructor không có ID (dùng khi thêm dịch vụ mới)
    public Service(String name, String avatar, double priceUnit, String unit) {
        this.name = name;
        this.avatar = avatar;
        this.priceUnit = priceUnit;
        this.unit = unit;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getAvatar() { return avatar; }
    public double getPriceUnit() { return priceUnit; }
    public String getUnit() { return unit; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public void setPriceUnit(double priceUnit) { this.priceUnit = priceUnit; }
    public void setUnit(String unit) { this.unit = unit; }
}
