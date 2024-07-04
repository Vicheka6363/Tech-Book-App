package com.ite.schoolapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ite.schoolapplication.databinding.FragmentHomeBinding;
public class HomeFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        FragmentHomeBinding binding =FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

}
