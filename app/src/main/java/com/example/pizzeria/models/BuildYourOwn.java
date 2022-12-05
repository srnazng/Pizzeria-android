package com.example.pizzeria.models;

import androidx.annotation.NonNull;

/**
 * The BuidlYourOwn class extends the abstract Pizza class.
 * This class is responsible for adding the user selected toppings
 * and calculating the Build Your Own pizza prices based on size and toppings.
 * @author Serena Zeng, Jackson Lee
 */
public class BuildYourOwn extends Pizza {
    private static final double SMALL_PRICE = 8.99;
    private static final double MED_PRICE = 10.99;
    private static final double LARGE_PRICE = 12.99;
    private static final double PRICE_PER_TOPPING = 1.59;
    private static final int INVALID = -1;

    /**
     * Create a new BuildYourOwn object.
     * @param crust         Crust type
     * @param isChicago     true if is Chicago Pizza, false otherwise
     */
    public BuildYourOwn(Crust crust, boolean isChicago) {
        super(crust, isChicago);
    }

    /**
     * Get price of BuildYourOwn pizza based on size and toppings
     * @return price of pizza as double
     */
    @Override
    public double price() {
        Size size = getSize();
        if (getToppings().size() > Pizza.MAX_TOPPINGS){
            return INVALID;
        }
        double topping_price = getToppings().size() * PRICE_PER_TOPPING;
        if(size == Size.SMALL) return SMALL_PRICE + topping_price;
        if(size == Size.MEDIUM) return MED_PRICE + topping_price;
        return LARGE_PRICE + topping_price;
    }

    /**
     * Calculate price of a Build Your Own pizza based on size
     * to be displayed on UI while user is creating pizza
     * @param size          Size object (small, medium, large)
     * @param numToppings   Number of toppings user added to pizza
     * @return              price of pizza as double
     */
    public static double calculatePrice(Size size, int numToppings) {
        double topping_price = numToppings * PRICE_PER_TOPPING;
        if(size == Size.SMALL) return SMALL_PRICE + topping_price;
        if(size == Size.MEDIUM) return MED_PRICE + topping_price;
        return LARGE_PRICE + topping_price;
    }

    /**
     * Create String representing Build Your Own pizza object
     * including pizza type, crust, size and toppings
     * @return  Build Your Own object as String
     */
    @NonNull
    @Override
    public String toString(){
        return "Build Your Own " + super.toString();
    }
}
