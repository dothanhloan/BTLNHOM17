package vn.edu.tlu.dothithanhloan.btlnhom17.model;

public class Category {
    private int id;
    private String name;

    // Constructor đầy đủ thông tin
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Constructor không có ID (dùng khi thêm danh mục mới)
    public Category(String name) {
        this.name = name;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return name; // Hữu ích khi hiển thị Category trong Spinner/Dropdown
    }
}
