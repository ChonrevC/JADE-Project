package store;

// This file is to describe the qualities of that ALL products of the store should have

public class Product {
    
    // Attributes all products should have
    protected String name;
    protected double price;
    protected double cost;

    // Constructor
    //      - requires the product's name, price, and cost to create
    public Product(String name, double price, double cost) {
        this.name = name;
        this.price = price;
        this.cost = cost;
    }

    ///////////
    // Methods

    // name() method that returns the name of the product
    public String name() {
        return name;
    }


    @Override
    public String toString() {
        return name + "\t($" + price + ", ";
    }

}