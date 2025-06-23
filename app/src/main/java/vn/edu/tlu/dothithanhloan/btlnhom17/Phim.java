package vn.edu.tlu.dothithanhloan.btlnhom17;

public class Phim {
    public int posterResId;
    public String tenPhim;
    public String thoiLuong;
    public String ngayKhoiChieu;
    public String theLoai;
    public String moTa; // nếu có thêm mô tả
    public String videoId;
    public Phim(int posterResId, String tenPhim, String thoiLuong, String ngayKhoiChieu, String theLoai) {
        this.posterResId = posterResId;
        this.tenPhim = tenPhim;
        this.thoiLuong = thoiLuong;
        this.ngayKhoiChieu = ngayKhoiChieu;
        this.theLoai = theLoai;
        this.moTa = null; // hoặc gán giá trị mặc định
        this.videoId = videoId;
    }

    // --- Getter ---
    public int getHinhAnh() {
        return posterResId;
    }

    public String getTen() {
        return tenPhim;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public String getKhoiChieu() {
        return ngayKhoiChieu;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    // (nếu cần thêm setter cho mô tả)
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
