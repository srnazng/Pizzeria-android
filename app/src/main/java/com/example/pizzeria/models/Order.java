package com.example.pizzeria.models;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The Order class represents an order of one or more pizzas
 * and uniquely identified by an integer id.
 * @author Serena Zeng, Jackson Lee
 */
public class Order implements Customizable {
    private boolean valid;
    private final ArrayList<Pizza> pizzaList;
    private final int orderNumber;
    private static final double SALES_TAX_RATE = 0.06625;
    private static final double DEFAULT_PRICE = 0;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Create new Order object that defaults to valid with an empty
     * list of pizzas, and a new order id.
     */
    public Order(){
        valid = true;
        orderNumber = StoreOrder.storeOrder.generateOrderId();
        pizzaList = new ArrayList<>();
    }

    /**
     * Get unique order id
     * @return  order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Get list of pizzas in order
     * @return  ArrayList of Pizza objects
     */
    public ArrayList<Pizza> getPizzaList(){
        return pizzaList;
    }

    /**
     * Calculate subtotal of pizzas in order
     * @return  subtotal as a double
     */
    public double getSubtotal(){
        double amount = DEFAULT_PRICE;
        for(Pizza p : pizzaList){
            amount += p.price();
        }
        return amount;
    }

    /**
     * Calculate sales tax of order
     * @return  sales tax as a double
     */
    public double getSalesTax(){
        return getSubtotal() * SALES_TAX_RATE;
    }

    /**
     * Calculate total price (includes subtotal and sales tax) of order)
     * @return  total price as a double
     */
    public double getTotal(){
        return getSubtotal() + getSalesTax();
    }

    /**
     * Marks order as invalid (after deletion)
     */
    public void setInvalid(){
        valid = false;
    }

    /**
     * Check if order is valid
     * @return  true if valid, false otherwise
     */
    public boolean isValid(){
        return valid;
    }

    /**
     * Add pizza to order
     * @param obj   Object to add
     * @return  true if add successful, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        Pizza pizza = (Pizza) obj;
        return pizzaList.add(pizza);
    }

    /**
     * Remove pizza from order
     * @param obj   Object to remove
     * @return  true if remove successful, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        Pizza pizza = (Pizza) obj;
        return pizzaList.remove(pizza);
    }

    /**
     * Convert Order to String containing order number, pizzas in order, and order total
     * @return  Order object as String
     */
    @NonNull
    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder("Order Number " + orderNumber + "\n");
        for(Pizza p : pizzaList){
            returnString.append(p.toString()).append("\n");
        }
        returnString.append("Order total: $").append(df.format(getTotal())).append("\n");
        return returnString.toString();
    }
}
