package com.ite.schoolapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.ite.schoolapplication.R;
import com.ite.schoolapplication.adapters.ViewNewBookRequestAdapter;

public class RequestNewBookActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewNewBookRequestAdapter viewNewBookRequestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_new_book);

        tabLayout = findViewById(R.id.tab_requestnewbook_layout);
        viewPager2 = findViewById(R.id.form_requestnewbook);
        viewNewBookRequestAdapter = new ViewNewBookRequestAdapter(this);
        viewPager2.setAdapter(viewNewBookRequestAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });


    }
}
