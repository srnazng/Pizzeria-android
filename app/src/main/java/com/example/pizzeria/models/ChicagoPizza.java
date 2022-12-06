package com.example.pizzeria.models;

/**
 * The ChicagoPizza class implements the PizzaFactory interface.
 * This class includes methods to create different types of pizzas with Chicago style crusts.
 * @author Serena Zeng, Jackson Lee
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Create Chicago Deluxe pizza with Deep Dish crust
     * @return  Chicago Deluxe pizza object
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.DEEP_DISH, true);
    }

    /**
     * Create Chicago Meatzza pizza with Stuffed crust
     * @return  Chicago Meatzza pizza object
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.STUFFED, true);
    }

    /**
     * Create Chicago BBQ Chicken pizza with Pan crust
     * @return  Chicago BBQChicken pizza object
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.PAN, true);
    }

    /**
     * Create Chicago Build Your Own pizza with Pan crust
     * @return  Chicago BuildYourOwn pizza object
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.PAN, true);
    }
}
