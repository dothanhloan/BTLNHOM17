package vn.edu.tlu.dothithanhloan.btlnhom17;

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
        // Mỗi khi chọn rạp → thêm 1 block vào danh sách đề xuất
        View view = LayoutInflater.from(this).inflate(R.layout.item_rap_dexuat, layoutDeXuat, false);
        TextView txtTen = view.findViewById(R.id.txtTenRap);
        TextView txtSuat = view.findViewById(R.id.txtSuatChieu);

        txtTen.setText(rap.getTen());
        txtSuat.setText("10:00   13:00   18:30   21:45");
        layoutDeXuat.addView(view);
    }
}

