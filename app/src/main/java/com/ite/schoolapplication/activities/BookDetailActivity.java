package com.ite.schoolapplication.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.ite.schoolapplication.database.DBHelper;

import com.ite.schoolapplication.R;

public class BookDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleTextView, authorTextView, descriptionTextView, publishedDateTextView;
    private Button saveToFavoritesButton, downloadButton, readButton;

    private DBHelper dbHelper;
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        dbHelper = new DBHelper(this);

        imageView = findViewById(R.id.bookImage);
        titleTextView = findViewById(R.id.bookTitleTextView);
        authorTextView = findViewById(R.id.bookAuthorTextView);
        descriptionTextView = findViewById(R.id.bookDescriptionTextView);
        publishedDateTextView = findViewById(R.id.bookPublishedDateTextView);
        saveToFavoritesButton = findViewById(R.id.saveToFavoritesButton);
        downloadButton = findViewById(R.id.downloadButton);
        readButton = findViewById(R.id.readButton);

        bookId = getIntent().getIntExtra("book_id", -1);

        loadBookDetails();
    }

    private void loadBookDetails() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("books", null, "id=?", new String[]{String.valueOf(bookId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String author = cursor.getString(cursor.getColumnIndex("author"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String publishedDate = cursor.getString(cursor.getColumnIndex("published_date"));
            byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
            byte[] pdf = cursor.getBlob(cursor.getColumnIndex("pdf"));

            titleTextView.setText(title);
            authorTextView.setText(author);
            descriptionTextView.setText(description);
            publishedDateTextView.setText(publishedDate);

            if (image != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageView.setImageBitmap(bitmap);
            }

            saveToFavoritesButton.setOnClickListener(v -> saveToFavorites());
            downloadButton.setOnClickListener(v -> downloadPdf(pdf));
            readButton.setOnClickListener(v -> readPdf(pdf));

            cursor.close();
        }
    }

    private void saveToFavorites() {
        // Implement save to favorites functionality
    }

    private void downloadPdf(byte[] pdf) {
        // Implement PDF download functionality
    }

    private void readPdf(byte[] pdf) {
        // Implement PDF read functionality
    }
}

