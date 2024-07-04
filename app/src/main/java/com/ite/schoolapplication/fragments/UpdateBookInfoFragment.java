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

public class UpdateBookInfoFragment extends Fragment {
    private TextInputEditText title_update, author_update, published_date_update, reason_update;
    private Button update_button, delete_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_book_info, container, false);

        title_update = view.findViewById(R.id.title_update);
        author_update = view.findViewById(R.id.author_update);
        published_date_update = view.findViewById(R.id.published_date_update);
        reason_update = view.findViewById(R.id.reason_update);
        update_button = view.findViewById(R.id.update_button);
        delete_button = view.findViewById(R.id.delete_button);

        UpdateBookRequestActivity activity = (UpdateBookRequestActivity) getActivity();
        // Set the text for the EditText fields
        title_update.setText(activity.title);
        author_update.setText(activity.author);
        published_date_update.setText(activity.publishedDate);
        reason_update.setText(activity.reason);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = title_update.getText().toString().trim();
                String author = author_update.getText().toString().trim();
                String publishedDate = published_date_update.getText().toString().trim();
                String reason = reason_update.getText().toString().trim();

                if (title.isEmpty() || author.isEmpty() || publishedDate.isEmpty() || reason.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Now, instead of inserting, call the update method in your activity
                activity.updateBookRequest(title, author, publishedDate, activity.link, reason); // Pass the link from the activity
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