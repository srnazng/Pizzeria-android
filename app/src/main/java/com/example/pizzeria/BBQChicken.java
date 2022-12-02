package com.example.pizzeria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The BBQChicken class extends the abstract Pizza class.
 * This class is responsible for adding the BBQ Chicken toppings
 * and calculating the BBQ Chicken pizza prices based on size.
 * @author Serena Zeng, Jackson Lee
 */
public class BBQChicken extends Pizza{
    private static final double SMALL_PRICE = 13.99;
    private static final double MED_PRICE = 15.99;
    private static final double LARGE_PRICE = 17.99;

    /**
     * Create a new BBQChicken object.
     * @param crust         Crust type
     * @param isChicago     true if is Chicago Pizza, false otherwise
     */
    public BBQChicken(Crust crust, boolean isChicago){
        super(crust, isChicago);
    }

    /**
     * Get BBQ Chicken pizza toppings
     * @return ArrayList of Topping objects
     */
    public static ArrayList<Topping> getBBQChickenToppings(){
        return new ArrayList<>(Arrays.asList(Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER,
                Topping.CHEDDAR, Topping.PROVOLONE));
    }

    /**
     * Get price of BBQ Chicken pizza based on size
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
     * Calculate price of a BBQ Chicken pizza based on size
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
     * Create String representing BBQ Chicken pizza object
     * including pizza type, crust, size and toppings
     * @return  BBQChicken object as String
     */
    @Override
    public String toString(){
        return "BBQ Chicken " + super.toString();
    }
}
