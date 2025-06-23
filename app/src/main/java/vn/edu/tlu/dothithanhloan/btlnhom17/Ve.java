package vn.edu.tlu.dothithanhloan.btlnhom17;

public class Ve {
    public String tenPhim;
    public String rap;
    public String ngay;
    public String gio;
    public String ghe;
    public int tongTien;

    // ✅ Constructor đầy đủ 6 tham số
    public Ve(String tenPhim, String rap, String ngay, String gio, String ghe, int tongTien) {
        this.tenPhim = tenPhim;
        this.rap = rap;
        this.ngay = ngay;
        this.gio = gio;
        this.ghe = ghe;
        this.tongTien = tongTien;
    }

    // ✅ Hàm tiện lợi nếu cần hiển thị kết hợp ngày + giờ
    public String getSuatChieu() {
        return ngay + " " + gio;
    }
}
