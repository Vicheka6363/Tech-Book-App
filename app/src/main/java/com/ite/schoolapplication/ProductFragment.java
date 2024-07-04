package com.ite.schoolapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ite.schoolapplication.adapters.ProductAdapter;
import com.ite.schoolapplication.databinding.FragmentProductBinding;
import com.ite.schoolapplication.models.Product;
import com.ite.schoolapplication.services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductFragment extends Fragment {
    FragmentProductBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater,container,false);
        showProducts();
        return binding.getRoot();
    }
    private void showProducts() {
        Retrofit retrofitClient = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com").addConverterFactory(GsonConverterFactory.create()).build();
        ApiService service = retrofitClient.create(ApiService.class);
        Call<List<Product>> task = service.loadProductList();
        task.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()) {
                    // Log.v("Data: ", response.body().toString());
                    bindingProduct(response.body());
                }
                else {
                    Toast.makeText(getContext(), "Failed to load product list from server.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable throwable) {
                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void bindingProduct(List<Product> products) {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        binding.recyclerView.setLayoutManager(manager);
        ProductAdapter adapter = new ProductAdapter();
        adapter.submitList(products);
        adapter.setOnClickListener((position, product) -> {
            Toast.makeText(getContext(), product.getTitle(), Toast.LENGTH_SHORT).show();
        });

        binding.recyclerView.setAdapter(adapter);
    }
}
