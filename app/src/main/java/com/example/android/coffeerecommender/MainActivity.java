package com.example.android.coffeerecommender;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private String coffeeType;
    private String coffeeShop;
    private String flavour;
    private String milk;
    private String temperature;
    private String roast;
    private String origin;
    private Button recButton;

    private View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recommendCoffee();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recButton = findViewById(R.id.btnRecommend);

        recButton.setOnClickListener(buttonOnClickListener);

        // get spinner values
        Spinner spType = findViewById(R.id.spType);
        Spinner spShop = findViewById(R.id.spShop);
        Spinner spFlavour = findViewById(R.id.spFlavour);
        Spinner spMilk = findViewById(R.id.spMilk);
        Spinner spTemperature = findViewById(R.id.spTemperature);
        Spinner spRoast = findViewById(R.id.spRoast);
        Spinner spOrigin = findViewById(R.id.spOrigin);

        // Retrieve the string array from the strings.xml file
        String[] spTypeValues = getResources().getStringArray(R.array.type);
        String[] spShopValues = getResources().getStringArray(R.array.shop);
        String[] spFlavourValues = getResources().getStringArray(R.array.flavour);
        String[] spMilkValues = getResources().getStringArray(R.array.milk);
        String[] spTemperatureValues = getResources().getStringArray(R.array.temperature);
        String[] spRoastValues = getResources().getStringArray(R.array.roast);
        String[] spOriginValues = getResources().getStringArray(R.array.origin);


        // set spinner adapter
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> spTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spTypeValues);
        ArrayAdapter<String> spShopAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spShopValues);
        ArrayAdapter<String> spFlavourAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spFlavourValues);
        ArrayAdapter<String> spMilkAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spMilkValues);
        ArrayAdapter<String> spTemperatureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spTemperatureValues);
        ArrayAdapter<String> spRoastAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spRoastValues);
        ArrayAdapter<String> spOriginAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spOriginValues);

        // Specify the layout to use when the list of choices appears
        spTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spShopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFlavourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMilkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTemperatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRoastAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOriginAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spType.setAdapter(spTypeAdapter);
        spShop.setAdapter(spShopAdapter);
        spFlavour.setAdapter(spFlavourAdapter);
        spMilk.setAdapter(spMilkAdapter);
        spTemperature.setAdapter(spTemperatureAdapter);
        spRoast.setAdapter(spRoastAdapter);
        spOrigin.setAdapter(spOriginAdapter);

        // set text
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coffeeType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spShop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coffeeShop = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spFlavour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flavour = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spMilk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                milk = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spTemperature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temperature = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spRoast.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roast = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spOrigin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                origin = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void recommendCoffee() {

        Button btnRecommend = findViewById(R.id.btnRecommend);

        // Create the Intent object of this class Context() to Second_activity class
        Intent intent = new Intent(getApplicationContext(), Recommendation.class);
        // now by putExtra method put the value in key, value pair key is
        // message_key by this key we will receive the value, and put the string
        intent.putExtra("coffeeType", coffeeType);
        intent.putExtra("coffeeShop", coffeeShop);
        intent.putExtra("flavour", flavour);
        intent.putExtra("milk", milk);
        intent.putExtra("temperature", temperature);
        intent.putExtra("roast", roast);
        intent.putExtra("origin", origin);
        // start the Intent
        startActivity(intent);

    }
}