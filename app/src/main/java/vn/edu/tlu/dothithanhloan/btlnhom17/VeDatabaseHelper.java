package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class VeDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ve_db";
    private static final int DB_VERSION = 1;

    public VeDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Ve (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenPhim TEXT, " +
                "rap TEXT, " +
                "ngay TEXT, " +
                "gio TEXT, " +
                "ghe TEXT, " +
                "tongTien INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Ve");
        onCreate(db);
    }

    // ✅ Hàm thêm vé vào database
    public void themVe(Ve ve) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenPhim", ve.tenPhim);
        values.put("rap", ve.rap);
        values.put("ngay", ve.ngay);
        values.put("gio", ve.gio);
        values.put("ghe", ve.ghe);
        values.put("tongTien", ve.tongTien);
        db.insert("Ve", null, values);
        db.close();
    }

    // ✅ Lấy danh sách vé từ database
    public List<Ve> getAllVe() {
        List<Ve> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Ve", null);

        if (cursor.moveToFirst()) {
            do {
                String tenPhim = cursor.getString(cursor.getColumnIndexOrThrow("tenPhim"));
                String rap = cursor.getString(cursor.getColumnIndexOrThrow("rap"));
                String ngay = cursor.getString(cursor.getColumnIndexOrThrow("ngay"));
                String gio = cursor.getString(cursor.getColumnIndexOrThrow("gio"));
                String ghe = cursor.getString(cursor.getColumnIndexOrThrow("ghe"));
                int tongTien = cursor.getInt(cursor.getColumnIndexOrThrow("tongTien"));

                list.add(new Ve(tenPhim, rap, ngay, gio, ghe, tongTien));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }
}
