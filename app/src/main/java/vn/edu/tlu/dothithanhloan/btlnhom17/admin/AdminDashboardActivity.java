package vn.edu.tlu.dothithanhloan.btlnhom17.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView; // Thêm import cho TextView
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.tlu.dothithanhloan.btlnhom17.LoginActivity; // Import LoginActivity
import vn.edu.tlu.dothithanhloan.btlnhom17.R;
import vn.edu.tlu.dothithanhloan.btlnhom17.admin.ManageMoviesActivity;
import vn.edu.tlu.dothithanhloan.btlnhom17.admin.ManageCategoriesActivity;
import vn.edu.tlu.dothithanhloan.btlnhom17.admin.ManageServicesActivity;
import vn.edu.tlu.dothithanhloan.btlnhom17.admin.ManageRoomsActivity;
import vn.edu.tlu.dothithanhloan.btlnhom17.admin.ManageScreeningsActivity;
public class AdminDashboardActivity extends AppCompatActivity {

    private Button btnManageMovies;
    private Button btnManageCategories;
    private Button btnManageServices;
    private Button btnLogoutAdmin;
    private Button btnManageRooms;
    private Button btnManageScreenings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Ánh xạ các thành phần giao diện
        btnManageMovies = findViewById(R.id.btnManageMovies);
        btnManageCategories = findViewById(R.id.btnManageCategories);
        btnManageServices = findViewById(R.id.btnManageServices);
        btnManageRooms = findViewById(R.id.btnManageRooms);
        btnManageScreenings = findViewById(R.id.btnManageScreenings);
        btnLogoutAdmin = findViewById(R.id.btnLogoutAdmin);

        // Xử lý sự kiện khi click vào các nút
        btnManageMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn hình quản lý phim (sẽ tạo sau)
                Toast.makeText(AdminDashboardActivity.this, "Chuyển đến màn hình Quản lý Phim", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminDashboardActivity.this, ManageMoviesActivity.class);
                startActivity(intent);
            }
        });

        btnManageCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn hình quản lý danh mục (sẽ tạo sau)
                Toast.makeText(AdminDashboardActivity.this, "Chuyển đến màn hình Quản lý Danh mục", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminDashboardActivity.this, ManageCategoriesActivity.class);
                startActivity(intent);
            }
        });

        btnManageServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn hình quản lý dịch vụ (sẽ tạo sau)
                Toast.makeText(AdminDashboardActivity.this, "Chuyển đến màn hình Quản lý Dịch vụ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminDashboardActivity.this, ManageServicesActivity.class);
                startActivity(intent);
            }
        });
        btnManageRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, ManageRoomsActivity.class);
                startActivity(intent);
            }
        });
        // Nút Quản lý Lịch chiếu
        btnManageScreenings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, ManageScreeningsActivity.class);
                startActivity(intent);
            }
        });

        btnLogoutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý đăng xuất Admin
                Toast.makeText(AdminDashboardActivity.this, "Đăng xuất Admin", Toast.LENGTH_SHORT).show();
                // Chuyển về màn hình đăng nhập (LoginActivity)
                Intent intent = new Intent(AdminDashboardActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Xóa các Activity cũ trong stack
                startActivity(intent);
                finish(); // Kết thúc AdminDashboardActivity
            }
        });
    }
}