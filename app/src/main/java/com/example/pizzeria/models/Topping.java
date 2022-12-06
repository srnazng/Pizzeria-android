package com.example.pizzeria.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * The Topping enum contains all possible pizza toppings
 * @author Serena Zeng, Jackson Lee
 */
public enum Topping {
    PEPPERONI, SAUSAGE, PINEAPPLE, MUSHROOM, BBQ_CHICKEN, GREEN_PEPPER,
    BROCCOLI, HAM, TOMATOES, ONION, CHEDDAR, PROVOLONE, BEEF;

    /**
     * Get list of all toppings
     * @return  ArrayList of Topping objects
     */
    public static ArrayList<Topping> getAvailableToppings(){
        return new ArrayList<>(List.of(PEPPERONI, SAUSAGE, PINEAPPLE, MUSHROOM,
                BBQ_CHICKEN, GREEN_PEPPER, BROCCOLI, HAM, TOMATOES,
                ONION, CHEDDAR, PROVOLONE, BEEF));
    }

    /**
     * Convert Topping to formatted String
     * @return  String of Topping
     */
    @NonNull
    @Override
    public String toString(){
        return (this.name().charAt(0) + this.name().substring(1).toLowerCase()).replace("_", " ");
    }
}
