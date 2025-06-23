package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Context;
import android.view.*;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VeAdapter extends RecyclerView.Adapter<VeAdapter.VeViewHolder> {

    private Context context;
    private List<Ve> veList;

    public VeAdapter(Context context, List<Ve> veList) {
        this.context = context;
        this.veList = veList;
    }

    @NonNull
    @Override
    public VeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_ve, parent, false);
        return new VeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VeViewHolder holder, int position) {
        Ve ve = veList.get(position);
        holder.txtTenPhim.setText(ve.tenPhim);
        holder.txtChiTiet.setText("Rạp: " + ve.rap + "\nSuất: " + ve.getSuatChieu() + "\nGhế: " + ve.ghe);
        holder.txtTien.setText("Tổng tiền: " + ve.tongTien + "đ");
    }

    @Override
    public int getItemCount() {
        return veList.size();
    }

    public static class VeViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenPhim, txtChiTiet, txtTien;

        public VeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenPhim = itemView.findViewById(R.id.txtTenPhim);
            txtChiTiet = itemView.findViewById(R.id.txtChiTiet);
            txtTien    = itemView.findViewById(R.id.txtTien);
        }
    }
}
