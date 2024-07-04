package com.ite.schoolapplication.services;

import com.ite.schoolapplication.models.Product;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/kimsongsao/node-rest-api-with-jwt/main/images/products.json")
    Call<List<Product>> loadProductList();
}
