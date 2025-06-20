package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChonGheActivity extends AppCompatActivity implements GheAdapter.OnGheClickListener {

    TextView txtTongTien, txtGheChon;
    List<String> gheDaChon = new ArrayList<>();
    final int GIA_VE = 80000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_ghe);

        txtTongTien = findViewById(R.id.txtTongTien);
        txtGheChon = findViewById(R.id.txtGheChon);
        RecyclerView recyclerGhe = findViewById(R.id.recyclerGhe);

        // Tạo danh sách ghế mẫu
        List<String> danhSachGhe = new ArrayList<>();
        for (char row = 'A'; row <= 'I'; row++) {
            for (int col = 1; col <= 8; col++) {
                danhSachGhe.add(row + String.valueOf(col));
            }
        }

        recyclerGhe.setLayoutManager(new GridLayoutManager(this, 8));
        recyclerGhe.setAdapter(new GheAdapter(danhSachGhe, this));
        Button btnTiep = findViewById(R.id.btnTiep);
        btnTiep.setOnClickListener(v -> {
            Intent intent = new Intent(ChonGheActivity.this, ChonBapNuocActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onGheClick(String ghe, boolean isSelected) {
        if (isSelected) {
            gheDaChon.add(ghe);
        } else {
            gheDaChon.remove(ghe);
        }
        txtGheChon.setText("Ghế chọn: " + gheDaChon.toString().replace("[", "").replace("]", ""));
        txtTongTien.setText("Giá tiền: " + (gheDaChon.size() * GIA_VE) + "đ");
    }
}
