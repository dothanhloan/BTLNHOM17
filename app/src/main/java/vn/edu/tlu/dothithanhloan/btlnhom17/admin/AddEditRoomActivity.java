package vn.edu.tlu.dothithanhloan.btlnhom17.admin;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.tlu.dothithanhloan.btlnhom17.DatabaseHelper; // Import DatabaseHelper
import vn.edu.tlu.dothithanhloan.btlnhom17.R; // Import lớp R
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Room; // Import lớp Room

public class AddEditRoomActivity extends AppCompatActivity {

    private EditText edtRoomNumber;
    private Button btnSaveRoom;
    private TextView tvAddEditRoomTitle;

    private DatabaseHelper db;
    private int roomId = -1; // -1 nếu là thêm mới, ID phòng nếu là sửa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_room);

        db = new DatabaseHelper(this);

        edtRoomNumber = findViewById(R.id.edtRoomNumber);
        btnSaveRoom = findViewById(R.id.btnSaveRoom);
        tvAddEditRoomTitle = findViewById(R.id.tvAddEditRoomTitle);

        // Kiểm tra xem là chế độ "thêm" hay "sửa"
        if (getIntent().hasExtra("room_id")) {
            roomId = getIntent().getIntExtra("room_id", -1);
            tvAddEditRoomTitle.setText("Sửa Thông Tin Phòng");
            loadRoomData(roomId); // Tải dữ liệu phòng để sửa
        } else {
            tvAddEditRoomTitle.setText("Thêm Phòng Mới");
        }

        btnSaveRoom.setOnClickListener(v -> saveRoom());
    }

    private void loadRoomData(int id) {
        Cursor cursor = db.getRoomById(id);
        if (cursor != null && cursor.moveToFirst()) {
            String roomNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ROOM_NUMBER));
            edtRoomNumber.setText(roomNumber);
            cursor.close();
        }
    }



    private void saveRoom() {
        String roomNumber = edtRoomNumber.getText().toString().trim();

        if (roomNumber.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số phòng.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isSuccess;
        if (roomId == -1) {
            // Thêm phòng mới
            isSuccess = db.insertRoom(roomNumber);
            if (isSuccess) {
                Toast.makeText(this, "Thêm phòng mới thành công!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK); // Đặt kết quả OK để ManageRoomsActivity biết cần cập nhật
                finish();
            } else {
                Toast.makeText(this, "Thêm phòng mới thất bại.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Sửa phòng
            isSuccess = db.updateRoom(roomId, roomNumber);
            if (isSuccess) {
                Toast.makeText(this, "Cập nhật phòng thành công!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK); // Đặt kết quả OK để ManageRoomsActivity biết cần cập nhật
                finish();
            } else {
                Toast.makeText(this, "Cập nhật phòng thất bại.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}