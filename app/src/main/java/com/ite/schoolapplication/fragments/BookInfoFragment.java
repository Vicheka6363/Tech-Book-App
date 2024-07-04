package com.ite.schoolapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ite.schoolapplication.R;
import com.ite.schoolapplication.database.RequestBookDatabaseHelper;

public class BookInfoFragment extends Fragment {

    private EditText title_input, author_input, published_date_input, reason_input;
    private Button add_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_info_fragment, container, false);

        title_input = view.findViewById(R.id.title_input);
        author_input = view.findViewById(R.id.author_input);
        published_date_input = view.findViewById(R.id.published_date_input);
        reason_input = view.findViewById(R.id.reason_input);
        add_button = view.findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = title_input.getText().toString().trim();
                String author = author_input.getText().toString().trim();
                String publishedDate = published_date_input.getText().toString().trim();
                String reason = reason_input.getText().toString().trim();

                if (title.isEmpty() || author.isEmpty() || publishedDate.isEmpty() || reason.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                RequestBookDatabaseHelper myDB = new RequestBookDatabaseHelper(getActivity());
                myDB.addRequestBook(title, author, publishedDate, "", reason);
            }
        });

        return view;
    }
}
