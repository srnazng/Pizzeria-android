package com.example.pizzeria;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The Pizza class is an abstract class that implements the Customizable
 * interface. The Pizza class includes the shared attributes between all
 * pizza types including topping list, crust, and size.
 * @author Serena Zeng, Jackson Lee
 */
public abstract class Pizza implements Customizable {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static final int MAX_TOPPINGS = 7;
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;
    public abstract double price();
    protected boolean isChicago;

    /**
     * Create a new Pizza object
     * @param crust         Crust type
     * @param isChicago     true if is a Chicago pizza, false otherwise
     */
    public Pizza(Crust crust, boolean isChicago){
        toppings = new ArrayList<>();
        this.size = Size.MEDIUM;
        this.crust = crust;
        this.isChicago = isChicago;
    }

    /**
     * Get size attribute of pizza object
     * @return  Size of pizza (SMALL, MEDIUM, LARGE)
     */
    public Size getSize() {
        return size;
    }

    /**
     * Set size attribute of pizza object
     * @param size  Size to set pizza
     */
    public void setSize(Size size){
        this.size = size;
    }

    /**
     * Get list of toppings added to pizza
     * @return  ArrayList of Topping objects
     */
    public ArrayList<Topping> getToppings(){ return toppings; }

    /**
     * Add topping or list of toppings to Pizza object
     * @param obj   Object to add
     * @return  true if toppings successfully added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Topping){
            toppings.add((Topping) obj);
        }
        else if(obj instanceof ArrayList){
            toppings.addAll((ArrayList<Topping>) obj);
        }
        else{
            return false;
        }
        return true;
    }

    /**
     * Remove topping or list of toppings from Pizza object
     * @param obj   Object to remove
     * @return  true if toppings successfully removed, false otherwise
     */
    @Override
    public boolean remove(Object obj){
        if(obj instanceof Topping){
            toppings.remove((Topping) obj);
        }
        else if(obj instanceof ArrayList){
            toppings.removeAll((ArrayList<Topping>) obj);
        }
        else{
            return false;
        }
        return true;
    }

    /**
     * Convert pizza object to String including pizza type,
     * crust, size, price, and toppings
     * @return  String of Pizza object
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("(");
        if(isChicago){
            str.append("Chicago Style - Pan), ");
        }
        else{
            str.append("NY Style - Thin), ");
        }
        ArrayList<Topping> toppings = getToppings();
        for(Topping t : toppings){
            str.append(t).append(", ");
        }

        str.append(getSize().toString().toLowerCase()).append(", $").append(df.format(price()));

        return str.toString();
    }
}
