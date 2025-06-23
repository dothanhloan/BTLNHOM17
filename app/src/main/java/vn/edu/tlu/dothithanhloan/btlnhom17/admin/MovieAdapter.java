package vn.edu.tlu.dothithanhloan.btlnhom17.admin;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
// import android.widget.Toast; // Chỉ dùng cho debug, có thể bỏ nếu không cần

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.dothithanhloan.btlnhom17.R; // <-- Đảm bảo import R từ gói gốc
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Movie; // <-- Đảm bảo import Movie từ gói model

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> movieList;
    private OnItemActionListener listener; // Interface để xử lý sự kiện sửa/xóa

    // Interface để định nghĩa các hành động khi click Sửa/Xóa
    public interface OnItemActionListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public MovieAdapter(Context context, List<Movie> movieList, OnItemActionListener listener) {
        this.context = context;
        this.movieList = movieList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_admin, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.tvMovieName.setText(movie.getName());
        holder.tvMovieTotalTime.setText("Thời lượng: " + movie.getTotalTime());
        holder.tvMovieReleaseDate.setText("Ngày chiếu: " + movie.getReleaseDate());
        // TODO: Lấy tên thể loại từ Category ID (sẽ cần truy vấn DB trong Activity hoặc truyền vào)
        holder.tvMovieCategory.setText("Thể loại: ID " + movie.getCategoryId()); // Tạm thời hiển thị ID

        // TODO: Hiển thị ảnh Avatar (nếu avatar là đường dẫn/URL, sẽ cần thư viện Glide/Picasso)
        // Hiện tại chỉ set placeholder (ảnh nền của launcher)
        String avatarFileName = movie.getAvatar(); // Lấy tên file ảnh từ đối tượng Movie
        if (avatarFileName != null && !avatarFileName.isEmpty()) {
            // Lấy ID của tài nguyên drawable từ tên file
            int imageResId = context.getResources().getIdentifier(
                    avatarFileName, // Tên file ảnh (ví dụ: "phim_godzilla")
                    "drawable",     // Loại tài nguyên
                    context.getPackageName() // Tên gói ứng dụng (ví dụ: "vn.edu.tlu.dothithanhloan.btlnhom17")
            );

            if (imageResId != 0) { // Nếu tìm thấy ID tài nguyên
                holder.ivMovieAvatar.setImageResource(imageResId);
            } else {
                // Nếu không tìm thấy ảnh theo tên, hiển thị ảnh mặc định
                holder.ivMovieAvatar.setImageResource(R.drawable.ic_launcher_background); // Hoặc một ảnh mặc định khác của bạn
                // Toast.makeText(context, "Không tìm thấy ảnh: " + avatarFileName, Toast.LENGTH_SHORT).show(); // Có thể dùng để debug
            }
        } else {
            // Nếu không có tên ảnh, hiển thị ảnh mặc định
            holder.ivMovieAvatar.setImageResource(R.drawable.ic_launcher_background); // Hoặc một ảnh mặc định khác của bạn
        }

        holder.btnEditMovie.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(position);
            }
        });

        holder.btnDeleteMovie.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovieAvatar;
        TextView tvMovieName, tvMovieCategory, tvMovieReleaseDate, tvMovieTotalTime;
        Button btnEditMovie, btnDeleteMovie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMovieAvatar = itemView.findViewById(R.id.ivMovieAvatar);
            tvMovieName = itemView.findViewById(R.id.tvMovieName);
            tvMovieCategory = itemView.findViewById(R.id.tvMovieCategory);
            tvMovieReleaseDate = itemView.findViewById(R.id.tvMovieReleaseDate);
            tvMovieTotalTime = itemView.findViewById(R.id.tvMovieTotalTime);
            btnEditMovie = itemView.findViewById(R.id.btnEditMovie);
            btnDeleteMovie = itemView.findViewById(R.id.btnDeleteMovie);
        }
    }

    // Phương thức giúp cập nhật dữ liệu cho RecyclerView sau khi thay đổi (thêm/sửa/xóa)
    public void updateMovieList(List<Movie> newList) {
        movieList.clear();
        movieList.addAll(newList);
        notifyDataSetChanged();
    }
}