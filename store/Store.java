package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

// File for holding the qualities this store will have

import java.util.ArrayList;

public class Store {

    /////////////////////////////////
    // Attributes a store should have
    private String storeName;
    private ArrayList<Product> products;

    //////////////
    // Constructor
    //      - uses only the store's name in its construction
    public Store(String storeName) {
        
        this.storeName = storeName;
        products = new ArrayList<>();
        
    }

    //////////////////////
    // Save & Load Methods
    
    // Load Method
    public Store(BufferedReader in) throws IOException {
        // since only the store name is needed to construct a store,
        //  we can go ahead a construct the store instead of assign a value to the name
        this(in.readLine());

        // get the number of products to load
        int numProducts = Integer.parseInt(in.readLine());
        // load all the products
        for(int i = 0; i < numProducts; i++)
        {
            // in both java & donut saves, it says whether or not it's a donut or java
            String productType = in.readLine();

            // based on the type of product, load in a coffee or donut in the arrayList
            if(productType == Java.ID) products.add(new Java(in));
            else if(productType == Donut.ID) products.add(new Donut(in));

            else throw new IOException("Invalid Product " + productType);
        }
    }

    // Save Method
    public void save(BufferedWriter out) throws IOException {
        out.write(storeName + '\n');
        out.write("" + products.size() + '\n');

        for(Product p : products)   p.save(out);
    }


    //////////////////
    // Utility Methods
    
    // storeName() that returns the name of the store when called
    public String storeName() {
        return storeName;
    }

    // addProduct() to add a product for the store to sell
    public void addProduct(Product product) {
        products.add(product);
    }

    // numberOfProducts() to give the amount of different products the store is selling
    public int numberOfProducts() {
        return products.size();
    }

    // toString method for the products that give the names and prices of each different product
    public String toString(int productIdx) {
        return products.get(productIdx).toString();
    }

    // toString() method for the store that gives the customer options between different products
    @Override
    public String toString() {
        String result = "\nWelcome to " + storeName + "!\n\n";

        for(int i = 0; i < products.size(); ++i)
        {
            result += i + ") " + products.get(i) + "\n";
        }

        return result;
    }

}