package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
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

        // Nhận tổng tiền từ màn trước
        int tongTien = getIntent().getIntExtra("tongTien", 0);
        txtChiTiet.setText("Tổng tiền cần thanh toán: " + tongTien + "đ");

        btnXacNhan.setOnClickListener(v -> {
            Toast.makeText(this, "Hệ thống sẽ xác nhận thanh toán trong vài phút.", Toast.LENGTH_LONG).show();

            // Sau 3 giây, chuyển về MainActivity và xoá stack
            v.postDelayed(() -> {
                Intent intent = new Intent(ThanhToanActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }, 3000);
        });
    }
}
