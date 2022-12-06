package com.example.pizzeria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzeria.models.Pizza;
import com.example.pizzeria.models.StoreOrder;

import java.util.ArrayList;

/**
 * The PizzaItemAdapter class binds the information about the pizzas in the current
 * order with items in the recycler view of activity_cart.xml
 * @author Serena Zeng, Jackson Lee
 */
public class PizzaItemAdapter extends RecyclerView.Adapter<PizzaItemAdapter.ItemsHolder>{

    private Context context;
    private ArrayList<Pizza> pizzas;

    /**
     * Create a PizzaItemAdapter from the current context and list of pizzas.
     * @param context   Current context of app
     * @param pizzas    List of pizzas to be displayed in cart
     */
    public PizzaItemAdapter(Context context, ArrayList<Pizza> pizzas) {
        this.context = context;
        this.pizzas = pizzas;
    }

    /**
     * This method will inflate the row layout for the pizza items in the RecyclerView
     * @param parent    parent ViewGroup of new view to be created
     * @param viewType  view type constant
     * @return          ItemsHolder with inflated view
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_pizza_item, parent, false);

        return new ItemsHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the pizza
     * item becomes visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the pizza item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        Pizza pizza = pizzas.get(position);
        holder.tvPizza.setText(pizza.toString());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreOrder.storeOrder.getCurrentOrder().getPizzaList().remove(pizza);
                pizzas.remove(pizza);
                notifyDataSetChanged();
                CartActivity.updateCosts();
            }
        });
    }

    /**
     * Get the number of pizza items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return pizzas.size(); //number of pizzas in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView tvPizza;
        private ImageView ivDelete;

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            tvPizza = itemView.findViewById(R.id.tvPizza);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }


}
