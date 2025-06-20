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

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.PhimViewHolder> {

    private Context context;
    private List<Phim> listPhim;

    public PhimAdapter(Context context, List<Phim> listPhim) {
        this.context = context;
        this.listPhim = listPhim;
    }

    @NonNull
    @Override
    public PhimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phim, parent, false);
        return new PhimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhimViewHolder holder, int position) {
        Phim phim = listPhim.get(position);

        // Gán dữ liệu
        holder.txtTenPhim.setText(phim.tenPhim);
        holder.txtThongTin.setText("Thể loại: " + phim.theLoai +
                "\nThời lượng: " + phim.thoiLuong + " phút" +
                "\nKhởi chiếu: " + phim.ngayKhoiChieu);
        holder.imgPoster.setImageResource(phim.posterResId);

        // Chuyển sang chi tiết phim
        View.OnClickListener goToDetail = v -> {
            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra("tenPhim", phim.tenPhim);
            intent.putExtra("thongTin", holder.txtThongTin.getText().toString());
            intent.putExtra("poster", phim.posterResId);
            intent.putExtra("moTa", "Đây là mô tả cho phim " + phim.tenPhim);
            context.startActivity(intent);
        };

        holder.itemView.setOnClickListener(goToDetail);
        holder.btnDatVe.setOnClickListener(goToDetail);
    }

    @Override
    public int getItemCount() {
        return listPhim.size();
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
