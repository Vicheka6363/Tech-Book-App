package com.ite.schoolapplication.activities;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.ite.schoolapplication.R;
import com.ite.schoolapplication.adapters.UpdateBookRequestAdapter;
import com.ite.schoolapplication.database.RequestBookDatabaseHelper;

public class UpdateBookRequestActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    UpdateBookRequestAdapter updateBookRequestAdapter;

    public int bookId;
    public String title;
    public String author;
    public String publishedDate;
    public String link;
    public String reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book_request);

        tabLayout = findViewById(R.id.tab_update_layout);
        viewPager2 = findViewById(R.id.form_update);
        updateBookRequestAdapter = new UpdateBookRequestAdapter(this);
        viewPager2.setAdapter(updateBookRequestAdapter);

        // Get data from Intent
        Intent intent = getIntent();
        bookId = intent.getIntExtra("BOOK_ID", -1);
        title = intent.getStringExtra("BOOK_TITLE");
        author = intent.getStringExtra("BOOK_AUTHOR");
        publishedDate = intent.getStringExtra("BOOK_PUBLISHED_DATE");
        link = intent.getStringExtra("BOOK_LINK");
        reason = intent.getStringExtra("BOOK_REASON");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }

    // Method to handle updates
    public void updateBookRequest(String title, String author, String publishedDate, String link, String reason) {
        RequestBookDatabaseHelper myDB = new RequestBookDatabaseHelper(UpdateBookRequestActivity.this);
        if (myDB.updateRequest(bookId, title, author, publishedDate, link, reason)) {
            Toast.makeText(UpdateBookRequestActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            // You might want to refresh the view or finish this activity after update
        } else {
            Toast.makeText(UpdateBookRequestActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to handle deletions
    public void deleteBookRequest() {
        RequestBookDatabaseHelper myDB = new RequestBookDatabaseHelper(UpdateBookRequestActivity.this);
        Log.d("Delete Request", "Attempting to delete book ID: " + bookId); // Add this log
        if (myDB.deleteRequest(bookId)) {
            Toast.makeText(UpdateBookRequestActivity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
            // Close the UpdateBookRequestActivity or refresh the list
            finish(); // This will close the activity
        } else {
            Toast.makeText(UpdateBookRequestActivity.this, "Delete Failed", Toast.LENGTH_SHORT).show();
        }
    }

}
