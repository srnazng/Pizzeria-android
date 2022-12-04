package com.example.pizzeria.models;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The Order class represents an order of one or more pizzas
 * and uniquely identified by an integer id.
 * @author Serena Zeng, Jackson Lee
 */
public class Order implements Customizable {
    private boolean valid;
    private ArrayList<Pizza> pizzaList;
    private int orderNumber;
    private static final double SALES_TAX_RATE = 0.06625;
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
     * Remove all pizzas in order
     */
    public void clear(){
        pizzaList = new ArrayList<>();
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
        double amount = 0;
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
    @Override
    public String toString(){
        String returnString = "Order Number " + orderNumber + "\n";
        for(Pizza p : pizzaList){
            returnString += p.toString() + "\n";
        }
        returnString += "Order total: $" + df.format(getTotal()) + "\n";
        return returnString;
    }
}
