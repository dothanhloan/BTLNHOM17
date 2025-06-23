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
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Service; // Import lớp Service

public class ManageServicesActivity extends AppCompatActivity implements ServiceAdapter.OnItemActionListener {

    private RecyclerView recyclerViewServices;
    private ServiceAdapter serviceAdapter;
    private List<Service> serviceList;
    private DatabaseHelper db;
    private FloatingActionButton btnAddService;

    private static final int REQUEST_CODE_ADD_SERVICE = 1;
    private static final int REQUEST_CODE_EDIT_SERVICE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);

        db = new DatabaseHelper(this);
        serviceList = new ArrayList<>();

        recyclerViewServices = findViewById(R.id.recyclerViewServices);
        recyclerViewServices.setLayoutManager(new LinearLayoutManager(this));

        serviceAdapter = new ServiceAdapter(this, serviceList, this);
        recyclerViewServices.setAdapter(serviceAdapter);

        btnAddService = findViewById(R.id.btnAddService);
        btnAddService.setOnClickListener(v -> {
            Intent intent = new Intent(ManageServicesActivity.this, AddEditServiceActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_SERVICE);
        });

        loadServicesFromDb();
    }

    private void loadServicesFromDb() {
        serviceList.clear();
        Cursor cursor = db.getAllServices();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SERVICE_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SERVICE_NAME));
                String avatar = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SERVICE_AVATAR));
                double priceUnit = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SERVICE_PRICE_UNIT));
                String unit = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SERVICE_UNIT));

                Service service = new Service(id, name, avatar, priceUnit, unit);
                serviceList.add(service);
            } while (cursor.moveToNext());
            cursor.close();
        }
        serviceAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditClick(int position) {
        Service serviceToEdit = serviceList.get(position);
        Intent intent = new Intent(ManageServicesActivity.this, AddEditServiceActivity.class);
        intent.putExtra("service_id", serviceToEdit.getId());
        intent.putExtra("service_name", serviceToEdit.getName());
        intent.putExtra("service_avatar", serviceToEdit.getAvatar());
        intent.putExtra("service_price_unit", serviceToEdit.getPriceUnit());
        intent.putExtra("service_unit", serviceToEdit.getUnit());
        startActivityForResult(intent, REQUEST_CODE_EDIT_SERVICE);
    }

    @Override
    public void onDeleteClick(int position) {
        Service serviceToDelete = serviceList.get(position);
        boolean deleted = db.deleteService(serviceToDelete.getId());
        if (deleted) {
            Toast.makeText(this, "Đã xóa dịch vụ: " + serviceToDelete.getName(), Toast.LENGTH_SHORT).show();
            loadServicesFromDb();
        } else {
            Toast.makeText(this, "Xóa dịch vụ thất bại.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADD_SERVICE || requestCode == REQUEST_CODE_EDIT_SERVICE) {
                loadServicesFromDb();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadServicesFromDb();
    }
}