package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzeria.models.Pizza;
import com.example.pizzeria.models.StoreOrder;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The CartActivity class is the controller class for the activity_cart.xml page.
 * This class initiates UI components and handles user interaction events.
 * @author Serena Zeng, Jackson Lee
 */
public class CartActivity extends AppCompatActivity {
    TextView orderNumber;
    static TextView tvSubtotal;
    static TextView tvSalesTax;
    static TextView tvCartTotal;
    RecyclerView rvCartPizzas;
    Button btnClearCart, btnPlaceOrder;
    ArrayList<Pizza> pizzaList;
    private PizzaItemAdapter adapter;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Create a CartActivity activity, and load a saved past state of the activity
     * if the activity is being reloaded
     * @param savedInstanceState  saved past state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tvSubtotal = findViewById(R.id.tvSubtotal);
        tvSalesTax = findViewById(R.id.tvSalesTax);
        tvCartTotal = findViewById(R.id.tvCartTotal);
        orderNumber = findViewById(R.id.tvOrderNum);
        rvCartPizzas = findViewById(R.id.rvPizzas);
        btnClearCart = findViewById(R.id.btnClearCart);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        pizzaList = new ArrayList<>();
        init();
        adapter = new PizzaItemAdapter(this, pizzaList); //create the adapter
        rvCartPizzas.setAdapter(adapter); //bind the list of items to the RecyclerView
        rvCartPizzas.setLayoutManager(new LinearLayoutManager(this));
        initButtons();
        updateCosts();
    }

    /**
     * Set the onClick methods for buttons
     */
    private void initButtons(){
        btnClearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreOrder.storeOrder.getCurrentOrder().getPizzaList().clear();
                pizzaList.clear();
                adapter.notifyDataSetChanged();
                updateCosts();
            }
        });

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StoreOrder.storeOrder.getCurrentOrder().getPizzaList().size() < 1){
                    Toast.makeText(CartActivity.this, "No Pizzas Added to Order", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(CartActivity.this);
                alert.setTitle("Cart");
                alert.setMessage("Place Order");
                alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        StoreOrder.storeOrder.completeCurrentOrder();
                        Toast.makeText(CartActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        init();
                    }
                }).setNegativeButton("Cancel", null);
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    /**
     * Set the order number in the UI, load the list of pizzas in the
     * current order, and update the cost of the order in the UI
     */
    private void init(){
        orderNumber.setText("Order Number: " + StoreOrder.storeOrder.generateOrderId());
        pizzaList.clear();
        pizzaList.addAll(StoreOrder.storeOrder.getCurrentOrder().getPizzaList());
        updateCosts();
    }

    /**
     * Update the costs of the order in the UI
     */
    public static void updateCosts(){
        tvSubtotal.setText(df.format(StoreOrder.storeOrder.getCurrentOrder().getSubtotal()));
        tvSalesTax.setText(df.format(StoreOrder.storeOrder.getCurrentOrder().getSalesTax()));
        tvCartTotal.setText(df.format(StoreOrder.storeOrder.getCurrentOrder().getTotal()));
    }
}