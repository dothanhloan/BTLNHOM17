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

public class ComingSoonAdapter extends RecyclerView.Adapter<ComingSoonAdapter.ViewHolder> {

    public interface OnViewClickListener {
        void onViewClick(Phim phim);
    }

    private Context context;
    private List<Phim> phimList;
    private OnViewClickListener listener;

    public ComingSoonAdapter(Context context, List<Phim> phimList, OnViewClickListener listener) {
        this.context = context;
        this.phimList = phimList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_phim_coming_soon, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int pos) {
        Phim phim = phimList.get(pos);
        h.imgPoster.setImageResource(phim.getHinhAnh());
        h.txtTenPhim.setText(phim.getTen());
        h.txtNgayKhoiChieu.setText("Khởi chiếu: " + phim.getKhoiChieu());

        h.btnXem.setOnClickListener(v -> {
            if (listener != null) {
                listener.onViewClick(phim);
            } else {
                // Nếu không truyền listener thì mở MovieDetailActivity trực tiếp
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("tenPhim", phim.getTen());
                intent.putExtra("thongTin", "Thời lượng: " + phim.getThoiLuong()
                        + "\nKhởi chiếu: " + phim.getKhoiChieu()
                        + "\nThể loại: " + phim.getTheLoai());
                intent.putExtra("moTa", phim.getMoTa() != null ? phim.getMoTa() : "Chưa có mô tả.");
                intent.putExtra("poster", phim.getHinhAnh());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phimList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView txtTenPhim, txtNgayKhoiChieu;
        Button btnXem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster        = itemView.findViewById(R.id.imgPoster);
            txtTenPhim       = itemView.findViewById(R.id.txtTenPhim);
            txtNgayKhoiChieu = itemView.findViewById(R.id.txtNgayKhoiChieu);
            btnXem           = itemView.findViewById(R.id.btnXem);
        }
    }
}
