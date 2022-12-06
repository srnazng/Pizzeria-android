package com.example.pizzeria.models;

import androidx.annotation.NonNull;

/**
 * The Crust enum defines different types of crusts
 * @author Serena Zeng, Jackson Lee
 */
public enum Crust {
    DEEP_DISH, PAN, STUFFED, // Chicago style
    BROOKLYN, THIN, HAND_TOSSED; // NY style

    private static final int FIRST_LETTER = 0;
    private static final int SECOND_LETTER = 1;

    /**
     * Converts crust to String to be displayed on UI
     * @return  String containing crust name
     */
    @NonNull
    @Override
    public String toString(){
        return (this.name().charAt(FIRST_LETTER) + this.name().substring(SECOND_LETTER).toLowerCase()).replace("_", " ");
    }
}
