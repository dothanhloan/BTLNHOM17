package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class ComingSoonFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup ct, Bundle b) {
        View v = inf.inflate(R.layout.fragment_coming_soon, ct, false);
        RecyclerView rv = v.findViewById(R.id.recyclerViewPhim);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Phim> list = Arrays.asList(
                new Phim(R.drawable.phim_godzilla, "Bí kíp luyện rồng", "110 phút", "25/06/2025", "Hoạt hình"),
                new Phim(R.drawable.phim_avatar2, "Avengers: Secret Wars", "140 phút", "28/06/2025", "Hành động")
        );

        rv.setAdapter(new ComingSoonAdapter(getContext(), list, phim -> {
            Intent intent = new Intent(getContext(), MovieDetailActivity.class);
            intent.putExtra("tenPhim", phim.getTen());
            intent.putExtra("thoiLuong", phim.getThoiLuong());
            intent.putExtra("theLoai", phim.getTheLoai());
            intent.putExtra("ngayKhoiChieu", phim.getKhoiChieu());
            intent.putExtra("moTa", phim.getMoTa() != null ? phim.getMoTa() : "Chưa có mô tả.");
            intent.putExtra("posterResId", phim.getHinhAnh());
            intent.putExtra("coDatVe", false);
//            intent.putExtra("videoId", phim.getVideoId()); // nếu có
            startActivity(intent);
        }));


        return v;
    }
}
