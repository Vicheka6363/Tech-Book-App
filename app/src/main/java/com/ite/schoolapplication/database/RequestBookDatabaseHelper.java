package com.ite.schoolapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import android.database.Cursor;
import java.util.ArrayList;
import com.ite.schoolapplication.models.BookRequest;
public class RequestBookDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookRequest.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "book_request";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PUBLISHED_DATE = "published_date";
    private static final String COLUMN_LINK = "book_link";
    private static final String COLUMN_REASON = "reason";

    public RequestBookDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_PUBLISHED_DATE + " TEXT, " +
                COLUMN_LINK + " TEXT, " +
                COLUMN_REASON + " TEXT );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRequestBook(String title, String author, String published_date, String link, String reason) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PUBLISHED_DATE, published_date);
        cv.put(COLUMN_LINK, link);
        cv.put(COLUMN_REASON, reason);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<BookRequest> getAllRequests() {
        ArrayList<BookRequest> bookRequests = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                BookRequest bookRequest = new BookRequest(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PUBLISHED_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LINK)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_REASON))
                );
                bookRequests.add(bookRequest);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bookRequests;
    }
    // Update a book request in the database
    public boolean updateRequest(int id, String title, String author, String publishedDate, String link, String reason) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PUBLISHED_DATE, publishedDate);
        cv.put(COLUMN_LINK, link);
        cv.put(COLUMN_REASON, reason);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{String.valueOf(id)});
        if (result == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
            return false; // Update failed
        } else {
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
            return true; // Update successful
        }
    }

    public boolean deleteRequest(int id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            int result = db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(id)});
            if (result > 0) {
                Log.d("DB Delete", "Successfully deleted book ID: " + id);
                return true;
            } else {
                Log.e("DB Delete", "No rows deleted for book ID: " + id);
                return false;
            }
        } catch (Exception e) {
            Log.e("DB Error", "Error deleting request: " + e.getMessage());
            return false;
        }
    }
}
