package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NgayAdapter extends RecyclerView.Adapter<NgayAdapter.NgayViewHolder> {

    private List<String> ngayList;
    private OnNgayClickListener listener;
    private int selectedPosition = -1;

    public interface OnNgayClickListener {
        void onNgayClick(String ngay);
    }

    public NgayAdapter(List<String> ngayList, OnNgayClickListener listener) {
        this.ngayList = ngayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NgayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngay, parent, false);
        return new NgayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NgayViewHolder holder, int position) {
        String ngay = ngayList.get(position);
        holder.txtThu.setText("T" + ((position % 7) + 2)); // T2 -> CN
        holder.txtNgay.setText(ngay);

        // Highlight ngày đang chọn
        if (position == selectedPosition) {
            holder.itemView.setBackgroundResource(R.drawable.bg_ngay_selected);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.bg_ngay_unselected);
        }

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged(); // cập nhật lại giao diện
            if (listener != null) {
                listener.onNgayClick(ngay);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ngayList.size();
    }

    public static class NgayViewHolder extends RecyclerView.ViewHolder {
        TextView txtThu, txtNgay;

        public NgayViewHolder(@NonNull View itemView) {
            super(itemView);
            txtThu = itemView.findViewById(R.id.txtThu);
            txtNgay = itemView.findViewById(R.id.txtNgay);
        }
    }
}
