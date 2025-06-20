package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DatVeActivity extends AppCompatActivity {

    TextView txtTenPhim, txtNgay;
    Button btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ve);

        txtTenPhim = findViewById(R.id.txtTenPhim);
        txtNgay = findViewById(R.id.txtNgay);
        btnXacNhan = findViewById(R.id.btnXacNhan);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String tenPhim = intent.getStringExtra("tenPhim");
        String ngayChieu = intent.getStringExtra("ngayChieu");

        txtTenPhim.setText("Phim: " + tenPhim);
        txtNgay.setText("Ngày chiếu: " + ngayChieu);

        btnXacNhan.setOnClickListener(v -> {
            Toast.makeText(this, "Đặt vé thành công!", Toast.LENGTH_SHORT).show();
            finish(); // Đóng màn hình đặt vé
        });
    }
}

