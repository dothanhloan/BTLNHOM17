package vn.edu.tlu.dothithanhloan.btlnhom17;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import vn.edu.tlu.dothithanhloan.btlnhom17.admin.AdminDashboardActivity; // Import AdminDashboardActivity


public class LoginActivity extends AppCompatActivity {


    EditText edtEmail, edtPassword;
    Button btnDangNhap;
    TextView txtDangKy;


    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        txtDangKy = findViewById(R.id.txtDangKy);


        db = new DatabaseHelper(this);


        btnDangNhap.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();


            if (db.checkUser(email, password)) {
                String role = db.getUserRole(email); // Lấy vai trò người dùng
                // Log.d("LoginActivity", "User role for " + email + ": " + role); // Thêm dòng này để debug


                // Đảm bảo so sánh chuỗi chính xác và không phân biệt hoa thường nếu cần
                if (role != null && role.equalsIgnoreCase("admin")) { // Sử dụng equalsIgnoreCase để so sánh không phân biệt hoa thường
                    Toast.makeText(this, "Đăng nhập thành công với quyền Admin!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else { // Nếu vai trò không phải "admin" (bao gồm cả null hoặc "user")
                    Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            } else {
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });


        txtDangKy.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }
}
