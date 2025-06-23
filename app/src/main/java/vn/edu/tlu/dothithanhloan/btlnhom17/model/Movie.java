package vn.edu.tlu.dothithanhloan.btlnhom17.model;

public class Movie {
    private int id;
    private String name;
    private String totalTime; // e.g., "115 phút"
    private String description;
    private String avatar;    // Path to drawable or URL for the movie poster
    private String trailer;   // URL for the trailer
    private String releaseDate; // e.g., "26/07/2024"
    private int categoryId;   // Foreign Key to Category table

    // Constructor đầy đủ thông tin
    public Movie(int id, String name, String totalTime, String description, String avatar, String trailer, String releaseDate, int categoryId) {
        this.id = id;
        this.name = name;
        this.totalTime = totalTime;
        this.description = description;
        this.avatar = avatar;
        this.trailer = trailer;
        this.releaseDate = releaseDate;
        this.categoryId = categoryId;
    }

    // Constructor không có ID (dùng khi thêm phim mới, ID sẽ tự động tạo bởi DB)
    public Movie(String name, String totalTime, String description, String avatar, String trailer, String releaseDate, int categoryId) {
        this.name = name;
        this.totalTime = totalTime;
        this.description = description;
        this.avatar = avatar;
        this.trailer = trailer;
        this.releaseDate = releaseDate;
        this.categoryId = categoryId;
    }

    // Getters (phương thức để lấy giá trị)
    public int getId() { return id; }
    public String getName() { return name; }
    public String getTotalTime() { return totalTime; }
    public String getDescription() { return description; }
    public String getAvatar() { return avatar; }
    public String getTrailer() { return trailer; }
    public String getReleaseDate() { return releaseDate; }
    public int getCategoryId() { return categoryId; }

    // Setters (phương thức để gán giá trị)
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setTotalTime(String totalTime) { this.totalTime = totalTime; }
    public void setDescription(String description) { this.description = description; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public void setTrailer(String trailer) { this.trailer = trailer; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    // THÊM PHƯƠNG THỨC toString() NÀY VÀO LỚP MOVIE.JAVA
    @Override
    public String toString() {
        return name; // Trả về tên phim để hiển thị trong Spinner
    }
}