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
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Category; // Import lớp Category

public class ManageCategoriesActivity extends AppCompatActivity implements CategoryAdapter.OnItemActionListener {

    private RecyclerView recyclerViewCategories;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;
    private DatabaseHelper db;
    private FloatingActionButton btnAddCategory;

    private static final int REQUEST_CODE_ADD_CATEGORY = 1;
    private static final int REQUEST_CODE_EDIT_CATEGORY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_categories);

        db = new DatabaseHelper(this);
        categoryList = new ArrayList<>();

        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this));

        categoryAdapter = new CategoryAdapter(this, categoryList, this);
        recyclerViewCategories.setAdapter(categoryAdapter);

        btnAddCategory = findViewById(R.id.btnAddCategory);
        btnAddCategory.setOnClickListener(v -> {
            Intent intent = new Intent(ManageCategoriesActivity.this, AddEditCategoryActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_CATEGORY);
        });

        loadCategoriesFromDb();
    }

    private void loadCategoriesFromDb() {
        categoryList.clear();
        Cursor cursor = db.getAllCategories();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_CATEGORY_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_CATEGORY_NAME));
                Category category = new Category(id, name);
                categoryList.add(category);
            } while (cursor.moveToNext());
            cursor.close();
        }
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditClick(int position) {
        Category categoryToEdit = categoryList.get(position);
        Intent intent = new Intent(ManageCategoriesActivity.this, AddEditCategoryActivity.class);
        intent.putExtra("category_id", categoryToEdit.getId());
        intent.putExtra("category_name", categoryToEdit.getName());
        startActivityForResult(intent, REQUEST_CODE_EDIT_CATEGORY);
    }

    @Override
    public void onDeleteClick(int position) {
        Category categoryToDelete = categoryList.get(position);
        boolean deleted = db.deleteCategory(categoryToDelete.getId());
        if (deleted) {
            Toast.makeText(this, "Đã xóa danh mục: " + categoryToDelete.getName(), Toast.LENGTH_SHORT).show();
            loadCategoriesFromDb();
        } else {
            Toast.makeText(this, "Xóa danh mục thất bại. Có thể có phim đang dùng danh mục này.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADD_CATEGORY || requestCode == REQUEST_CODE_EDIT_CATEGORY) {
                loadCategoriesFromDb();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCategoriesFromDb();
    }
}