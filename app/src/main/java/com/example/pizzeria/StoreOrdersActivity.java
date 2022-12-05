package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzeria.models.Order;
import com.example.pizzeria.models.Pizza;
import com.example.pizzeria.models.StoreOrder;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity {
    private Spinner spOrderNum;
    private ImageView btnCancelOrder;
    private TextView tvOrderTotal;
    private ListView lvOrder;
    private ArrayAdapter<Integer> orderNumAdapter;
    private Order order;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        spOrderNum = findViewById(R.id.spOrderNum);
        btnCancelOrder = findViewById(R.id.btnCancelOrder);
        tvOrderTotal = findViewById(R.id.tvOrderTotal);
        lvOrder = findViewById(R.id.lvOrder);

        spOrderNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                order = StoreOrder.storeOrder.getOrder(Integer.parseInt(spOrderNum.getSelectedItem().toString()));
                tvOrderTotal.setText(df.format(order.getTotal()));
                updateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                ArrayList<Integer> orderNums = StoreOrder.storeOrder.getOrderNumbers();
                if(orderNums.size() > 0){
                    spOrderNum.setSelection(0);
                    order = StoreOrder.storeOrder.getOrder(Integer.parseInt(spOrderNum.getSelectedItem().toString()));
                }
            }
        });

        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spOrderNum.getSelectedItem() == null){
                    Toast.makeText(StoreOrdersActivity.this, "No Order Selected", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer orderNum = Integer.parseInt(spOrderNum.getSelectedItem().toString());
                StoreOrder.storeOrder.cancelOrder(orderNum);
                Toast.makeText(StoreOrdersActivity.this, "Order " + orderNum + " cancelled", Toast.LENGTH_SHORT).show();
                init();
            }
        });

        init();
    }

    private void init(){
        orderNumAdapter = new ArrayAdapter<Integer>(StoreOrdersActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                StoreOrder.storeOrder.getOrderNumbers());
        orderNumAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spOrderNum.setAdapter(orderNumAdapter);

        if(StoreOrder.storeOrder.getOrderNumbers().size() > 0){
            spOrderNum.setSelection(0);
            order = StoreOrder.storeOrder.getOrder(Integer.parseInt(spOrderNum.getSelectedItem().toString()));
            updateList();
        }
        else{
            clearList();
        }
    }

    private void updateList(){
        ArrayAdapter<Pizza> arrayAdapter = new ArrayAdapter<Pizza>(
                this,
                android.R.layout.simple_list_item_1,
                order.getPizzaList() );
        lvOrder.setAdapter(arrayAdapter);
    }

    private void clearList(){
        order = null;
        ArrayAdapter<Pizza> arrayAdapter = new ArrayAdapter<Pizza>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<>());
        lvOrder.setAdapter(arrayAdapter);
        tvOrderTotal.setText("0.00");
    }
}