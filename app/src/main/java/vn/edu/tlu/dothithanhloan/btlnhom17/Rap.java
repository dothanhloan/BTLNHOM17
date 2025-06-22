package vn.edu.tlu.dothithanhloan.btlnhom17;

public class Rap {
    private int logoResId;
    private String ten;
    private int gia; // ✅ sửa từ String → int

    public Rap(int logoResId, String ten, int gia) {
        this.logoResId = logoResId;
        this.ten = ten;
        this.gia = gia;
    }

    public int getLogoResId() {
        return logoResId;
    }

    public String getTen() {
        return ten;
    }

    public int getGiaVe() {
        return gia;
    }
}
