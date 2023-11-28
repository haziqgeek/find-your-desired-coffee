package com.example.android.coffeerecommender.remote;


import com.example.android.coffeerecommender.model.Coffee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoffeeService {

    @GET("/")
    Call<List<Coffee>> getCoffee(@Query("Coffee_Type") String coffeeType, @Query("Coffee_Shop") String coffeeShop, @Query("Flavour") String flavour, @Query("Milk_Type") String milkType, @Query("Temperature") String temperature, @Query("Roast_Type") String roastType, @Query("Origin") String origin);

}