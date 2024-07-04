package com.ite.schoolapplication;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.ite.schoolapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        showHome();
        binding.bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.menuHome){
                showHome();
            } else if(menuItem.getItemId() == R.id.menuProvince){
                showProvince();
            } else if(menuItem.getItemId() == R.id.menuSearch){
                showSearch();
            } else if(menuItem.getItemId() == R.id.menuAccount){
                showAccount();
            } else if(menuItem.getItemId() == R.id.menuMore){
                showMore();
            }

            return false;
        });
    }
    private void showHome(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        HomeFragment home = new HomeFragment();
        transaction.replace(binding.mainContent.getId(), home);
        transaction.commit();
    }
    private void showProvince(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ProvinceFragment home = new ProvinceFragment();
        transaction.replace(binding.mainContent.getId(), home);
        transaction.commit();
    }
    private void showSearch(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SearchFragment home = new SearchFragment();
        transaction.replace(binding.mainContent.getId(), home);
        transaction.commit();
    }
    private void showAccount(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        AccountFragment home = new AccountFragment();
        transaction.replace(binding.mainContent.getId(), home);
        transaction.commit();
    }

    private void showMore(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MoreFragment home = new MoreFragment();
        transaction.replace(binding.mainContent.getId(), home);
        transaction.commit();
    }
}