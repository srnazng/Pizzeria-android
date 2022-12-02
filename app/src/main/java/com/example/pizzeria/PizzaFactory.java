package com.example.pizzeria;

/**
 * The PizzaFactory interface defines the methods
 * to create different types of pizzas
 * @author Serena Zeng, Jackson Lee
 */
public interface PizzaFactory {
    /**
     * Create deluxe pizza
     * @return  new Deluxe object
     */
    Pizza createDeluxe();

    /**
     * Create Meatzza pizza
     * @return  new Meatzza object
     */
    Pizza createMeatzza();

    /**
     * Create BBQ Chicken pizza
     * @return  new BBQChicken object
     */
    Pizza createBBQChicken();

    /**
     * Create Build Your Own pizza
     * @return  new BuildYourOwn object
     */
    Pizza createBuildYourOwn();
}
