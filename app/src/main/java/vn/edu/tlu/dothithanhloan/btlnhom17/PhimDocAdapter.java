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

        // Nút "Đặt vé"
        holder.btnDatVe.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChonRapSuatActivity.class);
            intent.putExtra("tenPhim", phim.tenPhim);
            intent.putExtra("ngayChieu", phim.ngayKhoiChieu);
            context.startActivity(intent);
        });

        // Nút "Xem chi tiết"
        holder.btnChiTiet.setOnClickListener(v -> {
            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra("tenPhim", phim.tenPhim);
            intent.putExtra("thoiLuong", phim.thoiLuong);
            intent.putExtra("theLoai", phim.theLoai);
            intent.putExtra("ngayKhoiChieu", phim.ngayKhoiChieu);
            intent.putExtra("coDatVe", true); // ✅ Cho đặt vé

            intent.putExtra("posterResId", phim.posterResId); // nếu muốn hiển thị ảnh
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
        Button btnDatVe, btnChiTiet; // ✅ Thêm btnChiTiet ở đây

        public PhimViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster    = itemView.findViewById(R.id.imgPoster);
            txtTenPhim   = itemView.findViewById(R.id.txtTenPhim);
            txtThongTin  = itemView.findViewById(R.id.txtThongTin);
            btnDatVe     = itemView.findViewById(R.id.btnDatVe);
            btnChiTiet   = itemView.findViewById(R.id.btnChiTiet); // ✅ Gán nút
        }
    }
}
