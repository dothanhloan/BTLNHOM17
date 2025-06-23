package vn.edu.tlu.dothithanhloan.btlnhom17.admin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vn.edu.tlu.dothithanhloan.btlnhom17.DatabaseHelper; // Import DatabaseHelper
import vn.edu.tlu.dothithanhloan.btlnhom17.R; // Import lớp R
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Movie; // Import lớp Movie
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Room; // Import lớp Room
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Screening; // Import lớp Screening

public class AddEditScreeningActivity extends AppCompatActivity {

    private TextView tvAddEditScreeningTitle, tvSelectedDateTime;
    private Spinner spinnerScreeningMovie, spinnerScreeningRoom;
    private Button btnPickDate, btnPickTime, btnSaveScreening;


    private DatabaseHelper db;
    private int screeningId = -1; // -1 nếu là thêm mới, ID lịch chiếu nếu là sửa

    private List<Movie> movieList;
    private List<Room> roomList;
    private ArrayAdapter<Movie> movieAdapter;
    private ArrayAdapter<Room> roomAdapter;

    private Calendar selectedDateTime; // Dùng để lưu trữ ngày giờ đã chọn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_screening);

        db = new DatabaseHelper(this);

        // Ánh xạ các thành phần giao diện
        tvAddEditScreeningTitle = findViewById(R.id.tvAddEditScreeningTitle);
        tvSelectedDateTime = findViewById(R.id.tvSelectedDateTime);
        spinnerScreeningMovie = findViewById(R.id.spinnerScreeningMovie);
        spinnerScreeningRoom = findViewById(R.id.spinnerScreeningRoom);
        btnPickDate = findViewById(R.id.btnPickDate);
        btnPickTime = findViewById(R.id.btnPickTime);
        btnSaveScreening = findViewById(R.id.btnSaveScreening);

        movieList = new ArrayList<>();
        movieAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, movieList);
        movieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerScreeningMovie.setAdapter(movieAdapter);

        roomList = new ArrayList<>();
        roomAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomList);
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerScreeningRoom.setAdapter(roomAdapter);

        selectedDateTime = Calendar.getInstance(); // Khởi tạo với thời gian hiện tại

        loadMoviesIntoSpinner(); // Tải danh sách phim vào Spinner
        loadRoomsIntoSpinner(); // Tải danh sách phòng vào Spinner

        // Xử lý chọn ngày giờ
        btnPickDate.setOnClickListener(v -> showDatePickerDialog());
        btnPickTime.setOnClickListener(v -> showTimePickerDialog());

        // Kiểm tra chế độ "thêm" hay "sửa"
        if (getIntent().hasExtra("screening_id")) {
            screeningId = getIntent().getIntExtra("screening_id", -1);
            tvAddEditScreeningTitle.setText("Sửa Lịch Chiếu");
            loadScreeningData(screeningId);
        } else {
            tvAddEditScreeningTitle.setText("Thêm Lịch Chiếu Mới");
            updateDateTimeDisplay(); // Cập nhật hiển thị ngày giờ ban đầu
        }

        btnSaveScreening.setOnClickListener(v -> saveScreening());
    }

    private void loadMoviesIntoSpinner() {
        movieList.clear(); // Xóa dữ liệu cũ trong danh sách
        Cursor cursor = db.getAllMovies(); // Lấy tất cả phim từ DB

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_NAME));
                // Thêm đối tượng Movie vào danh sách.
                // Đảm bảo Movie class có constructor (int id, String name, ...)
                // hoặc constructor mà bạn đã định nghĩa và điền đủ null/0 cho các trường không dùng tới ở đây
                movieList.add(new Movie(id, name, null, null, null, null, null, 0));
            } while (cursor.moveToNext());
            cursor.close();
        }
        // GỌI notifyDataSetChanged SAU KHI DANH SÁCH ĐƯỢC CẬP NHẬT
        movieAdapter.notifyDataSetChanged(); // <--- Đảm bảo dòng này có ở đây

        if (movieList.isEmpty()) {
            // Đây là phần sẽ chạy nếu không có phim nào trong DB
            Toast.makeText(this, "Chưa có phim nào. Vui lòng thêm phim trước.", Toast.LENGTH_LONG).show();
            // Nếu bạn đang dùng code thêm mẫu tạm thời, hãy đảm bảo nó nằm trong điều kiện này
            // và sau khi thêm mẫu, gọi lại loadMoviesIntoSpinner() một lần nữa.
        }
    }

    private void loadRoomsIntoSpinner() {
        roomList.clear(); // Xóa dữ liệu cũ trong danh sách
        Cursor cursor = db.getAllRooms(); // Lấy tất cả phòng từ DB

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ROOM_ID));
                String roomNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ROOM_NUMBER));
                roomList.add(new Room(id, roomNumber));
            } while (cursor.moveToNext());
            cursor.close();
        }
        // GỌI notifyDataSetChanged SAU KHI DANH SÁCH ĐƯỢC CẬP NHẬT
        roomAdapter.notifyDataSetChanged(); // <--- Đảm bảo dòng này có ở đây

        if (roomList.isEmpty()) {
            // Đây là phần sẽ chạy nếu không có phòng nào trong DB
            Toast.makeText(this, "Chưa có phòng nào. Vui lòng thêm phòng trước.", Toast.LENGTH_LONG).show();
            // Nếu bạn đang dùng code thêm mẫu tạm thời, hãy đảm bảo nó nằm trong điều kiện này
        }
    }

    private void showDatePickerDialog() {
        int year = selectedDateTime.get(Calendar.YEAR);
        int month = selectedDateTime.get(Calendar.MONTH);
        int day = selectedDateTime.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    selectedDateTime.set(selectedYear, selectedMonth, selectedDay);
                    updateDateTimeDisplay();
                }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        int hour = selectedDateTime.get(Calendar.HOUR_OF_DAY);
        int minute = selectedDateTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, selectedHour, selectedMinute) -> {
                    selectedDateTime.set(Calendar.HOUR_OF_DAY, selectedHour);
                    selectedDateTime.set(Calendar.MINUTE, selectedMinute);
                    updateDateTimeDisplay();
                }, hour, minute, true); // true cho định dạng 24 giờ
        timePickerDialog.show();
    }

    private void updateDateTimeDisplay() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        tvSelectedDateTime.setText("Thời gian đã chọn: " + sdf.format(selectedDateTime.getTime()));
    }

    private void loadScreeningData(int id) {
        Cursor cursor = db.getScreeningById(id);
        if (cursor != null && cursor.moveToFirst()) {
            int movieId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SCREENING_MOVIE_ID));
            int roomId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SCREENING_ROOM_ID));
            String startDateStr = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SCREENING_START_DATE));

            // Chọn đúng phim trên Spinner
            for (int i = 0; i < movieList.size(); i++) {
                if (movieList.get(i).getId() == movieId) {
                    spinnerScreeningMovie.setSelection(i);
                    break;
                }
            }

            // Chọn đúng phòng trên Spinner
            for (int i = 0; i < roomList.size(); i++) {
                if (roomList.get(i).getId() == roomId) {
                    spinnerScreeningRoom.setSelection(i);
                    break;
                }
            }

            // Cập nhật Calendar và hiển thị ngày giờ
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            try {
                Date date = sdf.parse(startDateStr);
                selectedDateTime.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(this, "Lỗi định dạng ngày giờ.", Toast.LENGTH_SHORT).show();
            }
            updateDateTimeDisplay();
            cursor.close();
        }
    }

    private void saveScreening() {
        Movie selectedMovie = (Movie) spinnerScreeningMovie.getSelectedItem();
        Room selectedRoom = (Room) spinnerScreeningRoom.getSelectedItem();

        if (selectedMovie == null || selectedRoom == null) {
            Toast.makeText(this, "Vui lòng chọn phim và phòng.", Toast.LENGTH_SHORT).show();
            return;
        }

        int movieId = selectedMovie.getId();
        int roomId = selectedRoom.getId();
        // Định dạng ngày giờ từ Calendar thành chuỗi để lưu vào DB
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String startDate = sdf.format(selectedDateTime.getTime());

        boolean isSuccess;
        if (screeningId == -1) {
            // Thêm lịch chiếu mới
            isSuccess = db.insertScreening(movieId, roomId, startDate);
            if (isSuccess) {
                Toast.makeText(this, "Thêm lịch chiếu mới thành công!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "Thêm lịch chiếu mới thất bại.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Sửa lịch chiếu
            isSuccess = db.updateScreening(screeningId, movieId, roomId, startDate);
            if (isSuccess) {
                Toast.makeText(this, "Cập nhật lịch chiếu thành công!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "Cập nhật lịch chiếu thất bại.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}