package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
        setContentView(R.layout.activity_chon_suat); // sua ơ day dung k, dr, ma k phai

        recyclerNgay = findViewById(R.id.recyclerNgay);
        recyclerRap = findViewById(R.id.recyclerRap);
        layoutDeXuat = findViewById(R.id.layoutDeXuat);

        // Danh sách ngày
        List<String> ngayList = Arrays.asList("22/06", "23/06", "24/06", "25/06", "26/06", "27/06");
        recyclerNgay.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerNgay.setAdapter(new NgayAdapter(ngayList));

        // Danh sách rạp (logo + tên + giá)
        List<Rap> rapList = Arrays.asList(
                new Rap(R.drawable.ic_cgv, "CGV", "84k"), // e tao di nhung ma phai co anh , thi tim
                new Rap(R.drawable.ic_lotte, "Lotte Cinema", "65k"),
                new Rap(R.drawable.ic_beta, "Beta Cinemas", "56k"),
                new Rap(R.drawable.ic_bhd, "BHD Star", "Free")
        );
        recyclerRap.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerRap.setAdapter(new RapAdapter(rapList, this::themRapDeXuat));
    }

    private void themRapDeXuat(Rap rap) {
        // Inflate layout
        View view = LayoutInflater.from(this).inflate(R.layout.item_rap_dexuat, layoutDeXuat, false);

        TextView txtTen = view.findViewById(R.id.txtTenRap);
        txtTen.setText(rap.getTen());

        // Lấy các nút suất chiếu
        TextView txtSuat = view.findViewById(R.id.txtSuatChieu);
        txtSuat.setText("10:00   13:00   18:30   21:45");

        // ✅ Lấy các button suất
        View.OnClickListener suatClickListener = v -> {
            String gioChieu = ((TextView) v).getText().toString();
            Intent intent = new Intent(ChonRapSuatActivity.this, ChonGheActivity.class);
            intent.putExtra("rap", rap.getTen());
            intent.putExtra("suat", gioChieu);
            startActivity(intent);
        };

        // ✅ Gán sự kiện cho các nút suất
        view.findViewById(R.id.btnSuat1).setOnClickListener(suatClickListener);
        view.findViewById(R.id.btnSuat2).setOnClickListener(suatClickListener);
        view.findViewById(R.id.btnSuat3).setOnClickListener(suatClickListener);

        // Thêm vào layout
        layoutDeXuat.addView(view);
    }

}

