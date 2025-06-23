package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThanhToanActivity extends AppCompatActivity {

    TextView txtChiTiet;
    Button btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        txtChiTiet = findViewById(R.id.txtChiTiet);
        btnXacNhan = findViewById(R.id.btnXacNhan);

        // Nhận dữ liệu từ Intent
        int tongTien = getIntent().getIntExtra("tongTien", 0);
        String tenPhim = getIntent().getStringExtra("tenPhim");
        String rap = getIntent().getStringExtra("rap");
        String ngay = getIntent().getStringExtra("ngay");
        String gio = getIntent().getStringExtra("gio");

        String ghe = getIntent().getStringExtra("ghe");

        // Kiểm tra null để debug (nếu cần)
        Log.d("THANHTOAN", "tenPhim=" + tenPhim + ", rap=" + rap + ", ngay=" + ngay + ", gio=" + gio + ", ghe=" + ghe + ", tien=" + tongTien);

        // Hiển thị thông tin chi tiết thanh toán
        String chiTiet = "Phim: " + (tenPhim != null ? tenPhim : "N/A") +
                "\nRạp: " + (rap != null ? rap : "N/A") +
                "\nSuất: " + (ngay != null ? ngay : "N/A") + " " + (gio != null ? gio : "N/A") +
                "\nGhế: " + (ghe != null ? ghe : "N/A") +
                "\n\nTổng tiền cần thanh toán: " + tongTien + "đ";

        txtChiTiet.setText(chiTiet);

        btnXacNhan.setOnClickListener(v -> {
            if (tenPhim == null || rap == null || ngay == null || gio == null || ghe == null) {
                Toast.makeText(this, "Dữ liệu chưa đầy đủ. Không thể lưu vé.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lưu vé vào SQLite
            Ve ve = new Ve(tenPhim, rap, ngay, gio, ghe, tongTien);
            VeDatabaseHelper dbHelper = new VeDatabaseHelper(this);
            dbHelper.themVe(ve);

            // Thông báo và quay về màn chính
            Toast.makeText(this, "Hệ thống sẽ xác nhận thanh toán trong vài phút.", Toast.LENGTH_LONG).show();
            v.postDelayed(() -> {
                Intent intent = new Intent(ThanhToanActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }, 3000);
        });
    }
}
