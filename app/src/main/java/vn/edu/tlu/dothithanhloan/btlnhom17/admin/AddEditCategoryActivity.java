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
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Category; // Import lớp Category

public class AddEditCategoryActivity extends AppCompatActivity {

    private EditText edtCategoryName;
    private Button btnSaveCategory;
    private TextView tvAddEditCategoryTitle;

    private DatabaseHelper db;
    private int categoryId = -1; // -1 nếu là thêm mới, ID danh mục nếu là sửa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_category);

        db = new DatabaseHelper(this);

        edtCategoryName = findViewById(R.id.edtCategoryName);
        btnSaveCategory = findViewById(R.id.btnSaveCategory);
        tvAddEditCategoryTitle = findViewById(R.id.tvAddEditCategoryTitle);

        // Kiểm tra xem là chế độ "thêm" hay "sửa"
        if (getIntent().hasExtra("category_id")) {
            categoryId = getIntent().getIntExtra("category_id", -1);
            tvAddEditCategoryTitle.setText("Sửa Danh mục");
            loadCategoryData(categoryId); // Tải dữ liệu danh mục để sửa
        } else {
            tvAddEditCategoryTitle.setText("Thêm Danh mục Mới");
        }

        btnSaveCategory.setOnClickListener(v -> saveCategory());
    }

    private void loadCategoryData(int id) {
        Cursor cursor = db.getCategoryById(id);
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_CATEGORY_NAME));
            edtCategoryName.setText(name);
            cursor.close();
        }
    }

    private void saveCategory() {
        String name = edtCategoryName.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên danh mục.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isSuccess;
        if (categoryId == -1) {
            // Thêm danh mục mới
            isSuccess = db.insertCategory(name);
            if (isSuccess) {
                Toast.makeText(this, "Thêm danh mục mới thành công!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK); // Đặt kết quả OK để ManageCategoriesActivity biết cần cập nhật
                finish();
            } else {
                Toast.makeText(this, "Thêm danh mục mới thất bại.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Sửa danh mục
            isSuccess = db.updateCategory(categoryId, name);
            if (isSuccess) {
                Toast.makeText(this, "Cập nhật danh mục thành công!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK); // Đặt kết quả OK để ManageCategoriesActivity biết cần cập nhật
                finish();
            } else {
                Toast.makeText(this, "Cập nhật danh mục thất bại.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

