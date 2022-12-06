package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * The MainActivity class is the controller for the activity_main.xml file
 * This class initializes UI components and handles user interaction events
 * @author Serena Zeng, Jackson Lee
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvChicagoPizza, tvNYPizza;
    private Button btnStoreOrders, btnCart;

    /**
     * Create a MainActivity activity, and load a past saved state of the
     * activity if the activity is being reloaded. Bind listeners to navigate
     * to other pages.
     * @param savedInstanceState  saved past state of activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvChicagoPizza = findViewById(R.id.tvChicagoPizza);
        tvChicagoPizza.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ChicagoActivity.class);
            startActivity(i);
        });
        tvNYPizza = findViewById(R.id.tvNYPizza);
        tvNYPizza.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, NewYorkActivity.class);
            startActivity(i);
        });
        btnStoreOrders = findViewById(R.id.btnStoreOrders);
        btnStoreOrders.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, StoreOrdersActivity.class);
            startActivity(i);
        });
        btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, CartActivity.class);
            startActivity(i);
        });
    }
}