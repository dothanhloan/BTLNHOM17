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
import java.util.Arrays;
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
        danhSachPhim.add(new Phim(R.drawable.phim_godzilla, "Godzilla x Kong: The New Empire", "115", "26/07/2024", "Hành động"));
        danhSachPhim.add(new Phim(R.drawable.phim_doraemon, "Doraemon", "107", "20/06/2024", "Hoạt hình"));
        danhSachPhim.add(new Phim(R.drawable.phim_batman, "The Batman", "176", "15/07/2024", "Hành động, Hình sự"));
        danhSachPhim.add(new Phim(R.drawable.phim_marvels, "The Marvels", "105", "10/08/2024", "Siêu anh hùng"));
        danhSachPhim.add(new Phim(R.drawable.phim_meg2, "The Meg 2: The Trench", "116", "12/06/2024", "Kinh dị"));
        danhSachPhim.add(new Phim(R.drawable.phim_insideout2, "Inside Out 2", "90", "21/06/2024", "Hoạt hình, Gia đình"));
        danhSachPhim.add(new Phim(R.drawable.phim_spiderman, "Spider-Man: No Way Home", "148", "30/06/2024", "Hành động, Viễn tưởng"));
        danhSachPhim.add(new Phim(R.drawable.phim_elemental, "Elemental", "109", "05/07/2024", "Hoạt hình, Tình cảm"));
        danhSachPhim.add(new Phim(R.drawable.phim_haunting, "The Haunting in Venice", "103", "18/07/2024", "Hình sự, Kinh dị"));
        danhSachPhim.add(new Phim(R.drawable.phim_avatar2, "Avatar: The Way of Water", "192", "28/06/2024", "Viễn tưởng, Phiêu lưu"));

        List<Rap> rapList = Arrays.asList(
                new Rap(R.drawable.ic_cgv, "CGV Vincom", "84k"),
                new Rap(R.drawable.ic_lotte, "Lotte Landmark", "75k"),
                new Rap(R.drawable.ic_beta, "Beta Mỹ Đình", "60k"),
                new Rap(R.drawable.ic_bhd, "BHD Star Bitexco", "90k")
        );


        PhimDocAdapter adapter = new PhimDocAdapter(this, danhSachPhim);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
