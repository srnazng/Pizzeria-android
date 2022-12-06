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
    private ImageView ivChicago;

    private static final String PIZZA_CRUST = "Crust: ";
    private static final String PIZZA_PRICE = "Pizza Price: $";
    private static final String DELUXE = "Deluxe";
    private static final String MEATZZA = "Meatzza";
    private static final String BBQ_CHICKEN = "BBQ Chicken";
    private static final int DEFAULT_SELECT = 0;

    private static Size size;
    private static String type;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Create a ChicagoActivity activity, and load a past saved state of
     * the activity if the activity is being reloaded
     * @param savedInstanceState  saved past state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago);

        ivChicago = findViewById(R.id.ivChicago);
        tvChicagoCrust = findViewById(R.id.tvChicagoCrust);
        tvChicagoPrice = findViewById(R.id.tvChicagoPrice);
        btnAddChicago = findViewById(R.id.btnAddChicago);
        rgChicagoSize = findViewById(R.id.rgChicagoSize);
        rbChicagoSelectedSize = findViewById(R.id.rbtnMedium);
        spChicagoType = findViewById(R.id.spChicagoType);
        rvChicagoToppings = findViewById(R.id.rvChicagoToppings);

        type = spChicagoType.getSelectedItem().toString();

        init();
    }

    /**
     * Bind listeners for all UI components, including add pizza button, size
     * radio group, pizza type spinner, and toppings recycler view
     */
    private void init(){
        btnAddChicago.setOnClickListener(v -> addToOrder());

        size = Size.MEDIUM;
        rgChicagoSize.setOnCheckedChangeListener((group, checkedId) -> {
            rbChicagoSelectedSize = findViewById(checkedId);
            size = Size.toSize(rbChicagoSelectedSize.getText().toString());
            calculatePrice(ToppingsAdapter.selectedToppings.size());
        });
        rbChicagoSelectedSize.setSelected(true);

        spChicagoType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                type = spChicagoType.getSelectedItem().toString();
                toppingsAdapter.notifyDataSetChanged();
                setUpView();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                spChicagoType.setSelection(DEFAULT_SELECT);
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
        ChicagoPizza factory = new ChicagoPizza();
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
        Toast.makeText(ChicagoActivity.this, "Pizza Successfully Added to Cart!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Populate the UI components of the page in accordance
     * with the pizza type and size.
     */
    @SuppressLint("SetTextI18n")
    private void setUpView() {
        toppings.clear();
        int res;
        switch (type) {
            case MEATZZA:
                toppings.addAll(Meatzza.getMeatzzaToppings());
                toppingsAdapter.setDisableToppings(true);
                tvChicagoCrust.setText(PIZZA_CRUST + Crust.STUFFED);
                tvChicagoPrice.setText(PIZZA_PRICE + Meatzza.calculatePrice(size));
                res = getResources().getIdentifier("chicago_meatzza", "drawable", ChicagoActivity.this.getPackageName());
                break;
            case DELUXE:
                toppings.addAll(Deluxe.getDeluxeToppings());
                toppingsAdapter.setDisableToppings(true);
                tvChicagoCrust.setText(PIZZA_CRUST + Crust.DEEP_DISH);
                tvChicagoPrice.setText(PIZZA_PRICE + Deluxe.calculatePrice(size));
                res = getResources().getIdentifier("chicago_deluxe","drawable", ChicagoActivity.this.getPackageName());
                break;
            case BBQ_CHICKEN:
                toppings.addAll(BBQChicken.getBBQChickenToppings());
                toppingsAdapter.setDisableToppings(true);
                tvChicagoCrust.setText(PIZZA_CRUST + Crust.PAN);
                tvChicagoPrice.setText(PIZZA_PRICE + BBQChicken.calculatePrice(size));
                res = getResources().getIdentifier("chicago_bbq_chicken","drawable", ChicagoActivity.this.getPackageName());
                break;
            default:
                toppings.addAll(Topping.getAvailableToppings());
                ToppingsAdapter.selectedToppings.clear();
                toppingsAdapter.setDisableToppings(false);
                tvChicagoCrust.setText(PIZZA_CRUST + Crust.PAN);
                tvChicagoPrice.setText(PIZZA_PRICE
                        + df.format(BuildYourOwn.calculatePrice(size, ToppingsAdapter.selectedToppings.size())));
                res = getResources().getIdentifier("chicago_pizza", "drawable", ChicagoActivity.this.getPackageName());
                break;
        }
        ivChicago.setImageResource(res);
    }

    /**
     * Calculate the price of the pizza being ordered,
     * and set the price text field in the UI
     * @param numToppings  Number of toppings on the pizza
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
        tvChicagoPrice.setText(price);
    }
}