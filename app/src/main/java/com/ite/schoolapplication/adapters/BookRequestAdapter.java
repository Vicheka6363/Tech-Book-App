package com.ite.schoolapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ite.schoolapplication.activities.UpdateBookRequestActivity;

import com.ite.schoolapplication.R;
import com.ite.schoolapplication.models.BookRequest;

import java.util.ArrayList;

public class BookRequestAdapter extends RecyclerView.Adapter<BookRequestAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BookRequest> bookRequests;

    public BookRequestAdapter(Context context, ArrayList<BookRequest> bookRequests) {
        this.context = context;
        this.bookRequests = bookRequests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookRequest bookRequest = bookRequests.get(position);
        holder.titleTextView.setText(bookRequest.getTitle());
        holder.authorTextView.setText(bookRequest.getAuthor());
        holder.publishedDateTextView.setText(bookRequest.getPublishedDate());
        holder.linkTextView.setText(bookRequest.getLink());
        holder.reasonTextView.setText(bookRequest.getReason());
        holder.itemView.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return bookRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView, authorTextView, publishedDateTextView, linkTextView, reasonTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            authorTextView = itemView.findViewById(R.id.author_text_view);
            publishedDateTextView = itemView.findViewById(R.id.published_date_text_view);
            linkTextView = itemView.findViewById(R.id.link_text_view);
            reasonTextView = itemView.findViewById(R.id.reason_text_view);
        }


        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition(); // Get the clicked item position
            if (position != RecyclerView.NO_POSITION) {
                BookRequest bookRequest = bookRequests.get(position); // Get the book data

                // Create Intent to start UpdateBookRequestActivity
                Intent intent = new Intent(context, UpdateBookRequestActivity.class);
                intent.putExtra("BOOK_ID", bookRequest.getId());
                intent.putExtra("BOOK_TITLE", bookRequest.getTitle());
                intent.putExtra("BOOK_AUTHOR", bookRequest.getAuthor());
                intent.putExtra("BOOK_PUBLISHED_DATE", bookRequest.getPublishedDate());
                intent.putExtra("BOOK_LINK", bookRequest.getLink());
                intent.putExtra("BOOK_REASON", bookRequest.getReason());

                context.startActivity(intent); // Start the activity
            }
        }
    }
}
