package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChonBapNuocActivity extends AppCompatActivity {

    TextView txtTongTien;
    int tongTien = 0;
    int seatCost = 0;

    // Dữ liệu ghế và phim
    String tenPhim, rap, ngay, gio, ghe;

    int slBapCaramel = 0, slBapPhoMai = 0, slPepsi = 0, slCombo = 0;
    int giaBap = 80000, giaPhoMai = 75000, giaPepsi = 30000, giaCombo = 115000;

    TextView txtSLBap, txtSLPhoMai, txtSLPepsi, txtSLCombo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_bap_nuoc);

        // ✅ Nhận dữ liệu từ Intent
        seatCost = getIntent().getIntExtra("seatCost", 0);
        tenPhim  = getIntent().getStringExtra("tenPhim");
        rap      = getIntent().getStringExtra("rap");
        ngay     = getIntent().getStringExtra("ngay");
        gio = getIntent().getStringExtra("gio"); // ✔ đúng theo key truyền từ ChonGheActivity
        ghe      = getIntent().getStringExtra("ghe");

        txtTongTien = findViewById(R.id.txtTongTien);

        View viewBapCaramel = findViewById(R.id.monBapCaramel);
        View viewBapPhoMai  = findViewById(R.id.monBapPhoMai);
        View viewPepsi      = findViewById(R.id.monPepsi);
        View viewCombo      = findViewById(R.id.monCombo);

        txtSLBap    = viewBapCaramel.findViewById(R.id.txtSoLuong);
        txtSLPhoMai = viewBapPhoMai.findViewById(R.id.txtSoLuong);
        txtSLPepsi  = viewPepsi.findViewById(R.id.txtSoLuong);
        txtSLCombo  = viewCombo.findViewById(R.id.txtSoLuong);

        // Gán giá và tên món
        ((TextView) viewBapCaramel.findViewById(R.id.txtGiaMon)).setText(giaBap + "đ");
        ((TextView) viewBapPhoMai.findViewById(R.id.txtGiaMon)).setText(giaPhoMai + "đ");
        ((TextView) viewPepsi.findViewById(R.id.txtGiaMon)).setText(giaPepsi + "đ");
        ((TextView) viewCombo.findViewById(R.id.txtGiaMon)).setText(giaCombo + "đ");

        ((TextView) viewBapCaramel.findViewById(R.id.txtTenMon)).setText("Bắp Caramel");
        ((TextView) viewBapPhoMai.findViewById(R.id.txtTenMon)).setText("Bắp Phô Mai");
        ((TextView) viewPepsi.findViewById(R.id.txtTenMon)).setText("Nước Pepsi");
        ((TextView) viewCombo.findViewById(R.id.txtTenMon)).setText("Combo Bắp + Nước");

        ((ImageView) viewBapCaramel.findViewById(R.id.imgMon)).setImageResource(R.drawable.bap);
        ((ImageView) viewBapPhoMai.findViewById(R.id.imgMon)).setImageResource(R.drawable.bap);
        ((ImageView) viewPepsi.findViewById(R.id.imgMon)).setImageResource(R.drawable.nuoc);
        ((ImageView) viewCombo.findViewById(R.id.imgMon)).setImageResource(R.drawable.combo);

        // Nút + -
        viewBapCaramel.findViewById(R.id.btnCong).setOnClickListener(v -> { slBapCaramel++; updateUI(); });
        viewBapCaramel.findViewById(R.id.btnTru).setOnClickListener(v -> { if (slBapCaramel > 0) slBapCaramel--; updateUI(); });

        viewBapPhoMai.findViewById(R.id.btnCong).setOnClickListener(v -> { slBapPhoMai++; updateUI(); });
        viewBapPhoMai.findViewById(R.id.btnTru).setOnClickListener(v -> { if (slBapPhoMai > 0) slBapPhoMai--; updateUI(); });

        viewPepsi.findViewById(R.id.btnCong).setOnClickListener(v -> { slPepsi++; updateUI(); });
        viewPepsi.findViewById(R.id.btnTru).setOnClickListener(v -> { if (slPepsi > 0) slPepsi--; updateUI(); });

        viewCombo.findViewById(R.id.btnCong).setOnClickListener(v -> { slCombo++; updateUI(); });
        viewCombo.findViewById(R.id.btnTru).setOnClickListener(v -> { if (slCombo > 0) slCombo--; updateUI(); });

        Button btnHoanThanh = findViewById(R.id.btnHoanThanh);
        btnHoanThanh.setOnClickListener(v -> {
            int total = tongTien + seatCost;
            Intent intent = new Intent(ChonBapNuocActivity.this, ThanhToanActivity.class);
            intent.putExtra("tongTien", total);
            intent.putExtra("seatCost", seatCost);

            // ✅ Truyền thêm thông tin vé
            intent.putExtra("tenPhim", tenPhim);
            intent.putExtra("rap", rap);
            intent.putExtra("ngay", ngay);
            intent.putExtra("gio", gio);
            intent.putExtra("ghe", ghe);

            startActivity(intent);
        });

        updateUI();
    }

    private void updateUI() {
        txtSLBap.setText(String.valueOf(slBapCaramel));
        txtSLPhoMai.setText(String.valueOf(slBapPhoMai));
        txtSLPepsi.setText(String.valueOf(slPepsi));
        txtSLCombo.setText(String.valueOf(slCombo));

        tongTien = (slBapCaramel * giaBap) +
                (slBapPhoMai * giaPhoMai) +
                (slPepsi * giaPepsi) +
                (slCombo * giaCombo);

        txtTongTien.setText("Tổng tiền: " + (tongTien + seatCost) + "đ");
    }
}
