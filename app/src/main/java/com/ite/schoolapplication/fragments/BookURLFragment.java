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

public class BookURLFragment extends Fragment {

    private EditText link_input, reason_input;
    private Button add_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_u_r_l_fragment, container, false);

        link_input = view.findViewById(R.id.link_input);
        reason_input = view.findViewById(R.id.reason_input);
        add_button = view.findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = link_input.getText().toString().trim();
                String reason = reason_input.getText().toString().trim();

                if (link.isEmpty() || reason.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                RequestBookDatabaseHelper myDB = new RequestBookDatabaseHelper(getActivity());
                myDB.addRequestBook("", "", "", link, reason);
            }
        });

        return view;
    }
}
