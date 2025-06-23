package vn.edu.tlu.dothithanhloan.btlnhom17.model;

public class Screening {
    private int id;
    private int movieId;
    private int roomId;
    private String startDate; // Lưu dưới dạng chuỗi ngày giờ
    private String movieName; // Để hiển thị tên phim trong danh sách
    private String roomNumber; // Để hiển thị số phòng trong danh sách

    // Constructor đầy đủ thông tin (từ DB, có tên phim/phòng)
    public Screening(int id, int movieId, int roomId, String startDate, String movieName, String roomNumber) {
        this.id = id;
        this.movieId = movieId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.movieName = movieName;
        this.roomNumber = roomNumber;
    }

    // Constructor khi thêm mới (chỉ có ID)
    public Screening(int movieId, int roomId, String startDate) {
        this.movieId = movieId;
        this.roomId = roomId;
        this.startDate = startDate;
    }



    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
}