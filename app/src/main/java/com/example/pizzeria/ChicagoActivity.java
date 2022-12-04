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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChicagoActivity extends AppCompatActivity {
    private RecyclerView rvChicagoToppings;
    private Spinner spChicagoType;
    private ArrayList<Topping> toppings;
    private ToppingsAdapter toppingsAdapter;
    private TextView tvChicagoCrust, tvChicagoPrice;
    private RadioGroup rgChicagoSize;
    private RadioButton rbChicagoSelectedSize;
    private Button btnAddChicago;
    private String PIZZA_PRICE;
    private String PIZZA_CRUST;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago);

        PIZZA_PRICE = getResources().getString(R.string.pizza_price);
        PIZZA_CRUST = getResources().getString(R.string.crust);

        tvChicagoCrust = findViewById(R.id.tvChicagoCrust);
        tvChicagoPrice = findViewById(R.id.tvChicagoPrice);
        rgChicagoSize = findViewById(R.id.rgChicagoSize);
        btnAddChicago = findViewById(R.id.btnAddChicago);
        rgChicagoSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId)
              {
                  rbChicagoSelectedSize = findViewById(checkedId);
              }
          }
        );
        rbChicagoSelectedSize = findViewById(R.id.rbtnMedium);
        rbChicagoSelectedSize.setSelected(true);

        spChicagoType = findViewById(R.id.spChicagoType);
        spChicagoType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setUpView();
                toppingsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                spChicagoType.setSelection(0);
            }
        });

        rvChicagoToppings = findViewById(R.id.rvChicagoToppings);
        toppings = new ArrayList<>();
        setUpView(); //add the list of items to the ArrayList
        toppingsAdapter = new ToppingsAdapter(this, toppings); //create the adapter
        rvChicagoToppings.setAdapter(toppingsAdapter);
        //use the LinearLayout for the RecyclerView
        rvChicagoToppings.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setUpView() {
        String type = spChicagoType.getSelectedItem().toString();
        Size size = Size.toSize(rbChicagoSelectedSize.getText().toString());
        System.out.println(type);
        if(type.equals(getResources().getStringArray(R.array.pizza_types)[0])){
            toppings.clear();
            toppings.addAll(Topping.getAvailableToppings());
            tvChicagoCrust.setText(PIZZA_CRUST + Crust.PAN.toString());
            tvChicagoPrice.setText(PIZZA_PRICE + df.format(BuildYourOwn.calculatePrice(size, toppings.size())));
        }
        else if(type.equals(getResources().getStringArray(R.array.pizza_types)[1])){
            toppings.clear();
            toppings.addAll(Deluxe.getDeluxeToppings());
            tvChicagoCrust.setText(PIZZA_CRUST + Crust.DEEP_DISH.toString());
            tvChicagoPrice.setText(PIZZA_PRICE + Deluxe.calculatePrice(size));
        }
        else if(type.equals(getResources().getStringArray(R.array.pizza_types)[2])){
            toppings.clear();
            toppings.addAll(BBQChicken.getBBQChickenToppings());
            tvChicagoCrust.setText(PIZZA_CRUST + Crust.PAN.toString());
            tvChicagoPrice.setText(PIZZA_PRICE + BBQChicken.calculatePrice(size));
        }
        else{
            toppings.clear();
            toppings.addAll(Meatzza.getMeatzzaToppings());
            tvChicagoCrust.setText(PIZZA_CRUST + Crust.STUFFED.toString());
            tvChicagoPrice.setText(PIZZA_PRICE + Meatzza.calculatePrice(size));
        }
        System.out.println(toppings);
    }
}