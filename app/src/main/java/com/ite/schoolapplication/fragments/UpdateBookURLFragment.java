package com.ite.schoolapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import com.ite.schoolapplication.R;
import com.ite.schoolapplication.activities.UpdateBookRequestActivity;

public class UpdateBookURLFragment extends Fragment {

    private TextInputEditText link_update, reason_update;
    private Button update_button, delete_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_book_u_r_l, container, false);

        link_update = view.findViewById(R.id.link_update);
        reason_update = view.findViewById(R.id.reason_update);
        update_button = view.findViewById(R.id.add_button); // Make sure your button ID is correct
        delete_button = view.findViewById(R.id.delete_button);

        UpdateBookRequestActivity activity = (UpdateBookRequestActivity) getActivity();

        // Set the text for the EditText fields
        link_update.setText(activity.link);
        reason_update.setText(activity.reason);


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = link_update.getText().toString().trim();
                String reason = reason_update.getText().toString().trim();

                if (link.isEmpty() || reason.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                activity.updateBookRequest(activity.title, activity.author, activity.publishedDate, link, reason); // Pass the other fields from the activity
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the delete method from the activity
                activity.deleteBookRequest();
            }
        });

        return view;
    }
}
