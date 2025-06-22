package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class ChonSuatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_suat);

        RecyclerView recyclerNgay = findViewById(R.id.recyclerNgay);
        RecyclerView recyclerSuat = findViewById(R.id.recyclerSuat);

        // Ngày chiếu mẫu
        List<String> ngayList = Arrays.asList("22/06", "23/06", "24/06", "25/06");
        recyclerNgay.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerNgay.setAdapter(new NgayAdapter(ngayList, selectedNgay -> {
            // Xử lý khi chọn ngày
            // Ví dụ: hiện toast hoặc lọc danh sách rạp tương ứng
            // Toast.makeText(this, "Chọn ngày: " + selectedNgay, Toast.LENGTH_SHORT).show();
        }));


        // Suất chiếu mẫu
        List<String> suatList = Arrays.asList("10:00", "13:00", "15:30", "18:00", "20:30");
        recyclerSuat.setLayoutManager(new LinearLayoutManager(this));
        recyclerSuat.setAdapter(new SuatAdapter(suatList, suat -> {
            Intent intent = new Intent(ChonSuatActivity.this, ChonGheActivity.class);
            intent.putExtra("suat", suat);
            startActivity(intent);
        }));
    }
}

