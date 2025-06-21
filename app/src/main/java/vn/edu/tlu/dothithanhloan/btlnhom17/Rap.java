package vn.edu.tlu.dothithanhloan.btlnhom17;

public class Rap {
    private int logoResId;
    private String ten;
    private String gia;

    public Rap(int logoResId, String ten, String gia) {
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

    public String getGia() {
        return gia;
    }
}
