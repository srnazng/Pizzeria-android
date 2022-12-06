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

    private static final int FIRST_LETTER = 0;
    private static final int SECOND_LETTER = 1;

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
        return (this.name().charAt(FIRST_LETTER) + this.name().substring(SECOND_LETTER).toLowerCase()).replace("_", " ");
    }
}
