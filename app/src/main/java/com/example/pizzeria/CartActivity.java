package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.pizzeria.models.Pizza;
import com.example.pizzeria.models.StoreOrder;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    TextView orderNumber;
    RecyclerView rvCartPizzas;
    ArrayList<Integer> orderNumberList;
    ArrayList<Pizza> pizzaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        orderNumber = findViewById(R.id.tvOrderNum);
        orderNumber.setText("Order Number: " + StoreOrder.storeOrder.generateOrderId());

        rvCartPizzas = findViewById(R.id.rvPizzas);

        PizzaItemAdapter adapter = new PizzaItemAdapter(this, pizzaList); //create the adapter
        rvCartPizzas.setAdapter(adapter); //bind the list of items to the RecyclerView
    }

    private void setPizzaList(){
        pizzaList = StoreOrder.storeOrder.getCurrentOrder().getPizzaList();
    }


}