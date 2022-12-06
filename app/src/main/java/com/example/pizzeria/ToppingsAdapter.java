package com.example.pizzeria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzeria.models.Topping;

import java.util.ArrayList;

/**
 * The ToppingsAdapter class binds the topping options with items in the
 * recycler views in the Chicago and New York activities
 * @author Serena, Jackson
 */
public class ToppingsAdapter extends RecyclerView.Adapter<ToppingsAdapter.ItemsHolder>{
    private Context context; //need the context to inflate the layout
    private ArrayList<Topping> items; //need the data binding to each row of RecyclerView
    private boolean disableToppings;
    private boolean isChicago;
    public static ArrayList<Topping> selectedToppings;

    /**
     * Create a ToppingsAdapter from the current context and list of toppings for the pizza type.
     * @param context      Context of the activity
     * @param items        List of toppings
     * @param isChicago    true if is Chicago pizza, false otherwise
     */
    public ToppingsAdapter(Context context, ArrayList<Topping> items, boolean isChicago) {
        this.context = context;
        this.items = items;
        this.isChicago = isChicago;
        selectedToppings = new ArrayList<>();
        disableToppings = false;
    }

    /**
     * Set toppings as checkable or not
     * @param disableToppings   true to disable, false otherwise
     */
    public void setDisableToppings(boolean disableToppings){
        this.disableToppings = disableToppings;
    }

    /**
     * Inflate the row layout for the Topping items in the RecyclerView
     * @param parent    parent ViewGroup of new view to be created
     * @param viewType  view type constant
     * @return          ItemsHolder with inflated view
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_topping, parent, false);

        return new ItemsHolder(view);
    }

    /**
     * Assign toppings to each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder    the instance of ItemsHolder
     * @param position  the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        //assign values for each row
        Topping topping = items.get(position);
        holder.cbTopping.setText(topping.toString());

        if (disableToppings){
            holder.cbTopping.setChecked(true);
            holder.cbTopping.setEnabled(false);
        }
        else{
            holder.cbTopping.setChecked(false);
            holder.cbTopping.setEnabled(true);
        }

        holder.cbTopping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selectedToppings.add(topping);
                }
                else {
                    selectedToppings.remove(topping);
                }
                if(isChicago){
                    ChicagoActivity.calculatePrice(selectedToppings.size());
                }
                else{
                    NewYorkActivity.calculatePrice(selectedToppings.size());
                }
            }
        });
    }

    /**
     * Get the number of topping items in the ArrayList.
     * @return the number of topping items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private CheckBox cbTopping;

        /**
         * Create new ItemsHolder
         * @param itemView  View of individual topping item
         */
        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            cbTopping = itemView.findViewById(R.id.cbTopping);
        }
    }
}
