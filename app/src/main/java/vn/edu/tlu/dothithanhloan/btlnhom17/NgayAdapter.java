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

    public NgayAdapter(List<String> ngayList) {
        this.ngayList = ngayList;
    }

    @NonNull
    @Override
    public NgayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new NgayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NgayViewHolder holder, int position) {
        holder.txtNgay.setText(ngayList.get(position));
    }

    @Override
    public int getItemCount() {
        return ngayList.size();
    }

    public static class NgayViewHolder extends RecyclerView.ViewHolder {
        TextView txtNgay;

        public NgayViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNgay = itemView.findViewById(android.R.id.text1);
        }
    }
}
