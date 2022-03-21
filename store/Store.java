package store;

// File for holding the qualities this store will have

import java.util.ArrayList;

public class Store {

    // Attributes a store should have
    private String storeName;
    private ArrayList<Product> products;

    // Constructor
    //      - uses only the store's name in its construction
    public Store(String storeName) {
        
        this.storeName = storeName;
        products = new ArrayList<>();
        
    }

    // Methods

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