package com.ite.schoolapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ite.schoolapplication.databinding.ActivityProductMainBinding;
import com.google.android.material.navigation.NavigationBarView;
public class ProductMainActivity extends AppCompatActivity {
    private ActivityProductMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        showFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.menuHome){
                showFragment(new HomeFragment());
                return true;
            }
            else if(menuItem.getItemId() == R.id.menuCart) {
                showFragment(new CartFragment());
                return true;
            }
            else if(menuItem.getItemId() == R.id.menuProduct) {
                showFragment(new ProductFragment());
                return true;
            }
            else if(menuItem.getItemId() == R.id.menuAccount){
                showFragment(new AccountFragment());
                return true;
            }
            return false;
        });
    }
    private void showFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(binding.mainContent.getId(),fragment);
        transaction.commit();
    }
}
