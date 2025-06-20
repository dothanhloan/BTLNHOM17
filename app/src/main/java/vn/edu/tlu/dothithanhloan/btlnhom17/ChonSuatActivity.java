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

        // Danh sách ngày mẫu
        List<String> ngayList = Arrays.asList("Tue 25", "Wed 26", "Thu 27", "Fri 28", "Sat 29", "Sun 30");
        recyclerNgay.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerNgay.setAdapter(new NgayAdapter(ngayList));

        // Danh sách suất mẫu
        List<String> suatList = Arrays.asList("07:50", "09:00", "11:30", "14:15", "16:45", "19:00", "21:15");
        recyclerSuat.setLayoutManager(new LinearLayoutManager(this));
        recyclerSuat.setAdapter(new SuatAdapter(suatList, suat -> {
            Intent intent = new Intent(ChonSuatActivity.this, ChonGheActivity.class);
            intent.putExtra("suat", suat);
            startActivity(intent);
        }));
    }
}
