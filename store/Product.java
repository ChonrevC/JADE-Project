package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

// This file is to describe the qualities of that ALL products of the store should have

public class Product {
    
    //////////////////////////////////////
    // Attributes all products should have
    protected String name;
    protected double price;
    protected double cost;

    //////////////
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

    //////////////////////
    // Save & Load Methods

    // to load a product
    public Product(BufferedReader in) throws IOException {
        // make the name, cost, & price what it says in the file
        this.name = in.readLine();
        this.cost = Double.parseDouble(in.readLine());
        this.price = Double.parseDouble(in.readLine());
    }

    // to save a product
    public void save(BufferedWriter out) throws IOException {
        // save the name to the first line, cost to the second line, & price to third line
        out.write("" + name + '\n');
        out.write("" + cost + '\n');
        out.write("" + price + '\n');
    }

}