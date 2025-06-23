package vn.edu.tlu.dothithanhloan.btlnhom17.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.dothithanhloan.btlnhom17.R; // Import lớp R
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Screening; // Import lớp Screening

public class ScreeningAdapter extends RecyclerView.Adapter<ScreeningAdapter.ScreeningViewHolder> {

    private Context context;
    private List<Screening> screeningList;
    private OnItemActionListener listener;

    public interface OnItemActionListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public ScreeningAdapter(Context context, List<Screening> screeningList, OnItemActionListener listener) {
        this.context = context;
        this.screeningList = screeningList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ScreeningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_screening_admin, parent, false);
        return new ScreeningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScreeningViewHolder holder, int position) {
        Screening screening = screeningList.get(position);
        holder.tvScreeningMovieName.setText("Tên phim: " + screening.getMovieName());
        holder.tvScreeningRoomNumber.setText("Phòng: " + screening.getRoomNumber());
        holder.tvScreeningStartTime.setText("Thời gian chiếu: " + screening.getStartDate());

        holder.btnEditScreening.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(position);
            }
        });

        holder.btnDeleteScreening.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(position);
            }
        });
    }





    @Override
    public int getItemCount() {
        return screeningList.size();
    }

    public static class ScreeningViewHolder extends RecyclerView.ViewHolder {
        TextView tvScreeningMovieName, tvScreeningRoomNumber, tvScreeningStartTime;
        Button btnEditScreening, btnDeleteScreening;

        public ScreeningViewHolder(@NonNull View itemView) {
            super(itemView);
            tvScreeningMovieName = itemView.findViewById(R.id.tvScreeningMovieName);
            tvScreeningRoomNumber = itemView.findViewById(R.id.tvScreeningRoomNumber);
            tvScreeningStartTime = itemView.findViewById(R.id.tvScreeningStartTime);
            btnEditScreening = itemView.findViewById(R.id.btnEditScreening);
            btnDeleteScreening = itemView.findViewById(R.id.btnDeleteScreening);
        }
    }

    public void updateScreeningList(List<Screening> newList) {
        screeningList.clear();
        screeningList.addAll(newList);
        notifyDataSetChanged();
    }
}