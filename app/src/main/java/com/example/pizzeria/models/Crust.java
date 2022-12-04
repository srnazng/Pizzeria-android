package com.example.pizzeria.models;

/**
 * The Crust enum defines different types of crusts
 * @author Serena Zeng, Jackson Lee
 */
public enum Crust {
    DEEP_DISH, PAN, STUFFED, // Chicago style
    BROOKLYN, THIN, HAND_TOSSED; // NY style

    /**
     * Converts crust to String to be displayed on UI
     * @return  String containing crust name
     */
    @Override
    public String toString(){
        return (this.name().charAt(0) + this.name().substring(1).toLowerCase()).replace("_", " ");
    }
}
