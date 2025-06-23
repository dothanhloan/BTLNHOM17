package vn.edu.tlu.dothithanhloan.btlnhom17;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MovieApp.db";
    private static final int DB_VERSION = 3; // <--- ĐÃ SỬA: Tăng phiên bản DB lên 3

    // Constants for Users table
    private static final String TABLE_USERS = "Users";

    // Thêm các hằng số cho bảng Movies
    public static final String TABLE_MOVIES = "Movies";
    public static final String COL_MOVIE_ID = "id";
    public static final String COL_MOVIE_NAME = "Name";
    public static final String COL_MOVIE_TOTAL_TIME = "TotalTime";
    public static final String COL_MOVIE_DESCRIPTION = "Description";
    public static final String COL_MOVIE_AVATAR = "Avatar"; // Path to drawable or URL
    public static final String COL_MOVIE_TRAILER = "Trailer"; // URL
    public static final String COL_MOVIE_RELEASE_DATE = "ReleaseDate";
    public static final String COL_MOVIE_CATEGORY_ID = "CategoryId"; // Foreign Key

    // Thêm các hằng số cho bảng Categories
    public static final String TABLE_CATEGORIES = "Categories";
    public static final String COL_CATEGORY_ID = "id";
    public static final String COL_CATEGORY_NAME = "Name";

    // Thêm các hằng số cho bảng Services
    public static final String TABLE_SERVICES = "Services";
    public static final String COL_SERVICE_ID = "id";
    public static final String COL_SERVICE_NAME = "Name";
    public static final String COL_SERVICE_AVATAR = "Avatar"; // Path to drawable or URL
    public static final String COL_SERVICE_PRICE_UNIT = "PriceUnit"; //
    public static final String COL_SERVICE_UNIT = "Unit"; // e.g., "Túi", "Cốc"

    // Thêm các hằng số cho bảng Rooms
    public static final String TABLE_ROOMS = "Rooms";
    public static final String COL_ROOM_ID = "id";
    public static final String COL_ROOM_NUMBER = "RoomNumber";

    // Thêm các hằng số cho bảng Screenings (Lịch chiếu)
    public static final String TABLE_SCREENINGS = "Screenings";
    public static final String COL_SCREENING_ID = "id";
    public static final String COL_SCREENING_MOVIE_ID = "MovieId"; // Foreign Key to Movies
    public static final String COL_SCREENING_ROOM_ID = "RoomId";   // Foreign Key to Rooms
    public static final String COL_SCREENING_START_DATE = "StartDate"; // Ngày giờ chiếu

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Users
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" +
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

        // Thêm các bảng mới cho chức năng Admin
        // Tạo bảng Categories
        db.execSQL("CREATE TABLE " + TABLE_CATEGORIES + " (" +
                COL_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_CATEGORY_NAME + " TEXT UNIQUE NOT NULL)");

        // Tạo bảng Movies
        db.execSQL("CREATE TABLE " + TABLE_MOVIES + " (" +
                COL_MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_MOVIE_NAME + " TEXT NOT NULL," +
                COL_MOVIE_TOTAL_TIME + " TEXT," +
                COL_MOVIE_DESCRIPTION + " TEXT," +
                COL_MOVIE_AVATAR + " TEXT," +
                COL_MOVIE_TRAILER + " TEXT," +
                COL_MOVIE_RELEASE_DATE + " TEXT," +
                COL_MOVIE_CATEGORY_ID + " INTEGER," +
                "FOREIGN KEY (" + COL_MOVIE_CATEGORY_ID + ") REFERENCES " + TABLE_CATEGORIES + "(" + COL_CATEGORY_ID + ") ON DELETE CASCADE)");

        // Tạo bảng Services
        db.execSQL("CREATE TABLE " + TABLE_SERVICES + " (" +
                COL_SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_SERVICE_NAME + " TEXT NOT NULL," +
                COL_SERVICE_AVATAR + " TEXT," +
                COL_SERVICE_PRICE_UNIT + " REAL NOT NULL," +
                COL_SERVICE_UNIT + " TEXT)");

        // Tạo bảng Rooms
        db.execSQL("CREATE TABLE " + TABLE_ROOMS + " (" +
                COL_ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_ROOM_NUMBER + " TEXT UNIQUE NOT NULL)");

        // Tạo bảng Screenings
        db.execSQL("CREATE TABLE " + TABLE_SCREENINGS + " (" +
                COL_SCREENING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_SCREENING_MOVIE_ID + " INTEGER," +
                COL_SCREENING_ROOM_ID + " INTEGER," +
                COL_SCREENING_START_DATE + " TEXT NOT NULL," +
                "FOREIGN KEY (" + COL_SCREENING_MOVIE_ID + ") REFERENCES " + TABLE_MOVIES + "(" + COL_MOVIE_ID + ") ON DELETE CASCADE," +
                "FOREIGN KEY (" + COL_SCREENING_ROOM_ID + ") REFERENCES " + TABLE_ROOMS + "(" + COL_ROOM_ID + ") ON DELETE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Xóa các bảng cũ để tạo lại với cấu trúc mới (quan trọng khi tăng DB_VERSION)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCREENINGS); // Drop Screenings trước vì nó có khóa ngoại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS); // Drop Users cuối cùng
        onCreate(db);
    }

    //region Existing User Management Methods
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

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE email = ? AND password = ?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

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

    public boolean checkEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    //endregion

    //region New Admin Management Methods
    // --- Phương thức cho bảng Movies ---
    public boolean insertMovie(String name, String totalTime, String description, String avatar, String trailer, String releaseDate, int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_MOVIE_NAME, name);
        cv.put(COL_MOVIE_TOTAL_TIME, totalTime);
        cv.put(COL_MOVIE_DESCRIPTION, description);
        cv.put(COL_MOVIE_AVATAR, avatar);
        cv.put(COL_MOVIE_TRAILER, trailer);
        cv.put(COL_MOVIE_RELEASE_DATE, releaseDate);
        cv.put(COL_MOVIE_CATEGORY_ID, categoryId);
        long result = db.insert(TABLE_MOVIES, null, cv);
        return result != -1;
    }

    public Cursor getAllMovies() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_MOVIES, null);
    }

    public Cursor getMovieById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_MOVIES + " WHERE " + COL_MOVIE_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public boolean updateMovie(int id, String name, String totalTime, String description, String avatar, String trailer, String releaseDate, int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_MOVIE_NAME, name);
        cv.put(COL_MOVIE_TOTAL_TIME, totalTime);
        cv.put(COL_MOVIE_DESCRIPTION, description);
        cv.put(COL_MOVIE_AVATAR, avatar);
        cv.put(COL_MOVIE_TRAILER, trailer);
        cv.put(COL_MOVIE_RELEASE_DATE, releaseDate);
        cv.put(COL_MOVIE_CATEGORY_ID, categoryId);
        int result = db.update(TABLE_MOVIES, cv, COL_MOVIE_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteMovie(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_MOVIES, COL_MOVIE_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    // --- Phương thức cho bảng Categories ---
    public boolean insertCategory(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_CATEGORY_NAME, name);
        long result = db.insert(TABLE_CATEGORIES, null, cv);
        return result != -1;
    }

    public Cursor getAllCategories() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_CATEGORIES, null);
    }

    public Cursor getCategoryById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_CATEGORIES + " WHERE " + COL_CATEGORY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public boolean updateCategory(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_CATEGORY_NAME, name);
        int result = db.update(TABLE_CATEGORIES, cv, COL_CATEGORY_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteCategory(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_CATEGORIES, COL_CATEGORY_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    // --- Phương thức cho bảng Services ---
    public boolean insertService(String name, String avatar, double priceUnit, String unit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_SERVICE_NAME, name);
        cv.put(COL_SERVICE_AVATAR, avatar);
        cv.put(COL_SERVICE_PRICE_UNIT, priceUnit);
        cv.put(COL_SERVICE_UNIT, unit);
        long result = db.insert(TABLE_SERVICES, null, cv);
        return result != -1;
    }

    public Cursor getAllServices() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SERVICES, null);
    }

    public Cursor getServiceById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SERVICES + " WHERE " + COL_SERVICE_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public boolean updateService(int id, String name, String avatar, double priceUnit, String unit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_SERVICE_NAME, name);
        cv.put(COL_SERVICE_AVATAR, avatar);
        cv.put(COL_SERVICE_PRICE_UNIT, priceUnit);
        cv.put(COL_SERVICE_UNIT, unit);
        int result = db.update(TABLE_SERVICES, cv, COL_SERVICE_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteService(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_SERVICES, COL_SERVICE_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    // --- Phương thức cho bảng Rooms ---
    public boolean insertRoom(String roomNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_ROOM_NUMBER, roomNumber);
        long result = db.insert(TABLE_ROOMS, null, cv);
        return result != -1;
    }

    public Cursor getAllRooms() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ROOMS, null);
    }

    public Cursor getRoomById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ROOMS + " WHERE " + COL_ROOM_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public boolean updateRoom(int id, String roomNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_ROOM_NUMBER, roomNumber);
        int result = db.update(TABLE_ROOMS, cv, COL_ROOM_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteRoom(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_ROOMS, COL_ROOM_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    // --- Phương thức cho bảng Screenings ---
    public boolean insertScreening(int movieId, int roomId, String startDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_SCREENING_MOVIE_ID, movieId);
        cv.put(COL_SCREENING_ROOM_ID, roomId);
        cv.put(COL_SCREENING_START_DATE, startDate);
        long result = db.insert(TABLE_SCREENINGS, null, cv);
        return result != -1;
    }

    public Cursor getAllScreenings() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT s." + COL_SCREENING_ID + ", " +
                "s." + COL_SCREENING_START_DATE + ", " +
                "s." + COL_SCREENING_MOVIE_ID + ", " + // Thêm cột này
                "s." + COL_SCREENING_ROOM_ID + ", " +   // Thêm cột này
                "m." + COL_MOVIE_NAME + " AS MovieName, " +
                "r." + COL_ROOM_NUMBER + " AS RoomNumber " +
                "FROM " + TABLE_SCREENINGS + " s " +
                "JOIN " + TABLE_MOVIES + " m ON s." + COL_SCREENING_MOVIE_ID + " = m." + COL_MOVIE_ID + " " +
                "JOIN " + TABLE_ROOMS + " r ON s." + COL_SCREENING_ROOM_ID + " = r." + COL_ROOM_ID;
        return db.rawQuery(query, null);
    }

    public Cursor getScreeningById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT s." + COL_SCREENING_ID + ", " +
                "s." + COL_SCREENING_START_DATE + ", " +
                "s." + COL_SCREENING_MOVIE_ID + ", " + // Thêm cột này
                "s." + COL_SCREENING_ROOM_ID + ", " +  // Thêm cột này
                "m." + COL_MOVIE_NAME + " AS MovieName, " +
                "r." + COL_ROOM_NUMBER + " AS RoomNumber " +
                "FROM " + TABLE_SCREENINGS + " s " +
                "JOIN " + TABLE_MOVIES + " m ON s." + COL_SCREENING_MOVIE_ID + " = m." + COL_MOVIE_ID + " " +
                "JOIN " + TABLE_ROOMS + " r ON s." + COL_SCREENING_ROOM_ID + " = r." + COL_ROOM_ID + " " +
                "WHERE s." + COL_SCREENING_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(id)});
    }
    public boolean updateScreening(int id, int movieId, int roomId, String startDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_SCREENING_MOVIE_ID, movieId);
        cv.put(COL_SCREENING_ROOM_ID, roomId);
        cv.put(COL_SCREENING_START_DATE, startDate);
        int result = db.update(TABLE_SCREENINGS, cv, COL_SCREENING_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteScreening(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_SCREENINGS, COL_SCREENING_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
    //endregion
}