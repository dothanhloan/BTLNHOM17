package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RapAdapter extends RecyclerView.Adapter<RapAdapter.RapViewHolder> {

    private List<Rap> rapList;
    private OnRapClickListener listener;

    public interface OnRapClickListener {
        void onRapClick(Rap rap);
    }

    public RapAdapter(List<Rap> rapList, OnRapClickListener listener) {
        this.rapList = rapList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rap, parent, false);
        return new RapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RapViewHolder holder, int position) {
        Rap rap = rapList.get(position);
        holder.imgLogo.setImageResource(rap.getLogoResId());
        holder.txtTen.setText(rap.getTen());
        holder.txtGia.setText(rap.getGia());

        holder.itemView.setOnClickListener(v -> listener.onRapClick(rap));
    }

    @Override
    public int getItemCount() {
        return rapList.size();
    }

    public static class RapViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLogo;
        TextView txtTen, txtGia;

        public RapViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.imgLogo);
            txtTen = itemView.findViewById(R.id.txtTenRap);
            txtGia = itemView.findViewById(R.id.txtGia);
        }
    }
}
