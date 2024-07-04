package com.ite.schoolapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ite.schoolapplication.databinding.FragmentProvinceBinding;

public class ProvinceFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        FragmentProvinceBinding binding = FragmentProvinceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
