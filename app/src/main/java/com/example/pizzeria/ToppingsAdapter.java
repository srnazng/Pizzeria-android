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
 * This is an Adapter class to be used to instantiate an adapter for the RecyclerView.
 * Must extend RecyclerView.Adapter, which will enforce you to implement 3 methods:
 *      1. onCreateViewHolder, 2. onBindViewHolder, and 3. getItemCount
 *
 * You must use the data type <thisClassName.yourHolderName>, in this example
 * <ItemAdapter.ItemHolder>. This will enforce you to define a constructor for the
 * ItemAdapter and an inner class ItemsHolder (a static class)
 * The ItemsHolder class must extend RecyclerView.ViewHolder. In the constructor of this class,
 * you do something similar to the onCreate() method in an Activity.
 * @author Lily Chang
 */
public class ToppingsAdapter extends RecyclerView.Adapter<ToppingsAdapter.ItemsHolder>{
    private Context context; //need the context to inflate the layout
    private ArrayList<Topping> items; //need the data binding to each row of RecyclerView
    private boolean disableToppings;
    public static ArrayList<Topping> selectedToppings;

    public ToppingsAdapter(Context context, ArrayList<Topping> items) {
        this.context = context;
        this.items = items;
        selectedToppings = new ArrayList<>();
        disableToppings = false;
    }

    public void setDisableToppings(boolean disableToppings){
        this.disableToppings = disableToppings;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent
     * @param viewType
     * @return
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
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
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
                ChicagoActivity.calculatePrice(selectedToppings.size());
            }
        });
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
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

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            cbTopping = itemView.findViewById(R.id.cbTopping);
        }
    }
}
