package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhimDocAdapter extends RecyclerView.Adapter<PhimDocAdapter.PhimViewHolder> {

    private List<Phim> phimList;

    public PhimDocAdapter(List<Phim> phimList) {
        this.phimList = phimList;
    }

    @NonNull
    @Override
    public PhimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phim, parent, false);
        return new PhimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhimViewHolder holder, int position) {
        Phim phim = phimList.get(position);
        holder.imgPoster.setImageResource(phim.posterResId);
        holder.txtTenPhim.setText(phim.tenPhim);
        holder.txtThongTin.setText("Thời lượng: " + phim.thoiLuong + " phút\nKhởi chiếu: " + phim.ngayKhoiChieu + "\nThể loại: " + phim.theLoai);
        holder.btnDatVe.setText("Đặt vé");
    }

    @Override
    public int getItemCount() {
        return phimList.size();
    }

    public static class PhimViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView txtTenPhim, txtThongTin;
        Button btnDatVe;

        public PhimViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.imgPoster);
            txtTenPhim = itemView.findViewById(R.id.txtTenPhim);
            txtThongTin = itemView.findViewById(R.id.txtThongTin);
            btnDatVe = itemView.findViewById(R.id.btnDatVe);
        }
    }
}