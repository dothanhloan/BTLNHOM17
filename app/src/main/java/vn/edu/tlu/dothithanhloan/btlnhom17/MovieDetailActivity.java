package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MovieDetailActivity extends AppCompatActivity {

    ImageView imgPoster, btnBack;
    TextView txtTenPhim, txtThongTinPhim, txtMoTaPhim;
    Button btnDatVe;
    YouTubePlayerView youtubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Ánh xạ view
        imgPoster = findViewById(R.id.imgPoster);
        txtTenPhim = findViewById(R.id.txtTenPhim);
        txtThongTinPhim = findViewById(R.id.txtThongTinPhim);
        txtMoTaPhim = findViewById(R.id.txtMoTaPhim);
        btnDatVe = findViewById(R.id.btnDatVe);
        btnBack = findViewById(R.id.btnBack);
        youtubePlayerView = findViewById(R.id.youtubePlayerView);

        // Quản lý vòng đời YouTubePlayerView
        getLifecycle().addObserver(youtubePlayerView);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String tenPhim = intent.getStringExtra("tenPhim");
        String theLoai = intent.getStringExtra("theLoai");
        String thoiLuong = intent.getStringExtra("thoiLuong");
        String ngayKhoiChieu = intent.getStringExtra("ngayKhoiChieu");
        String moTa = intent.getStringExtra("moTa");
        int posterResId = intent.getIntExtra("posterResId", R.drawable.phim_thamtukien);
        String videoId = intent.getStringExtra("videoId"); // ✅ Nhận videoId
        boolean coDatVe = intent.getBooleanExtra("coDatVe", true); // mặc định là true

        if (!coDatVe) {
            btnDatVe.setVisibility(View.GONE);
        } else {
            btnDatVe.setVisibility(View.VISIBLE);
        }
        if (videoId == null || videoId.isEmpty()) {
            videoId = "5PSNL1qE6VY"; // fallback video
        }

        // Phát video YouTube tương ứng
        String finalVideoId = videoId;
        youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(finalVideoId, 0);
            }
        });

        // Gán dữ liệu
        txtTenPhim.setText(tenPhim != null ? tenPhim : "Không rõ tên");
        txtThongTinPhim.setText("Thể loại: " + (theLoai != null ? theLoai : "N/A") +
                "\nThời lượng: " + (thoiLuong != null ? thoiLuong : "N/A") +
                "\nKhởi chiếu: " + (ngayKhoiChieu != null ? ngayKhoiChieu : "N/A"));
        txtMoTaPhim.setText(moTa != null ? moTa : "");
        imgPoster.setImageResource(posterResId);

        // Nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Nút đặt vé
        btnDatVe.setOnClickListener(v -> {
            Intent i = new Intent(this, ChonRapSuatActivity.class);
            i.putExtra("tenPhim", tenPhim); // truyền tiếp tên phim nếu cần
            startActivity(i);
        });
    }
}
