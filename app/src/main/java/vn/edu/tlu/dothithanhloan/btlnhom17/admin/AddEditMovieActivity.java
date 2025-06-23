package vn.edu.tlu.dothithanhloan.btlnhom17.admin;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.dothithanhloan.btlnhom17.DatabaseHelper; // Import DatabaseHelper
import vn.edu.tlu.dothithanhloan.btlnhom17.R; // Import lớp R
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Category; // Import lớp Category
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Movie; // Import lớp Movie

public class AddEditMovieActivity extends AppCompatActivity {

    private EditText edtMovieName, edtMovieTotalTime, edtMovieReleaseDate,
            edtMovieAvatar, edtMovieTrailer, edtMovieDescription;
    private Spinner spinnerMovieCategory;
    private Button btnSaveMovie;
    private TextView tvAddEditMovieTitle;

    private DatabaseHelper db;
    private int movieId = -1; // -1 nếu là thêm mới, ID phim nếu là sửa
    private List<Category> categoryList;
    private ArrayAdapter<Category> categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);

        db = new DatabaseHelper(this); // Khởi tạo DatabaseHelper

        // Ánh xạ các thành phần giao diện
        edtMovieName = findViewById(R.id.edtMovieName);
        edtMovieTotalTime = findViewById(R.id.edtMovieTotalTime);
        edtMovieReleaseDate = findViewById(R.id.edtMovieReleaseDate);
        edtMovieAvatar = findViewById(R.id.edtMovieAvatar);
        edtMovieTrailer = findViewById(R.id.edtMovieTrailer);
        edtMovieDescription = findViewById(R.id.edtMovieDescription);
        spinnerMovieCategory = findViewById(R.id.spinnerMovieCategory);
        btnSaveMovie = findViewById(R.id.btnSaveMovie);
        tvAddEditMovieTitle = findViewById(R.id.tvAddEditMovieTitle);

        categoryList = new ArrayList<>();
        categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMovieCategory.setAdapter(categoryAdapter);

        loadCategoriesIntoSpinner(); // Tải danh mục vào Spinner

        // Kiểm tra xem là chế độ "thêm" hay "sửa"
        if (getIntent().hasExtra("movie_id")) {
            movieId = getIntent().getIntExtra("movie_id", -1);
            tvAddEditMovieTitle.setText("Sửa Thông Tin Phim");
            loadMovieData(movieId); // Tải dữ liệu phim để sửa
        } else {
            tvAddEditMovieTitle.setText("Thêm Phim Mới");
        }

        btnSaveMovie.setOnClickListener(v -> saveMovie());
    }

    // Tải danh mục vào Spinner
    private void loadCategoriesIntoSpinner() {
        categoryList.clear();
        Cursor cursor = db.getAllCategories();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_CATEGORY_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_CATEGORY_NAME));
                categoryList.add(new Category(id, name));
            } while (cursor.moveToNext());
            cursor.close();
        }
        categoryAdapter.notifyDataSetChanged();

        // Thêm một số danh mục mẫu nếu database trống
        // Điều này chỉ chạy khi không có thể loại nào trong DB.
        // Trong thực tế, admin sẽ tự thêm thể loại trước.
        if (categoryList.isEmpty()) {
            db.insertCategory("Hành động");
            db.insertCategory("Hoạt hình");
            db.insertCategory("Tình cảm");
            db.insertCategory("Kinh dị");
            db.insertCategory("Khoa học viễn tưởng");
            loadCategoriesIntoSpinner(); // Tải lại sau khi thêm mẫu
            Toast.makeText(this, "Đã thêm một số thể loại mẫu.", Toast.LENGTH_SHORT).show();
        }
    }


    // Tải dữ liệu phim nếu đang ở chế độ sửa
    private void loadMovieData(int id) {
        Cursor cursor = db.getMovieById(id);
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_NAME));
            String totalTime = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_TOTAL_TIME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_DESCRIPTION));
            String avatar = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_AVATAR));
            String trailer = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_TRAILER));
            String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_RELEASE_DATE));
            int categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_CATEGORY_ID));

            edtMovieName.setText(name);
            edtMovieTotalTime.setText(totalTime);
            edtMovieDescription.setText(description);
            edtMovieAvatar.setText(avatar);
            edtMovieTrailer.setText(trailer);
            edtMovieReleaseDate.setText(releaseDate);

            // Chọn đúng thể loại trên Spinner
            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i).getId() == categoryId) {
                    spinnerMovieCategory.setSelection(i);
                    break;
                }
            }
            cursor.close();
        }
    }

    // Lưu phim (Thêm mới hoặc Cập nhật)
    private void saveMovie() {
        String name = edtMovieName.getText().toString().trim();
        String totalTime = edtMovieTotalTime.getText().toString().trim();
        String releaseDate = edtMovieReleaseDate.getText().toString().trim();
        String avatar = edtMovieAvatar.getText().toString().trim();
        String trailer = edtMovieTrailer.getText().toString().trim();
        String description = edtMovieDescription.getText().toString().trim();

        // Lấy Category ID từ Spinner
        Category selectedCategory = (Category) spinnerMovieCategory.getSelectedItem();
        if (selectedCategory == null) {
            Toast.makeText(this, "Vui lòng chọn thể loại phim.", Toast.LENGTH_SHORT).show();
            return;
        }
        int categoryId = selectedCategory.getId();

        if (name.isEmpty() || totalTime.isEmpty() || releaseDate.isEmpty() || avatar.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin phim.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isSuccess;
        if (movieId == -1) {
            // Thêm phim mới
            isSuccess = db.insertMovie(name, totalTime, description, avatar, trailer, releaseDate, categoryId);
            if (isSuccess) {
                Toast.makeText(this, "Thêm phim mới thành công!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK); // Đặt kết quả OK để ManageMoviesActivity biết cần cập nhật
                finish(); // Đóng Activity hiện tại
            } else {
                Toast.makeText(this, "Thêm phim mới thất bại.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Sửa phim
            isSuccess = db.updateMovie(movieId, name, totalTime, description, avatar, trailer, releaseDate, categoryId);
            if (isSuccess) {
                Toast.makeText(this, "Cập nhật phim thành công!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK); // Đặt kết quả OK để ManageMoviesActivity biết cần cập nhật
                finish(); // Đóng Activity hiện tại
            } else {
                Toast.makeText(this, "Cập nhật phim thất bại.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}