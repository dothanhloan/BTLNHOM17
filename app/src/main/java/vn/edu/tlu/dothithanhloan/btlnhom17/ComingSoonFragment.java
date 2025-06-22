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

import vn.edu.tlu.dothithanhloan.btlnhom17.ComingSoonAdapter;
import vn.edu.tlu.dothithanhloan.btlnhom17.Phim;

public class ComingSoonFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup ct, Bundle b) {
        View v = inf.inflate(R.layout.fragment_coming_soon, ct, false);
        RecyclerView rv = v.findViewById(R.id.recyclerViewPhim);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        // Dùng adapter tương tự nhưng hide button hoặc disable click
        List<Phim> list = Arrays.asList(
                new Phim(R.drawable.phim_godzilla, "Bí kíp luyện rồng", "110 phút", "25/06/2025", "Hoạt hình"),
                new Phim(R.drawable.phim_avatar2, "Avengers: Secret Wars", "140 phút", "28/06/2025", "Hành động")
        );

        rv.setAdapter(new ComingSoonAdapter(getContext(), list, null));

        return v;
    }
}
