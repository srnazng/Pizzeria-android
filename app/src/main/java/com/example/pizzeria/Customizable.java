package com.example.pizzeria;

/**
 * The Customizable interface defines the methods
 * to add and remove items from a customizable object
 * @author Serena Zeng, Jackson Lee
 */
public interface Customizable {
    /**
     * Add object to Customizable object
     * @param obj   Object to add
     * @return      true if add successful, false otherwise
     */
    boolean add(Object obj);

    /**
     * Remove object from Customizable object
     * @param obj   Object to remove
     * @return      true if remove successful, false otherwise
     */
    boolean remove(Object obj);
}
