package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * The PizzaItemActivityClass is the controller for the activity_pizza_item.xml, which is
 * a single pizza in the activity_cart.xml. This class initiates all UI components
 * @author Serena Zeng, Jackson Lee
 */
public class PizzaItemActivity extends AppCompatActivity {

    /**
     * Create PizzaItemActivity activity, and load a past saved state
     * of the activity if the activity is being reloaded.
     * @param savedInstanceState    bundle containing state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_item);
    }
}