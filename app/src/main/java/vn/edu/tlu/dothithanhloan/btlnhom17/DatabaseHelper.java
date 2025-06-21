package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MovieApp.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Users
        db.execSQL("CREATE TABLE Users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "email TEXT UNIQUE NOT NULL," +
                "password TEXT NOT NULL," +
                "role TEXT DEFAULT 'user')");

        // Thêm 10 tài khoản mẫu
        db.execSQL("INSERT INTO Users (username, email, password, role) VALUES" +
                "('Admin', 'admin@gmail.com', 'admin123', 'admin')," +
                "('Nguyen Van A', 'a@gmail.com', '123456', 'user')," +
                "('Le Thi B', 'b@gmail.com', '654321', 'user')," +
                "('Tran Van C', 'c@gmail.com', 'abc123', 'user')," +
                "('Hoang Thi D', 'd@gmail.com', '999999', 'user')," +
                "('Pham Minh E', 'e@gmail.com', 'phim123', 'user')," +
                "('Nguyen Thi F', 'f@gmail.com', 'matkhau1', 'user')," +
                "('Vo Quoc G', 'g@gmail.com', 'welcome', 'user')," +
                "('Bui Anh H', 'h@gmail.com', 'hello123', 'user')," +
                "('Le Bao Admin', 'admin2@gmail.com', 'admin456', 'admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }

    // Thêm tài khoản mới
    public boolean insertUser(String username, String email, String password, String role) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("username", username);
            cv.put("email", email);
            cv.put("password", password);
            cv.put("role", role);
            long result = db.insert("Users", null, cv);
            return result != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // Kiểm tra đăng nhập
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE email = ? AND password = ?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Lấy vai trò người dùng
    public String getUserRole(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT role FROM Users WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            String role = cursor.getString(0);
            cursor.close();
            return role;
        }
        return null;
    }

    // Kiểm tra email tồn tại (để tránh đăng ký trùng)
    public boolean checkEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}

