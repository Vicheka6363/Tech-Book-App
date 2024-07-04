package com.ite.schoolapplication.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ite.schoolapplication.fragments.BookInfoFragment;
import com.ite.schoolapplication.fragments.BookURLFragment;
import com.ite.schoolapplication.fragments.ViewBookRequestsFragment;

public class ViewNewBookRequestAdapter extends FragmentStateAdapter {

    public ViewNewBookRequestAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BookInfoFragment();
            case 1:
                return new BookURLFragment();
            case 2:
                return new ViewBookRequestsFragment();
            default:
                return new BookInfoFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
