package com.ite.schoolapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ite.schoolapplication.R;
import com.ite.schoolapplication.adapters.BookRequestAdapter;
import com.ite.schoolapplication.database.RequestBookDatabaseHelper;
import com.ite.schoolapplication.models.BookRequest;

import java.util.ArrayList;

public class ViewBookRequestsFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookRequestAdapter adapter;
    private ArrayList<BookRequest> bookRequests;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_book_requests, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        bookRequests = new ArrayList<>();
        adapter = new BookRequestAdapter(getActivity(), bookRequests);
        recyclerView.setAdapter(adapter);

        loadData();

        return view;
    }

    private void loadData() {
        RequestBookDatabaseHelper dbHelper = new RequestBookDatabaseHelper(getActivity());
        bookRequests.clear();
        bookRequests.addAll(dbHelper.getAllRequests());
        adapter.notifyDataSetChanged();
    }
}
