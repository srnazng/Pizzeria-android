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
import com.example.pizzeria.models.ChicagoPizza;
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

/**
 * The ChicagoActivity class is the controller for the activity_chicago.xml page.
 * This class initiates UI components and handles user interaction events
 * @author Serena Zeng, Jackson Lee
 */
public class ChicagoActivity extends AppCompatActivity {
    private RecyclerView rvChicagoToppings;
    private Spinner spChicagoType;
    private ArrayList<Topping> toppings;
    private ToppingsAdapter toppingsAdapter;
    private TextView tvChicagoCrust;
    private static TextView tvChicagoPrice;
    private RadioGroup rgChicagoSize;
    private RadioButton rbChicagoSelectedSize;
    private Button btnAddChicago;
    private static String PIZZA_PRICE;
    private String PIZZA_CRUST;
    private static String DELUXE;
    private static String MEATZZA;
    private static String BBQ_CHICKEN;
    private static Size size;
    private static String type;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Create a ChicagoActivity activity, and load a saved instance of
     * the activity if the activity is being reloaded
     * @param savedInstanceState  saved past state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago);

        PIZZA_PRICE = getResources().getString(R.string.pizza_price);
        PIZZA_CRUST = getResources().getString(R.string.crust);
        DELUXE = getResources().getStringArray(R.array.pizza_types)[1];
        BBQ_CHICKEN = getResources().getStringArray(R.array.pizza_types)[2];
        MEATZZA = getResources().getStringArray(R.array.pizza_types)[3];

        tvChicagoCrust = findViewById(R.id.tvChicagoCrust);
        tvChicagoPrice = findViewById(R.id.tvChicagoPrice);
        btnAddChicago = findViewById(R.id.btnAddChicago);
        rgChicagoSize = findViewById(R.id.rgChicagoSize);
        rbChicagoSelectedSize = findViewById(R.id.rbtnMedium);
        spChicagoType = findViewById(R.id.spChicagoType);
        rvChicagoToppings = findViewById(R.id.rvChicagoToppings);

        init();
    }

    /**
     * Bind listeners for all UI components, including add pizza button, size
     * radio group, pizza type spinner, and toppings recycler view
     */
    private void init(){
        btnAddChicago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToOrder();
            }
        });

        rgChicagoSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId)
             {
                     rbChicagoSelectedSize = findViewById(checkedId);
                     size = Size.toSize(rbChicagoSelectedSize.getText().toString());
                     calculatePrice(ToppingsAdapter.selectedToppings.size());
                 }
             }
        );
        rbChicagoSelectedSize.setSelected(true);

        spChicagoType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setUpView();
                toppingsAdapter.notifyDataSetChanged();
                type = spChicagoType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                spChicagoType.setSelection(0);
            }
        });

        toppings = new ArrayList<>();
        toppingsAdapter = new ToppingsAdapter(this, toppings, true); //create the adapter
        setUpView(); //add the list of items to the ArrayList
        rvChicagoToppings.setAdapter(toppingsAdapter);
        //use the LinearLayout for the RecyclerView
        rvChicagoToppings.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Add a pizza to the current order.
     * A toast is shown on success.
     */
    private void addToOrder(){
        String pizzaType = spChicagoType.getSelectedItem().toString();
        Size size = Size.toSize(rbChicagoSelectedSize.getText().toString());

        ChicagoPizza factory = new ChicagoPizza();
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
        Toast.makeText(ChicagoActivity.this, "Pizza Successfully Added to Cart!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Populate the UI components of the page in accordance
     * with the pizza type
     */
    private void setUpView() {
        String type = spChicagoType.getSelectedItem().toString();
        Size size = Size.toSize(rbChicagoSelectedSize.getText().toString());

        if(type.equals(getResources().getStringArray(R.array.pizza_types)[0])){
            toppings.clear();
            toppings.addAll(Topping.getAvailableToppings());
            toppingsAdapter.setDisableToppings(false);
            tvChicagoCrust.setText(PIZZA_CRUST + Crust.PAN);
            tvChicagoPrice.setText(PIZZA_PRICE + df.format(BuildYourOwn.calculatePrice(size, toppings.size())));
        }
        else if(type.equals(getResources().getStringArray(R.array.pizza_types)[1])){
            toppings.clear();
            toppings.addAll(Deluxe.getDeluxeToppings());
            toppingsAdapter.setDisableToppings(true);
            tvChicagoCrust.setText(PIZZA_CRUST + Crust.DEEP_DISH);
            tvChicagoPrice.setText(PIZZA_PRICE + Deluxe.calculatePrice(size));
        }
        else if(type.equals(getResources().getStringArray(R.array.pizza_types)[2])){
            toppings.clear();
            toppings.addAll(BBQChicken.getBBQChickenToppings());
            toppingsAdapter.setDisableToppings(true);
            tvChicagoCrust.setText(PIZZA_CRUST + Crust.PAN);
            tvChicagoPrice.setText(PIZZA_PRICE + BBQChicken.calculatePrice(size));
        }
        else{
            toppings.clear();
            toppings.addAll(Meatzza.getMeatzzaToppings());
            toppingsAdapter.setDisableToppings(true);
            tvChicagoCrust.setText(PIZZA_CRUST + Crust.STUFFED);
            tvChicagoPrice.setText(PIZZA_PRICE + Meatzza.calculatePrice(size));
        }
    }

    /**
     * Calculate the price of the pizza being ordered,
     * and set the price text field in the UI
     * @param numToppings  Number of toppings on the pizza
     */
    public static void calculatePrice(int numToppings){
        if(type.equals(MEATZZA)){
            tvChicagoPrice.setText(PIZZA_PRICE + Meatzza.calculatePrice(size));
        }
        else if(type.equals(DELUXE)){
            tvChicagoPrice.setText(PIZZA_PRICE + Deluxe.calculatePrice(size));
        }
        else if(type.equals(BBQ_CHICKEN)){
            tvChicagoPrice.setText(PIZZA_PRICE + BBQChicken.calculatePrice(size));
        }
        else{
            tvChicagoPrice.setText(PIZZA_PRICE + df.format(BuildYourOwn.calculatePrice(size, numToppings)));
        }
    }
}