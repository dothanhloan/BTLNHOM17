package vn.edu.tlu.dothithanhloan.btlnhom17;


import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GheAdapter extends RecyclerView.Adapter<GheAdapter.GheViewHolder> {

    public interface OnGheClickListener {
        void onGheClick(String ghe, boolean isSelected);
    }

    private List<String> gheList;
    private List<String> gheChon = new ArrayList<>();
    private OnGheClickListener listener;

    public GheAdapter(List<String> gheList, OnGheClickListener listener) {
        this.gheList = gheList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setPadding(8, 8, 8, 8);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(R.drawable.bg_ghe_trong);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(14);
        textView.setLayoutParams(new ViewGroup.LayoutParams(120, 120));
        return new GheViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull GheViewHolder holder, int position) {
        String ghe = gheList.get(position);
        holder.textView.setText(ghe);

        boolean isSelected = gheChon.contains(ghe);
        holder.textView.setBackgroundResource(isSelected ? R.drawable.bg_ghe_chon : R.drawable.bg_ghe_trong);

        holder.textView.setOnClickListener(v -> {
            if (gheChon.contains(ghe)) {
                gheChon.remove(ghe);
                listener.onGheClick(ghe, false);
            } else {
                gheChon.add(ghe);
                listener.onGheClick(ghe, true);
            }
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return gheList.size();
    }

    static class GheViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        GheViewHolder(@NonNull TextView itemView) {
            super(itemView);
            this.textView = itemView;
        }
    }
}
