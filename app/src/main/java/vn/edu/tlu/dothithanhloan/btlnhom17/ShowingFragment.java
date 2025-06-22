package vn.edu.tlu.dothithanhloan.btlnhom17;

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

public class ShowingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup ct, Bundle b) {
        View v = inf.inflate(R.layout.fragment_showing, ct, false);
        RecyclerView rv = v.findViewById(R.id.recyclerViewPhim);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Phim> list = Arrays.asList(
                new Phim(R.drawable.phim_avatar2, "Biệt Đội Siêu Anh Hùng", "126 phút", "20/06/2025", "Hành động"),
                new Phim(R.drawable.phim_marvels, "Vệ Binh Dải Ngân Hà", "118 phút", "15/06/2025", "Viễn tưởng")
        );


        rv.setAdapter(new PhimDocAdapter(getContext(), list));
        return v;
    }
}
