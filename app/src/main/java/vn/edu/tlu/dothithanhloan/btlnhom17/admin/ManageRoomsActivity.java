package vn.edu.tlu.dothithanhloan.btlnhom17.admin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.dothithanhloan.btlnhom17.DatabaseHelper; // Import DatabaseHelper
import vn.edu.tlu.dothithanhloan.btlnhom17.R; // Import lớp R
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Room; // Import lớp Room

public class ManageRoomsActivity extends AppCompatActivity implements RoomAdapter.OnItemActionListener {

    private RecyclerView recyclerViewRooms;
    private RoomAdapter roomAdapter;
    private List<Room> roomList;
    private DatabaseHelper db;
    private FloatingActionButton btnAddRoom;

    private static final int REQUEST_CODE_ADD_ROOM = 1;
    private static final int REQUEST_CODE_EDIT_ROOM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_rooms);

        db = new DatabaseHelper(this);
        roomList = new ArrayList<>();

        recyclerViewRooms = findViewById(R.id.recyclerViewRooms);
        recyclerViewRooms.setLayoutManager(new LinearLayoutManager(this));

        roomAdapter = new RoomAdapter(this, roomList, this);
        recyclerViewRooms.setAdapter(roomAdapter);

        btnAddRoom = findViewById(R.id.btnAddRoom);
        btnAddRoom.setOnClickListener(v -> {
            Intent intent = new Intent(ManageRoomsActivity.this, AddEditRoomActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_ROOM);
        });

        loadRoomsFromDb();
    }

    private void loadRoomsFromDb() {
        roomList.clear();
        Cursor cursor = db.getAllRooms();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ROOM_ID));
                String roomNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ROOM_NUMBER));
                Room room = new Room(id, roomNumber);
                roomList.add(room);
            } while (cursor.moveToNext());
            cursor.close();
        }
        roomAdapter.notifyDataSetChanged();

        // Thêm một số phòng mẫu nếu database trống
        if (roomList.isEmpty()) {
            db.insertRoom("P1");
            db.insertRoom("P2");
            db.insertRoom("P VIP 1");
            loadRoomsFromDb(); // Tải lại sau khi thêm mẫu
            Toast.makeText(this, "Đã thêm một số phòng mẫu.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onEditClick(int position) {
        Room roomToEdit = roomList.get(position);
        Intent intent = new Intent(ManageRoomsActivity.this, AddEditRoomActivity.class);
        intent.putExtra("room_id", roomToEdit.getId());
        intent.putExtra("room_number", roomToEdit.getRoomNumber());
        startActivityForResult(intent, REQUEST_CODE_EDIT_ROOM);
    }

    @Override
    public void onDeleteClick(int position) {
        Room roomToDelete = roomList.get(position);
        boolean deleted = db.deleteRoom(roomToDelete.getId());
        if (deleted) {
            Toast.makeText(this, "Đã xóa phòng: " + roomToDelete.getRoomNumber(), Toast.LENGTH_SHORT).show();
            loadRoomsFromDb();
        } else {
            Toast.makeText(this, "Xóa phòng thất bại. Có thể có lịch chiếu đang dùng phòng này.", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADD_ROOM || requestCode == REQUEST_CODE_EDIT_ROOM) {
                loadRoomsFromDb();
            }
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadRoomsFromDb();
    }
}