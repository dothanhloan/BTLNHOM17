package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VeCuaToiActivity extends AppCompatActivity {

    RecyclerView recyclerViewVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ve_cua_toi);

        recyclerViewVe = findViewById(R.id.recyclerViewVe);
        recyclerViewVe.setLayoutManager(new LinearLayoutManager(this));

        // ✅ Lấy dữ liệu từ SQLite
        VeDatabaseHelper dbHelper = new VeDatabaseHelper(this);
        // ✅ Chỉ cần thêm 1 lần để test
        dbHelper.themVe(new Ve("Bí kíp luyện rồng", "BHD Star", "22/06", "18:00", "A1", 150000));

        List<Ve> danhSachVe = dbHelper.getAllVe();

        // ✅ Gán adapter đúng tên
        VeAdapter adapter = new VeAdapter(this, danhSachVe);
        recyclerViewVe.setAdapter(adapter);
    }
}
