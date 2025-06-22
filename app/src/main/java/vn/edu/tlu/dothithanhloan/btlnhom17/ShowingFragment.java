package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup ct, Bundle b) {
        View v = inf.inflate(R.layout.fragment_showing, ct, false);
        RecyclerView rv = v.findViewById(R.id.recyclerViewPhim);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Phim> danhSachPhim = new ArrayList<>(Arrays.asList(
                new Phim(R.drawable.phim_avatar2, "Biệt Đội Siêu Anh Hùng", "126 phút", "20/06/2025", "Hành động"),
                new Phim(R.drawable.phim_marvels, "Vệ Binh Dải Ngân Hà", "118 phút", "15/06/2025", "Viễn tưởng"),
                new Phim(R.drawable.phim_godzilla, "Godzilla x Kong: The New Empire", "115", "26/07/2024", "Hành động")
        ));

// Thêm phần tử sau này bình thường:
        danhSachPhim.add(new Phim(R.drawable.phim_doraemon, "Doraemon", "107", "20/06/2024", "Hoạt hình"));
        danhSachPhim.add(new Phim(R.drawable.phim_batman, "The Batman", "176", "15/07/2024", "Hành động, Hình sự"));
        danhSachPhim.add(new Phim(R.drawable.phim_marvels, "The Marvels", "105", "10/08/2024", "Siêu anh hùng"));
        danhSachPhim.add(new Phim(R.drawable.phim_meg2, "The Meg 2: The Trench", "116", "12/06/2024", "Kinh dị"));
        danhSachPhim.add(new Phim(R.drawable.phim_insideout2, "Inside Out 2", "90", "21/06/2024", "Hoạt hình, Gia đình"));
        danhSachPhim.add(new Phim(R.drawable.phim_spiderman, "Spider-Man: No Way Home", "148", "30/06/2024", "Hành động, Viễn tưởng"));
        danhSachPhim.add(new Phim(R.drawable.phim_elemental, "Elemental", "109", "05/07/2024", "Hoạt hình, Tình cảm"));
        danhSachPhim.add(new Phim(R.drawable.phim_haunting, "The Haunting in Venice", "103", "18/07/2024", "Hình sự, Kinh dị"));
        danhSachPhim.add(new Phim(R.drawable.phim_avatar2, "Avatar: The Way of Water", "192", "28/06/2024", "Viễn tưởng, Phiêu lưu"));



        rv.setAdapter(new PhimDocAdapter(getContext(), danhSachPhim));
        return v;
    }
}
