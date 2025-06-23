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
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Room; // Import lớp Room

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private Context context;
    private List<Room> roomList;
    private OnItemActionListener listener;

    public interface OnItemActionListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }



    public RoomAdapter(Context context, List<Room> roomList, OnItemActionListener listener) {
        this.context = context;
        this.roomList = roomList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room_admin, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.tvRoomNumber.setText(room.getRoomNumber());

        holder.btnEditRoom.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(position);
            }
        });

        holder.btnDeleteRoom.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoomNumber;
        Button btnEditRoom, btnDeleteRoom;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoomNumber = itemView.findViewById(R.id.tvRoomNumber);
            btnEditRoom = itemView.findViewById(R.id.btnEditRoom);
            btnDeleteRoom = itemView.findViewById(R.id.btnDeleteRoom);
        }
    }

    public void updateRoomList(List<Room> newList) {
        roomList.clear();
        roomList.addAll(newList);
        notifyDataSetChanged();
    }
}