package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzeria.models.BBQChicken;
import com.example.pizzeria.models.BuildYourOwn;
import com.example.pizzeria.models.NYPizza;
import com.example.pizzeria.models.Crust;
import com.example.pizzeria.models.Deluxe;
import com.example.pizzeria.models.Meatzza;
import com.example.pizzeria.models.Order;
import com.example.pizzeria.models.Pizza;
import com.example.pizzeria.models.Size;
import com.example.pizzeria.models.StoreOrder;
import com.example.pizzeria.models.Topping;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NewYorkActivity extends AppCompatActivity {
    private RecyclerView rvNewYorkToppings;
    private Spinner spNewYorkType;
    private ArrayList<Topping> toppings;
    private ToppingsAdapter toppingsAdapter;
    private TextView tvNewYorkCrust;
    private static TextView tvNewYorkPrice;
    private RadioGroup rgNewYorkSize;
    private RadioButton rbNewYorkSelectedSize;
    private Button btnAddNewYork;
    private static String PIZZA_PRICE;
    private String PIZZA_CRUST;
    private static String DELUXE;
    private static String MEATZZA;
    private static String BBQ_CHICKEN;
    private static Size size;
    private static String type;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_york);

        PIZZA_PRICE = getResources().getString(R.string.pizza_price);
        PIZZA_CRUST = getResources().getString(R.string.crust);
        DELUXE = getResources().getStringArray(R.array.pizza_types)[1];
        BBQ_CHICKEN = getResources().getStringArray(R.array.pizza_types)[2];
        MEATZZA = getResources().getStringArray(R.array.pizza_types)[3];

        tvNewYorkCrust = findViewById(R.id.tvNewYorkCrust);
        tvNewYorkPrice = findViewById(R.id.tvNewYorkPrice);
        btnAddNewYork = findViewById(R.id.btnAddNewYork);
        rgNewYorkSize = findViewById(R.id.rgNewYorkSize);
        rbNewYorkSelectedSize = findViewById(R.id.rbtnMedium);
        spNewYorkType = findViewById(R.id.spNewYorkType);
        rvNewYorkToppings = findViewById(R.id.rvNewYorkToppings);

        init();
    }

    private void init(){
        btnAddNewYork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToOrder();
            }
        });

        rgNewYorkSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                     @Override
                                                     public void onCheckedChanged(RadioGroup group, int checkedId)
                                                     {
                                                         rbNewYorkSelectedSize = findViewById(checkedId);
                                                         size = Size.toSize(rbNewYorkSelectedSize.getText().toString());
                                                         calculatePrice(ToppingsAdapter.selectedToppings.size());
                                                     }
                                                 }
        );
        rbNewYorkSelectedSize.setSelected(true);

        spNewYorkType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setUpView();
                toppingsAdapter.notifyDataSetChanged();
                type = spNewYorkType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                spNewYorkType.setSelection(0);
            }
        });

        toppings = new ArrayList<>();
        toppingsAdapter = new ToppingsAdapter(this, toppings); //create the adapter
        setUpView(); //add the list of items to the ArrayList
        rvNewYorkToppings.setAdapter(toppingsAdapter);
        //use the LinearLayout for the RecyclerView
        rvNewYorkToppings.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addToOrder(){
        String pizzaType = spNewYorkType.getSelectedItem().toString();
        Size size = Size.toSize(rbNewYorkSelectedSize.getText().toString());

        NYPizza factory = new NYPizza();
        Pizza pizza;
        if(pizzaType.equals("Deluxe")){
            pizza = factory.createDeluxe();
        }
        else if(pizzaType.equals("BBQ Chicken")){
            pizza = factory.createBBQChicken();
        }
        else if(pizzaType.equals("Meatzza")){
            pizza = factory.createMeatzza();
        }
        else{
            pizza = factory.createBuildYourOwn();
        }
        pizza.setSize(size);
        pizza.add(ToppingsAdapter.selectedToppings);
        Order currentOrder = StoreOrder.storeOrder.getCurrentOrder();
        currentOrder.add(pizza);
        Toast.makeText(NewYorkActivity.this, "Pizza Successfully Added to Cart!", Toast.LENGTH_SHORT).show();
    }

    private void setUpView() {
        String type = spNewYorkType.getSelectedItem().toString();
        Size size = Size.toSize(rbNewYorkSelectedSize.getText().toString());

        if(type.equals(getResources().getStringArray(R.array.pizza_types)[0])){
            toppings.clear();
            toppings.addAll(Topping.getAvailableToppings());
            toppingsAdapter.setDisableToppings(false);
            tvNewYorkCrust.setText(PIZZA_CRUST + Crust.PAN);
            tvNewYorkPrice.setText(PIZZA_PRICE + df.format(BuildYourOwn.calculatePrice(size, toppings.size())));
        }
        else if(type.equals(getResources().getStringArray(R.array.pizza_types)[1])){
            toppings.clear();
            toppings.addAll(Deluxe.getDeluxeToppings());
            toppingsAdapter.setDisableToppings(true);
            tvNewYorkCrust.setText(PIZZA_CRUST + Crust.DEEP_DISH);
            tvNewYorkPrice.setText(PIZZA_PRICE + Deluxe.calculatePrice(size));
        }
        else if(type.equals(getResources().getStringArray(R.array.pizza_types)[2])){
            toppings.clear();
            toppings.addAll(BBQChicken.getBBQChickenToppings());
            toppingsAdapter.setDisableToppings(true);
            tvNewYorkCrust.setText(PIZZA_CRUST + Crust.PAN);
            tvNewYorkPrice.setText(PIZZA_PRICE + BBQChicken.calculatePrice(size));
        }
        else{
            toppings.clear();
            toppings.addAll(Meatzza.getMeatzzaToppings());
            toppingsAdapter.setDisableToppings(true);
            tvNewYorkCrust.setText(PIZZA_CRUST + Crust.STUFFED);
            tvNewYorkPrice.setText(PIZZA_PRICE + Meatzza.calculatePrice(size));
        }
    }

    public static void calculatePrice(int numToppings){
        if(type.equals(MEATZZA)){
            tvNewYorkPrice.setText(PIZZA_PRICE + Meatzza.calculatePrice(size));
        }
        else if(type.equals(DELUXE)){
            tvNewYorkPrice.setText(PIZZA_PRICE + Deluxe.calculatePrice(size));
        }
        else if(type.equals(BBQ_CHICKEN)){
            tvNewYorkPrice.setText(PIZZA_PRICE + BBQChicken.calculatePrice(size));
        }
        else{
            tvNewYorkPrice.setText(PIZZA_PRICE + df.format(BuildYourOwn.calculatePrice(size, numToppings)));
        }
    }
}