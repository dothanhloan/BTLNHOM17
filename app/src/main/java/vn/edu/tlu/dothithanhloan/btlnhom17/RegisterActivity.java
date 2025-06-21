package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnDangKy;
    TextView txtDangNhap;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnDangKy = findViewById(R.id.btnDangKy);
        txtDangNhap = findViewById(R.id.txtDangNhap);

        db = new DatabaseHelper(this);

        btnDangKy.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String username = "user"; // dùng giá trị mặc định

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else if (db.checkEmailExists(email)) {
                Toast.makeText(this, "Email đã được sử dụng", Toast.LENGTH_SHORT).show();
            } else {
                boolean inserted = db.insertUser(username, email, password, "user");
                if (inserted) {
                    Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Lỗi! Không thể đăng ký", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtDangNhap.setOnClickListener(v ->
                startActivity(new Intent(this, LoginActivity.class)));
    }
}
