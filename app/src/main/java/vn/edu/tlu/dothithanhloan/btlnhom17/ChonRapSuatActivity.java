package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class ChonRapSuatActivity extends AppCompatActivity {

    RecyclerView recyclerNgay, recyclerRap;
    LinearLayout layoutDeXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_suat); // layout phải chứa đúng ID

        recyclerNgay = findViewById(R.id.recyclerNgay);
        recyclerRap = findViewById(R.id.recyclerRap);
        layoutDeXuat = findViewById(R.id.layoutDeXuat);

        // Danh sách ngày
        List<String> ngayList = Arrays.asList("22/06", "23/06", "24/06", "25/06", "26/06", "27/06");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerNgay.setLayoutManager(layoutManager);
        recyclerNgay.setAdapter(new NgayAdapter(ngayList, selectedNgay -> {
            // Xử lý khi chọn ngày
            // Ví dụ: hiện toast hoặc lọc danh sách rạp tương ứng
            // Toast.makeText(this, "Chọn ngày: " + selectedNgay, Toast.LENGTH_SHORT).show();
        }));


        // Danh sách rạp
        List<Rap> rapList = Arrays.asList(
                new Rap(R.drawable.ic_cgv, "CGV", "84k"),
                new Rap(R.drawable.ic_lotte, "Lotte Cinema", "65k"),
                new Rap(R.drawable.ic_beta, "Beta Cinemas", "56k"),
                new Rap(R.drawable.ic_bhd, "BHD Star", "Free")
        );
        recyclerRap.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerRap.setAdapter(new RapAdapter(rapList, this::themRapDeXuat));
    }

    private void themRapDeXuat(Rap rap) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_rap_dexuat, layoutDeXuat, false);

        TextView txtTen = view.findViewById(R.id.txtTenRap);
        txtTen.setText(rap.getTen());

        TextView txtSuat = view.findViewById(R.id.txtSuatChieu);
        txtSuat.setText("10:00   13:00   18:30   21:45");

        // Gán sự kiện cho các Button suất
        Button btnSuat1 = view.findViewById(R.id.btnSuat1);
        Button btnSuat2 = view.findViewById(R.id.btnSuat2);
        Button btnSuat3 = view.findViewById(R.id.btnSuat3);

        View.OnClickListener suatClickListener = v -> {
            String gioChieu = ((Button) v).getText().toString();
            Intent intent = new Intent(ChonRapSuatActivity.this, ChonGheActivity.class);
            intent.putExtra("rap", rap.getTen());
            intent.putExtra("suat", gioChieu);
            startActivity(intent);
        };

        btnSuat1.setOnClickListener(suatClickListener);
        btnSuat2.setOnClickListener(suatClickListener);
        btnSuat3.setOnClickListener(suatClickListener);

        layoutDeXuat.addView(view);
    }
}
