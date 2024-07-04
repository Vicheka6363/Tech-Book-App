package com.ite.schoolapplication.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ite.schoolapplication.databinding.ViewHolderItemBinding;
import com.ite.schoolapplication.models.Product;
import com.squareup.picasso.Picasso;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    private final ViewHolderItemBinding binding;
    public ProductViewHolder(ViewHolderItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    public void bind(Product product) {
        Picasso.get().load(product.getImageUrl()).into(binding.imageView);
        binding.textPrice.setText(product.getPrice() + "");
        binding.textRating.setText(product.getRating() + "");
        binding.title.setText(product.getTitle());
    }
}
