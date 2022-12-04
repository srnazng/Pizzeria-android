package com.example.pizzeria.models;

/**
 * The Size enum defines the different size options of a pizza
 * @author Serena Zeng, Jackson Lee
 */
public enum Size {
    SMALL, MEDIUM, LARGE;

    public static Size toSize(String text){
        if(text.equalsIgnoreCase(SMALL.name())){
            return SMALL;
        }
        if(text.equalsIgnoreCase(MEDIUM.name())){
            return MEDIUM;
        }
        return LARGE;
    }
}
