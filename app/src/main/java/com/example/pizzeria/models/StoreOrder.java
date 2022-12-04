package com.example.pizzeria.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * The StoreOrder class implements the Customizable
 * interface and contains a list of all orders of a store.
 * @author Serena Zeng, Jackson Lee
 */
public class StoreOrder implements Customizable {
    private ArrayList<Order> orderList;
    private Order currentOrder;
    public static StoreOrder storeOrder = new StoreOrder();

    /**
     * Completes the current order and creates a
     * new order as the current order.
     */
    public void completeCurrentOrder(){
        add(currentOrder);
        currentOrder = new Order();
    }

    /**
     * Sets current order if null and then gets current order
     * @return  current order
     */
    public Order getCurrentOrder(){
        if(orderList == null){
            orderList = new ArrayList<>();
        }
        if(currentOrder == null){
            currentOrder = new Order();
        }
        return currentOrder;
    }

    /**
     * Get list of valid order numbers
     * @return  ArrayList of Integers
     */
    public ArrayList<Integer> getOrderNumbers(){
        if(orderList == null){
            orderList = new ArrayList<>();
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(Order o : orderList){
            if(o.isValid()){
                list.add(o.getOrderNumber());
            }
        }
        return list;
    }

    /**
     * Generate a unique order id
     * @return  new order number
     */
    public int generateOrderId(){
        if(orderList == null){
            return 0;
        }
        return orderList.size() + 1;
    }

    /**
     * Get order based on order number
     * @param id    order number
     * @return      Order object if found, null otherwise
     */
    public Order getOrder(int id){
        for(Order o : orderList){
            if(o.getOrderNumber() == id){
                return o;
            }
        }
        return null;
    }

    /**
     * Cancel order with given order id
     * @param id    order number
     * @return      true if successfully cancelled, false otherwise
     */
    public boolean cancelOrder(int id){
        Order o = getOrder(id);
        return remove(o);
    }

    /**
     * Export valid orders to a file
     * @param file  File to write on
     */
    public void export(File file){
        try{
            PrintWriter pw = new PrintWriter(file);
            for (Order order : orderList){
                if(order.isValid()){
                    pw.println(order);
                }
            }
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Add Order object to list of store orders
     * @param obj   Object to add
     * @return      true if success, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        return orderList.add((Order) obj);
    }

    /**
     * Marks order as invalid (to keep id numbers unique)
     * @param obj   Order
     * @return      true if success, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        Order o = (Order) obj;
        if(o.isValid()){
            o.setInvalid();
            return true;
        }
        return false;
    }
}
