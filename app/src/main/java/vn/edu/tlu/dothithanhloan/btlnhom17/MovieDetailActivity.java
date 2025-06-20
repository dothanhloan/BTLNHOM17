package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailActivity extends AppCompatActivity {

    ImageView imgPoster, btnBack;
    TextView txtTenPhim, txtThongTinPhim, txtMoTaPhim;
    Button btnDatVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Ánh xạ
        imgPoster = findViewById(R.id.imgPoster);
        txtTenPhim = findViewById(R.id.txtTenPhim);
        txtThongTinPhim = findViewById(R.id.txtThongTinPhim);
        txtMoTaPhim = findViewById(R.id.txtMoTaPhim);
        btnDatVe = findViewById(R.id.btnDatVe);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String tenPhim = intent.getStringExtra("tenPhim");
        String thongTin = intent.getStringExtra("thongTin");
        String moTa = intent.getStringExtra("moTa");
        int posterResId = intent.getIntExtra("poster", R.drawable.bap_phomai);

        // Gán lên giao diện
        imgPoster.setImageResource(posterResId);
        txtTenPhim.setText(tenPhim);
        txtThongTinPhim.setText(thongTin);
        txtMoTaPhim.setText(moTa);

        btnBack.setOnClickListener(v -> finish());

        btnDatVe.setOnClickListener(v -> {
            Intent i = new Intent(this, DatVeActivity.class);
            startActivity(i);
        });
    }
}
