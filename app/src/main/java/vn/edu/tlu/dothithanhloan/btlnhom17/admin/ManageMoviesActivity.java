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
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Movie; // Import lớp Movie
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Category; // Import lớp Category để lấy tên thể loại


public class ManageMoviesActivity extends AppCompatActivity implements MovieAdapter.OnItemActionListener {

    private RecyclerView recyclerViewMovies;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    private DatabaseHelper db;
    private FloatingActionButton btnAddMovie;

    private static final int REQUEST_CODE_ADD_MOVIE = 1;
    private static final int REQUEST_CODE_EDIT_MOVIE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_movies);

        db = new DatabaseHelper(this); // Khởi tạo DatabaseHelper
        movieList = new ArrayList<>();

        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        movieAdapter = new MovieAdapter(this, movieList, this); // 'this' vì activity implement interface
        recyclerViewMovies.setAdapter(movieAdapter);

        btnAddMovie = findViewById(R.id.btnAddMovie);
        btnAddMovie.setOnClickListener(v -> {
            // Chuyển đến màn hình thêm phim
            Intent intent = new Intent(ManageMoviesActivity.this, AddEditMovieActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_MOVIE);
        });

        loadMoviesFromDb(); // Tải danh sách phim từ DB khi Activity tạo
    }

    // Tải danh sách phim từ database và cập nhật RecyclerView
    private void loadMoviesFromDb() {
        movieList.clear(); // Xóa dữ liệu cũ
        Cursor cursor = db.getAllMovies(); // Lấy tất cả phim

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_NAME));
                String totalTime = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_TOTAL_TIME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_DESCRIPTION));
                String avatar = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_AVATAR));
                String trailer = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_TRAILER));
                String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_RELEASE_DATE));
                int categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOVIE_CATEGORY_ID));

                Movie movie = new Movie(id, name, totalTime, description, avatar, trailer, releaseDate, categoryId);
                movieList.add(movie);
            } while (cursor.moveToNext());
            cursor.close();
        }
        movieAdapter.notifyDataSetChanged(); // Cập nhật adapter
    }

    // Xử lý sự kiện khi click nút "Sửa" trên item
    @Override
    public void onEditClick(int position) {
        Movie movieToEdit = movieList.get(position);
        Intent intent = new Intent(ManageMoviesActivity.this, AddEditMovieActivity.class);
        // Truyền dữ liệu phim cần sửa sang màn hình AddEditMovieActivity
        intent.putExtra("movie_id", movieToEdit.getId());
        intent.putExtra("movie_name", movieToEdit.getName());
        intent.putExtra("movie_total_time", movieToEdit.getTotalTime());
        intent.putExtra("movie_description", movieToEdit.getDescription());
        intent.putExtra("movie_avatar", movieToEdit.getAvatar());
        intent.putExtra("movie_trailer", movieToEdit.getTrailer());
        intent.putExtra("movie_release_date", movieToEdit.getReleaseDate());
        intent.putExtra("movie_category_id", movieToEdit.getCategoryId());
        startActivityForResult(intent, REQUEST_CODE_EDIT_MOVIE);
    }

    // Xử lý sự kiện khi click nút "Xóa" trên item
    @Override
    public void onDeleteClick(int position) {
        Movie movieToDelete = movieList.get(position);
        // Hiển thị dialog xác nhận trước khi xóa (nên làm trong thực tế)
        boolean deleted = db.deleteMovie(movieToDelete.getId());
        if (deleted) {
            Toast.makeText(this, "Đã xóa phim: " + movieToDelete.getName(), Toast.LENGTH_SHORT).show();
            loadMoviesFromDb(); // Tải lại danh sách sau khi xóa
        } else {
            Toast.makeText(this, "Xóa phim thất bại: " + movieToDelete.getName() + ". Có thể do phim này đang được tham chiếu.", Toast.LENGTH_LONG).show();
        }
    }

    // Xử lý kết quả trả về từ AddEditMovieActivity (sau khi thêm/sửa)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADD_MOVIE || requestCode == REQUEST_CODE_EDIT_MOVIE) {
                loadMoviesFromDb(); // Tải lại danh sách phim sau khi thêm/sửa thành công
            }
        }
    }

    // Khi Activity được hiển thị lại (ví dụ: quay lại từ AddEditMovieActivity)
    @Override
    protected void onResume() {
        super.onResume();
        loadMoviesFromDb(); // Đảm bảo dữ liệu được cập nhật mới nhất
    }
}