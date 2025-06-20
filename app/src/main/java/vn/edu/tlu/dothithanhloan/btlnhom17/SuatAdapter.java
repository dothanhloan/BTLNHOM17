package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SuatAdapter extends RecyclerView.Adapter<SuatAdapter.SuatViewHolder> {

    public interface OnSuatClickListener {
        void onSuatClick(String suat);
    }

    private List<String> suatList;
    private OnSuatClickListener listener;

    public SuatAdapter(List<String> suatList, OnSuatClickListener listener) {
        this.suatList = suatList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SuatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new SuatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuatViewHolder holder, int position) {
        String suat = suatList.get(position);
        holder.txtSuat.setText(suat);
        holder.itemView.setOnClickListener(v -> listener.onSuatClick(suat));
    }

    @Override
    public int getItemCount() {
        return suatList.size();
    }

    public static class SuatViewHolder extends RecyclerView.ViewHolder {
        TextView txtSuat;

        public SuatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSuat = itemView.findViewById(android.R.id.text1);
        }
    }
}
