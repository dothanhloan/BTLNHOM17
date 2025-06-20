package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Context;
import android.content.Intent;
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

    private Context context;
    private List<Phim> phimList;

    // Constructor truyền context để sử dụng Intent
    public PhimDocAdapter(Context context, List<Phim> phimList) {
        this.context = context;
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

        // ✅ Xử lý nút "Đặt vé"
        holder.btnDatVe.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChonSuatActivity.class);
            intent.putExtra("tenPhim", phim.tenPhim);
            intent.putExtra("ngayChieu", phim.ngayKhoiChieu);
            context.startActivity(intent);
        });

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
