package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;

/**
 * The ToppingActivity class is the controller for the activity_topping.xml, which is
 * a single topping in the Chicago and NY Pizza activities.
 * @author Serena, Jackson
 */
public class ToppingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topping);
    }
}