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
        Pizza pizza = new Deluxe(Crust.DEEP_DISH, true);
        return pizza;
    }

    /**
     * Create Chicago Meatzza pizza with Stuffed crust
     * @return  Chicago Meatzza pizza object
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza(Crust.STUFFED, true);
        return pizza;
    }

    /**
     * Create Chicago BBQ Chicken pizza with Pan crust
     * @return  Chicago BBQChicken pizza object
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken(Crust.PAN, true);
        return pizza;
    }

    /**
     * Create Chicago Build Your Own pizza with Pan crust
     * @return  Chicago BuildYourOwn pizza object
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn(Crust.PAN, true);
        return pizza;
    }
}
