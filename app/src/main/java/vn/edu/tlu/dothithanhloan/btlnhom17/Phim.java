package vn.edu.tlu.dothithanhloan.btlnhom17;

public class Phim {
    public int posterResId;
    public String tenPhim;
    public String thoiLuong;
    public String ngayKhoiChieu;
    public String theLoai;

    public Phim(int posterResId, String tenPhim, String thoiLuong, String ngayKhoiChieu, String theLoai) {
        this.posterResId = posterResId;
        this.tenPhim = tenPhim;
        this.thoiLuong = thoiLuong;
        this.ngayKhoiChieu = ngayKhoiChieu;
        this.theLoai = theLoai;
    }
}
