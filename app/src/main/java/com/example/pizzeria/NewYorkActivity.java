package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
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

/**
 * The NewYorkActivity class is the controller for the activity_new_york page.
 * This class initiates UI components and handles user interaction events
 * @author Serena Zeng, Jackson Lee
 */
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
    private ImageView ivNewYork;

    private static final String PIZZA_CRUST = "Crust: ";
    private static final String PIZZA_PRICE = "Pizza Price: $";
    private static final String DELUXE = "Deluxe";
    private static final String MEATZZA = "Meatzza";
    private static final String BBQ_CHICKEN = "BBQ Chicken";

    private static Size size;
    private static String type;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Create a NewYorkActivity activity, and load a past saved state
     * of the activity if the activity is being reloaded
     * @param savedInstanceState   saved past state of activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_york);

        ivNewYork = findViewById(R.id.ivNewYork);
        tvNewYorkCrust = findViewById(R.id.tvNewYorkCrust);
        tvNewYorkPrice = findViewById(R.id.tvNewYorkPrice);
        btnAddNewYork = findViewById(R.id.btnAddNewYork);
        rgNewYorkSize = findViewById(R.id.rgNewYorkSize);
        rbNewYorkSelectedSize = findViewById(R.id.rbtnMedium);
        spNewYorkType = findViewById(R.id.spNewYorkType);
        rvNewYorkToppings = findViewById(R.id.rvNewYorkToppings);

        type = spNewYorkType.getSelectedItem().toString();

        init();
    }

    /**
     * Bind listeners for all UI components, including add pizza button, size
     * radio group, pizza type spinner, and toppings recycler view
     */
    private void init(){
        btnAddNewYork.setOnClickListener(v -> addToOrder());

        size = Size.MEDIUM;
        rgNewYorkSize.setOnCheckedChangeListener((group, checkedId) -> {
            rbNewYorkSelectedSize = findViewById(checkedId);
            size = Size.toSize(rbNewYorkSelectedSize.getText().toString());
            calculatePrice(ToppingsAdapter.selectedToppings.size());
        });
        rbNewYorkSelectedSize.setSelected(true);

        spNewYorkType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                type = spNewYorkType.getSelectedItem().toString();
                toppingsAdapter.notifyDataSetChanged();
                setUpView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                spNewYorkType.setSelection(0);
            }
        });

        toppings = new ArrayList<>();
        toppingsAdapter = new ToppingsAdapter(this, toppings, false); //create the adapter
        setUpView(); //add the list of items to the ArrayList
        rvNewYorkToppings.setAdapter(toppingsAdapter);
        //use the LinearLayout for the RecyclerView
        rvNewYorkToppings.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Add a pizza to the current order
     * A toast is shown on success
     */
    private void addToOrder(){
        NYPizza factory = new NYPizza();
        Pizza pizza;
        switch (type) {
            case DELUXE:
                pizza = factory.createDeluxe();
                break;
            case BBQ_CHICKEN:
                pizza = factory.createBBQChicken();
                break;
            case MEATZZA:
                pizza = factory.createMeatzza();
                break;
            default:
                pizza = factory.createBuildYourOwn();
                break;
        }
        pizza.setSize(size);
        pizza.add(ToppingsAdapter.selectedToppings);
        Order currentOrder = StoreOrder.storeOrder.getCurrentOrder();
        currentOrder.add(pizza);
        Toast.makeText(NewYorkActivity.this, "Pizza Successfully Added to Cart!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Populate the UI components of the page in accordance
     * With the pizza type and size
     */
    @SuppressLint("SetTextI18n")
    private void setUpView() {
        toppings.clear();
        int res;
        switch (type) {
            case MEATZZA:
                toppings.addAll(Meatzza.getMeatzzaToppings());
                toppingsAdapter.setDisableToppings(true);
                tvNewYorkCrust.setText(PIZZA_CRUST + Crust.STUFFED);
                tvNewYorkPrice.setText(PIZZA_PRICE + Meatzza.calculatePrice(size));
                res = getResources().getIdentifier("ny_meatzza", "drawable", NewYorkActivity.this.getPackageName());
                break;
            case DELUXE:
                toppings.addAll(Deluxe.getDeluxeToppings());
                toppingsAdapter.setDisableToppings(true);
                tvNewYorkCrust.setText(PIZZA_CRUST + Crust.DEEP_DISH);
                tvNewYorkPrice.setText(PIZZA_PRICE + Deluxe.calculatePrice(size));
                res = getResources().getIdentifier("ny_deluxe", "drawable", NewYorkActivity.this.getPackageName());
                break;
            case BBQ_CHICKEN:
                toppings.addAll(BBQChicken.getBBQChickenToppings());
                toppingsAdapter.setDisableToppings(true);
                tvNewYorkCrust.setText(PIZZA_CRUST + Crust.PAN);
                tvNewYorkPrice.setText(PIZZA_PRICE + BBQChicken.calculatePrice(size));
                res = getResources().getIdentifier("ny_bbq_chicken", "drawable", NewYorkActivity.this.getPackageName());
                break;
            default:
                toppings.addAll(Topping.getAvailableToppings());
                ToppingsAdapter.selectedToppings.clear();
                toppingsAdapter.setDisableToppings(false);
                tvNewYorkCrust.setText(PIZZA_CRUST + Crust.PAN);
                tvNewYorkPrice.setText(PIZZA_PRICE + df.format(BuildYourOwn.calculatePrice(size, ToppingsAdapter.selectedToppings.size())));
                res = getResources().getIdentifier("ny_pizza", "drawable", NewYorkActivity.this.getPackageName());
                break;
        }
        ivNewYork.setImageResource(res);
    }

    /**
     * Calculate the price of the pizza being ordered
     * and set the price text field in the UI
     * @param numToppings   Number of toppings on the pizza
     */
    public static void calculatePrice(int numToppings){
        String price;
        switch (type) {
            case MEATZZA:
                price = PIZZA_PRICE + Meatzza.calculatePrice(size);
                break;
            case DELUXE:
                price = PIZZA_PRICE + Deluxe.calculatePrice(size);
                break;
            case BBQ_CHICKEN:
                price = PIZZA_PRICE + BBQChicken.calculatePrice(size);
                break;
            default:
                price = PIZZA_PRICE + df.format(BuildYourOwn.calculatePrice(size, numToppings));
                break;
        }
        tvNewYorkPrice.setText(price);
    }
}