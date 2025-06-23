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
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Screening; // Import lớp Screening

public class ManageScreeningsActivity extends AppCompatActivity implements ScreeningAdapter.OnItemActionListener {

    private RecyclerView recyclerViewScreenings;
    private ScreeningAdapter screeningAdapter;
    private List<Screening> screeningList;
    private DatabaseHelper db;
    private FloatingActionButton btnAddScreening;

    private static final int REQUEST_CODE_ADD_SCREENING = 1;
    private static final int REQUEST_CODE_EDIT_SCREENING = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_screenings);

        db = new DatabaseHelper(this);
        screeningList = new ArrayList<>();

        recyclerViewScreenings = findViewById(R.id.recyclerViewScreenings);
        recyclerViewScreenings.setLayoutManager(new LinearLayoutManager(this));

        screeningAdapter = new ScreeningAdapter(this, screeningList, this);
        recyclerViewScreenings.setAdapter(screeningAdapter);

        btnAddScreening = findViewById(R.id.btnAddScreening);
        btnAddScreening.setOnClickListener(v -> {
            Intent intent = new Intent(ManageScreeningsActivity.this, AddEditScreeningActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_SCREENING);
        });

        loadScreeningsFromDb();
    }

    private void loadScreeningsFromDb() {
        screeningList.clear();
        Cursor cursor = db.getAllScreenings(); // Phương thức này trong DB Helper đã có JOIN

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SCREENING_ID));
                // Lấy MovieId và RoomId để truyền khi sửa
                int movieId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SCREENING_MOVIE_ID)); // Cần có trong Cursor
                int roomId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SCREENING_ROOM_ID)); // Cần có trong Cursor

                String startDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SCREENING_START_DATE));
                String movieName = cursor.getString(cursor.getColumnIndexOrThrow("MovieName")); // Tên alias từ JOIN
                String roomNumber = cursor.getString(cursor.getColumnIndexOrThrow("RoomNumber")); // Tên alias từ JOIN

                Screening screening = new Screening(id, movieId, roomId, startDate, movieName, roomNumber);
                screeningList.add(screening);
            } while (cursor.moveToNext());
            cursor.close();
        }
        screeningAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditClick(int position) {
        Screening screeningToEdit = screeningList.get(position);
        Intent intent = new Intent(ManageScreeningsActivity.this, AddEditScreeningActivity.class);
        intent.putExtra("screening_id", screeningToEdit.getId());
        intent.putExtra("movie_id", screeningToEdit.getMovieId()); // Truyền ID phim
        intent.putExtra("room_id", screeningToEdit.getRoomId());   // Truyền ID phòng
        intent.putExtra("start_date", screeningToEdit.getStartDate()); // Truyền ngày giờ dạng chuỗi
        startActivityForResult(intent, REQUEST_CODE_EDIT_SCREENING);
    }

    @Override
    public void onDeleteClick(int position) {
        Screening screeningToDelete = screeningList.get(position);
        boolean deleted = db.deleteScreening(screeningToDelete.getId());
        if (deleted) {
            Toast.makeText(this, "Đã xóa lịch chiếu của phim " + screeningToDelete.getMovieName() + " tại phòng " + screeningToDelete.getRoomNumber(), Toast.LENGTH_LONG).show();
            loadScreeningsFromDb();
        } else {
            Toast.makeText(this, "Xóa lịch chiếu thất bại.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADD_SCREENING || requestCode == REQUEST_CODE_EDIT_SCREENING) {
                loadScreeningsFromDb();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadScreeningsFromDb();
    }
}