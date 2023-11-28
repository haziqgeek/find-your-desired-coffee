package com.example.android.coffeerecommender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.coffeerecommender.adapter.RecommendationAdapter;
import com.example.android.coffeerecommender.model.Coffee;
import com.example.android.coffeerecommender.remote.ApiUtils;
import com.example.android.coffeerecommender.remote.CoffeeService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recommendation extends AppCompatActivity {

    CoffeeService coffeeService;
    Context context;
    RecyclerView viewListRecommendation;
    RecommendationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        context = this; // get current activity context

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // get reference to the RecyclerView viewList
        viewListRecommendation = findViewById(R.id.viewListRecommendation);

        // create the get Intent object
        Intent intent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String coffeeType = intent.getStringExtra("coffeeType");
        String coffeeShop = intent.getStringExtra("coffeeShop");
        String flavour = intent.getStringExtra("flavour");
        String milk = intent.getStringExtra("milk");
        String temperature = intent.getStringExtra("temperature");
        String roast = intent.getStringExtra("roast");
        String origin = intent.getStringExtra("origin");

        // get appointment service instance
        coffeeService = ApiUtils.getCoffeeService();

        // execute the call. send the user token when sending the query
        coffeeService.getCoffee(coffeeType, coffeeShop, flavour, milk, temperature, roast, origin).enqueue(new Callback<List<Coffee>>() {
            @Override
            public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
                // for debug purpose
                Log.d("MyApp:", "Response: " + response.raw().toString());

                // token is not valid/expired
                if (response.code() == 401) {
                    displayAlert("Session Invalid");
                }

                // Get list of appointment object from response
                List<Coffee> coffees = response.body();

                // initialize
                if (coffees != null) {
                    adapter = new RecommendationAdapter(context, coffees);

                    // set adapter to the RecyclerView
                    viewListRecommendation.setAdapter(adapter);

                    // set layout to recycler view
                    viewListRecommendation.setLayoutManager(new LinearLayoutManager(context));

                    // add separator between item in the list
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(viewListRecommendation.getContext(),
                            DividerItemDecoration.VERTICAL);
                    viewListRecommendation.addItemDecoration(dividerItemDecoration);
                }
                else {
                    displayAlert("Sorry, we cannot find your ideal coffee. Please try again by selecting other options.");
                }
            }

            @Override
            public void onFailure(Call<List<Coffee>> call, Throwable t) {
                Toast.makeText(context, "Error connecting to the server", Toast.LENGTH_LONG).show();
                Log.e("MyApp:", t.getMessage());
            }
        });
    }

    /**
     * Displaying an alert dialog with a single button
     * @param message - message to be displayed
     */
    public void displayAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}