package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                String role = db.getUserRole(email);
                Toast.makeText(this, "Đăng nhập thành công (" + role + ")", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        txtDangKy.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }
}

