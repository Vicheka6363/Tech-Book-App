package com.ite.schoolapplication.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.ite.schoolapplication.fragments.UpdateBookInfoFragment;
import com.ite.schoolapplication.fragments.UpdateBookURLFragment;

public class UpdateBookRequestAdapter extends FragmentStateAdapter {
    public UpdateBookRequestAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new UpdateBookInfoFragment();
            case 1:
                return new UpdateBookURLFragment();
            default:
                return new UpdateBookInfoFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Two tabs
    }
}