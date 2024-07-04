package com.ite.schoolapplication.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ite.schoolapplication.R;
import com.ite.schoolapplication.adapters.BookAdapter;
import com.ite.schoolapplication.database.DBHelper;
import com.ite.schoolapplication.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookMainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_book);

        dbHelper = new DBHelper(this);

        recyclerView = findViewById(R.id.recyclerViewBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadBooks();
    }

    private void loadBooks() {
        List<Book> books = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("books", null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Book book = new Book(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("author")),
                        cursor.getString(cursor.getColumnIndex("description")),
                        cursor.getString(cursor.getColumnIndex("published_date")),
                        cursor.getString(cursor.getColumnIndex("category")),
                        cursor.getBlob(cursor.getColumnIndex("image")),
                        cursor.getBlob(cursor.getColumnIndex("pdf"))
                );
                books.add(book);
            }
            cursor.close();
        }

        bookAdapter = new BookAdapter(books);
        recyclerView.setAdapter(bookAdapter);
    }

}
