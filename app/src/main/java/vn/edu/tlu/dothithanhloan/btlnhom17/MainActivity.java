package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Mặc định hiển thị ShowingFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, new ShowingFragment())
                .commit();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_showing) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new ShowingFragment())
                        .commit();
                return true;

            } else if (item.getItemId() == R.id.menu_coming_soon) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new ComingSoonFragment())
                        .commit();
                return true;

            } else if (item.getItemId() == R.id.my_ticket) {
                // ✅ Mở màn hình Vé của tôi
                Intent intent = new Intent(MainActivity.this, VeCuaToiActivity.class);
                startActivity(intent);
                return true;
            }

            return false;
        });
    }
}
