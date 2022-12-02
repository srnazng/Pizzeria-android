package com.example.pizzeria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Meatzza class extends the abstract Pizza class.
 * This class is responsible for adding the Meatzza toppings
 * and calculating the Meatzza pizza prices based on size.
 * @author Serena Zeng, Jackson Lee
 */
public class Meatzza extends Pizza{
    private static final double SMALL_PRICE = 15.99;
    private static final double MED_PRICE = 17.99;
    private static final double LARGE_PRICE = 19.99;

    /**
     * Create a new Meatzza object.
     * @param crust         Crust type
     * @param isChicago     true if is Chicago Pizza, false otherwise
     */
    public Meatzza(Crust crust, boolean isChicago){
        super(crust, isChicago);
    }

    /**
     * Get Meatzza pizza toppings
     * @return ArrayList of Topping objects
     */
    public static ArrayList<Topping> getMeatzzaToppings(){
        return new ArrayList<Topping>(Arrays.asList(Topping.PEPPERONI, Topping.SAUSAGE, Topping.HAM, Topping.BEEF));
    }

    /**
     * Get price of Meatzza pizza based on size
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
     * Calculate price of a Meatzza pizza based on size
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
     * Create String representing Meatzza pizza object
     * including pizza type, crust, size and toppings
     * @return  Meatzza object as String
     */
    @Override
    public String toString(){
        return "Meatzza " + super.toString();
    }
}
