package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
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

        // Ánh xạ
        imgPoster = findViewById(R.id.imgPoster);
        txtTenPhim = findViewById(R.id.txtTenPhim);
        txtThongTinPhim = findViewById(R.id.txtThongTinPhim);
        txtMoTaPhim = findViewById(R.id.txtMoTaPhim);
        btnDatVe = findViewById(R.id.btnDatVe);
        btnBack = findViewById(R.id.btnBack);
        youtubePlayerView = findViewById(R.id.youtubePlayerView);

        // Quản lý vòng đời
        getLifecycle().addObserver(youtubePlayerView);

        // Phát video YouTube
        youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String videoId = "5PSNL1qE6VY"; // ID từ link https://www.youtube.com/watch?v=5PSNL1qE6VY
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        // Xử lý sự kiện nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String tenPhim = intent.getStringExtra("tenPhim");
        String thongTin = intent.getStringExtra("thongTin");
        String moTa = intent.getStringExtra("moTa");
        int posterResId = intent.getIntExtra("poster", R.drawable.bap_phomai);

        // Gán dữ liệu lên giao diện
        imgPoster.setImageResource(posterResId);
        txtTenPhim.setText(tenPhim);
        txtThongTinPhim.setText(thongTin);
        txtMoTaPhim.setText(moTa);

        // Nút đặt vé
        //btnDatVe.setOnClickListener(v -> {
            //Intent i = new Intent(this, ChonRapSuatActivity.class);
            //startActivity(i);
        //});
    }
}
