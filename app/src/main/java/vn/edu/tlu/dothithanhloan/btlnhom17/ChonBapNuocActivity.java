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

    // Mỗi món gồm: số lượng, giá, TextView hiện số lượng
    int slBapCaramel = 0;
    int slPepsi = 0;
    int giaBap = 30000;
    int giaPepsi = 20000;

    TextView txtSLBap, txtSLPepsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_bap_nuoc);

        txtTongTien = findViewById(R.id.txtTongTien);
        txtSLBap = findViewById(R.id.monBapCaramel).findViewById(R.id.txtSoLuong);
        txtSLPepsi = findViewById(R.id.monPepsi).findViewById(R.id.txtSoLuong);

        Button btnCongBap = findViewById(R.id.monBapCaramel).findViewById(R.id.btnCong);
        Button btnTruBap = findViewById(R.id.monBapCaramel).findViewById(R.id.btnTru);

        Button btnCongPepsi = findViewById(R.id.monPepsi).findViewById(R.id.btnCong);
        Button btnTruPepsi = findViewById(R.id.monPepsi).findViewById(R.id.btnTru);

        // Bắp caramel
        btnCongBap.setOnClickListener(v -> {
            slBapCaramel++;
            updateUI();
        });
        btnTruBap.setOnClickListener(v -> {
            if (slBapCaramel > 0) slBapCaramel--;
            updateUI();
        });

        // Pepsi
        btnCongPepsi.setOnClickListener(v -> {
            slPepsi++;
            updateUI();
        });
        btnTruPepsi.setOnClickListener(v -> {
            if (slPepsi > 0) slPepsi--;
            updateUI();
        });
        ImageView imgBapCaramel = findViewById(R.id.monBapCaramel).findViewById(R.id.imgMon);
        imgBapCaramel.setImageResource(R.drawable.bap_phomai);

        ImageView imgBapPhoMai = findViewById(R.id.monBapPhoMai).findViewById(R.id.imgMon);
        imgBapPhoMai.setImageResource(R.drawable.bap_phomai);

        ImageView imgPepsi = findViewById(R.id.monPepsi).findViewById(R.id.imgMon);
        imgPepsi.setImageResource(R.drawable.pepsi);

        ImageView imgCombo = findViewById(R.id.monCombo).findViewById(R.id.imgMon);
        imgCombo.setImageResource(R.drawable.combo);
        Button btnHoanThanh = findViewById(R.id.btnHoanThanh);
        btnHoanThanh.setOnClickListener(v -> {
            // Quay về màn hình chính (MainActivity hoặc trang home bạn muốn)
            finish(); // Nếu gọi từ Home thì chỉ cần finish()

            // Hoặc nếu cần chuyển rõ ràng:
            Intent intent = new Intent(ChonBapNuocActivity.this, MainActivity.class);
            startActivity(intent);
        });
        updateUI();
    }

    private void updateUI() {
        txtSLBap.setText(String.valueOf(slBapCaramel));
        txtSLPepsi.setText(String.valueOf(slPepsi));

        tongTien = (slBapCaramel * giaBap) + (slPepsi * giaPepsi);
        txtTongTien.setText("Tổng tiền: " + tongTien + "đ");
    }
}
