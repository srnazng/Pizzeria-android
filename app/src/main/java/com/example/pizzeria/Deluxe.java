package com.example.pizzeria;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Deluxe class extends the abstract Pizza class.
 * This class is responsible for adding the Deluxe toppings
 * and calculating the Deluxe pizza prices based on size and toppings.
 * @author Serena Zeng, Jackson Lee
 */
public class Deluxe extends Pizza{
    private static final double SMALL_PRICE = 14.99;
    private static final double MED_PRICE = 16.99;
    private static final double LARGE_PRICE = 18.99;

    /**
     * Create a new Deluxe object.
     * @param crust         Crust type
     * @param isChicago     true if is Chicago Pizza, false otherwise
     */
    public Deluxe(Crust crust, boolean isChicago){
        super(crust, isChicago);
    }

    /**
     * Get Deluxe pizza toppings
     * @return ArrayList of Topping objects
     */
    public static ArrayList<Topping> getDeluxeToppings(){
        return new ArrayList<>(Arrays.asList(Topping.PEPPERONI, Topping.SAUSAGE,
                Topping.MUSHROOM, Topping.ONION, Topping.GREEN_PEPPER));
    }

    /**
     * Get price of Deluxe pizza based on size
     * @return price of pizza as double
     */
    @Override
    public double price() {
        Size size = getSize();
        if(size == Size.SMALL) return SMALL_PRICE;
        if(size == Size.MEDIUM) return MED_PRICE;
        return LARGE_PRICE;
    }

    /**
     * Calculate price of a Deluxe pizza based on size
     * to be displayed on UI while user is creating pizza
     * @param size  Size object (small, medium, large)
     * @return      price of pizza as double
     */
    public static double calculatePrice(Size size) {
        if(size == Size.SMALL) return SMALL_PRICE;
        if(size == Size.MEDIUM) return MED_PRICE;
        return LARGE_PRICE;
    }

    /**
     * Create String representing Deluxe pizza object
     * including pizza type, crust, size and toppings
     * @return  Deluxe object as String
     */
    @NonNull
    @Override
    public String toString(){
        return "Deluxe " + super.toString();
    }
}
