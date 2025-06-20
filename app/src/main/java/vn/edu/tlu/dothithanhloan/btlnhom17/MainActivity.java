package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Gán dữ liệu cho RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPhim);

        List<Phim> danhSachPhim = new ArrayList<>();
        danhSachPhim.add(new Phim(R.drawable.phim_thamtukien, "KING KONG X GODZILLA", "167", "26/07/2024", "Hành động"));
        danhSachPhim.add(new Phim(R.drawable.phim_thamtukien, "KHOA HỌC", "100", "27/06/2024", "Khoa học viễn tưởng"));

        PhimDocAdapter adapter = new PhimDocAdapter(danhSachPhim);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
