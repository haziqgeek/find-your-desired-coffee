package com.example.android.coffeerecommender.remote;

public class ApiUtils {

    // REST API server URL
    public static final String BASE_URL = "https://haziqraji.pythonanywhere.com";

    // return UserService instance
    public static CoffeeService getCoffeeService() {
        return RetrofitClient.getClient(BASE_URL).create(CoffeeService.class);
    }

}