package com.ite.schoolapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ite.schoolapplication.databinding.FragmentSearchBinding;
public class SearchFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        FragmentSearchBinding binding =FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
