package com.ite.schoolapplication.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;

import com.ite.schoolapplication.R;
import com.ite.schoolapplication.database.DBHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadBookActivity extends AppCompatActivity {

    private EditText titleEditText, authorEditText, descriptionEditText, dateEditText, categoryEditText;
    private Button selectImageButton, selectPdfButton, saveBookButton;
    private Uri imageUri, pdfUri;

    private DBHelper dbHelper;

    private final ActivityResultLauncher<Intent> selectImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                }
            });

    private final ActivityResultLauncher<Intent> selectPdfLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    pdfUri = result.getData().getData();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_book);

        dbHelper = new DBHelper(this);

        titleEditText = findViewById(R.id.bookTitleEditText);
        authorEditText = findViewById(R.id.bookAuthorEditText);
        descriptionEditText = findViewById(R.id.bookDescriptionEditText);
        dateEditText = findViewById(R.id.bookPublishedDateEditText);
        categoryEditText = findViewById(R.id.bookCategoryEditText);
        selectImageButton = findViewById(R.id.selectImageButton);
        selectPdfButton = findViewById(R.id.selectPdfButton);
        saveBookButton = findViewById(R.id.saveBookButton);

        selectImageButton.setOnClickListener(v -> selectImage());
        selectPdfButton.setOnClickListener(v -> selectPdf());
        saveBookButton.setOnClickListener(v -> saveBook());
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        selectImageLauncher.launch(intent);
    }

    private void selectPdf() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        selectPdfLauncher.launch(intent);
    }

    private void saveBook() {
        String title = titleEditText.getText().toString();
        String author = authorEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String publishedDate = dateEditText.getText().toString();
        String category = categoryEditText.getText().toString();

        byte[] imageData = readFile(imageUri);
        byte[] pdfData = readFile(pdfUri);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("author", author);
        values.put("description", description);
        values.put("published_date", publishedDate);
        values.put("category", category);
        values.put("image", imageData);
        values.put("pdf", pdfData);

        db.insert("books", null, values);
        db.close();
        finish();
    }

    private byte[] readFile(Uri uri) {
        try (InputStream inputStream = getContentResolver().openInputStream(uri);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
