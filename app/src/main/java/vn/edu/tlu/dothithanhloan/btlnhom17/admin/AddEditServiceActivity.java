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
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Service; // Import lớp Service

public class AddEditServiceActivity extends AppCompatActivity {

    private EditText edtServiceName, edtServiceAvatar, edtServicePriceUnit, edtServiceUnit;
    private Button btnSaveService;
    private TextView tvAddEditServiceTitle;

    private DatabaseHelper db;
    private int serviceId = -1; // -1 nếu là thêm mới, ID dịch vụ nếu là sửa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_service);

        db = new DatabaseHelper(this);

        edtServiceName = findViewById(R.id.edtServiceName);
        edtServiceAvatar = findViewById(R.id.edtServiceAvatar);
        edtServicePriceUnit = findViewById(R.id.edtServicePriceUnit);
        edtServiceUnit = findViewById(R.id.edtServiceUnit);
        btnSaveService = findViewById(R.id.btnSaveService);
        tvAddEditServiceTitle = findViewById(R.id.tvAddEditServiceTitle);

        // Kiểm tra xem là chế độ "thêm" hay "sửa"
        if (getIntent().hasExtra("service_id")) {
            serviceId = getIntent().getIntExtra("service_id", -1);
            tvAddEditServiceTitle.setText("Sửa Thông Tin Dịch vụ");
            loadServiceData(serviceId); // Tải dữ liệu dịch vụ để sửa
        } else {
            tvAddEditServiceTitle.setText("Thêm Dịch vụ Mới");
        }

        btnSaveService.setOnClickListener(v -> saveService());
    }

    private void loadServiceData(int id) {
        Cursor cursor = db.getServiceById(id);
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SERVICE_NAME));
            String avatar = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SERVICE_AVATAR));
            double priceUnit = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SERVICE_PRICE_UNIT));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SERVICE_UNIT));

            edtServiceName.setText(name);
            edtServiceAvatar.setText(avatar);
            edtServicePriceUnit.setText(String.valueOf(priceUnit)); // Chuyển double sang String
            edtServiceUnit.setText(unit);
            cursor.close();
        }
    }

    private void saveService() {
        String name = edtServiceName.getText().toString().trim();
        String avatar = edtServiceAvatar.getText().toString().trim();
        String priceUnitStr = edtServicePriceUnit.getText().toString().trim();
        String unit = edtServiceUnit.getText().toString().trim();

        if (name.isEmpty() || priceUnitStr.isEmpty() || unit.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin dịch vụ.", Toast.LENGTH_SHORT).show();
            return;
        }

        double priceUnit;
        try {
            priceUnit = Double.parseDouble(priceUnitStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Giá dịch vụ không hợp lệ.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isSuccess;
        if (serviceId == -1) {
            // Thêm dịch vụ mới
            isSuccess = db.insertService(name, avatar, priceUnit, unit);
            if (isSuccess) {
                Toast.makeText(this, "Thêm dịch vụ mới thành công!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK); // Đặt kết quả OK để ManageServicesActivity biết cần cập nhật
                finish();
            } else {
                Toast.makeText(this, "Thêm dịch vụ mới thất bại.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Sửa dịch vụ
            isSuccess = db.updateService(serviceId, name, avatar, priceUnit, unit);
            if (isSuccess) {
                Toast.makeText(this, "Cập nhật dịch vụ thành công!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK); // Đặt kết quả OK để ManageServicesActivity biết cần cập nhật
                finish();
            } else {
                Toast.makeText(this, "Cập nhật dịch vụ thất bại.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}