package com.example.pizzeria.models;

/**
 * The NYPizza class implements the PizzaFactory interface.
 * This class includes methods to create different types of pizzas with NY style crusts.
 * @author Serena Zeng, Jackson Lee
 */
public class NYPizza implements PizzaFactory{

    /**
     * Create NY Deluxe pizza with Brooklyn style crust
     * @return  NY Deluxe pizza object
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.BROOKLYN, false);
    }

    /**
     * Create NY Meatzza pizza with thin crust
     * @return  NY Meatzza pizza object
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.THIN, false);
    }

    /**
     * Create NY BBQ Chicken pizza with hand tossed crust
     * @return  NY BBQChicken pizza object
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.HAND_TOSSED, false);
    }

    /**
     * Create NY Build Your Own pizza with hand tossed crust
     * @return  NY BuildYourOwn pizza object
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.HAND_TOSSED, false);
    }
}
