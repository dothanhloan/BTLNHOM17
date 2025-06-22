package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChonGheActivity extends AppCompatActivity implements GheAdapter.OnGheClickListener {

    TextView txtTongTien, txtGheChon;
    List<String> gheDaChon = new ArrayList<>();
    int GIA_VE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GIA_VE = getIntent().getIntExtra("giaVe", 80000); // Lấy từ Intent, mặc định 80k nếu không có

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_ghe);

        txtTongTien = findViewById(R.id.txtTongTien);
        txtGheChon = findViewById(R.id.txtGheChon);
        RecyclerView recyclerGhe = findViewById(R.id.recyclerGhe);

        // Tạo danh sách ghế A1 - I8
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
            if (gheDaChon.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn ít nhất 1 ghế", Toast.LENGTH_SHORT).show();
            } else {
                int seatCost = gheDaChon.size() * GIA_VE;

                // Truyền sang ChonBapNuocActivity
                Intent intent = new Intent(ChonGheActivity.this, ChonBapNuocActivity.class);
                intent.putExtra("seatCost", seatCost); // ✅ truyền tổng tiền ghế
                intent.putExtra("rap", getIntent().getStringExtra("rap"));
                intent.putExtra("suat", getIntent().getStringExtra("suat"));

                startActivity(intent);
            }
        });
    }

    @Override
    public void onGheClick(String ghe, boolean isSelected) {
        if (isSelected) {
            gheDaChon.add(ghe);
        } else {
            gheDaChon.remove(ghe);
        }

        // Hiển thị danh sách ghế chọn và tiền
        txtGheChon.setText("Ghế chọn: " + gheDaChon.toString().replace("[", "").replace("]", ""));
        txtTongTien.setText("Giá tiền: " + (gheDaChon.size() * GIA_VE) + "đ");
    }
}
