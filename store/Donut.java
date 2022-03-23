package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Donut extends Product {

    /////////////
    // Attributes
    public static final String ID = "store.Donut";  // add this so the store knows to load a donut

    protected Frosting frosting;
    protected boolean sprinkles;
    protected Filling filling;

    //////////////
    // Constructor
    //      - requires donut's name, price, cost, frosting, filling, & whether it has sprinkles to create
    public Donut(String name, double price, double cost, Frosting frosting, Filling filling, boolean sprinkles) {
        
        // All products have a name, price, and cost
        super(name, price, cost);

        // Donuts specifically always have a frosting, sprinkle slection, and filling
        this.frosting = frosting;
        this.sprinkles = sprinkles;
        this.filling = filling;

        // Throw an exception if a donut has sprinkles, but is unfrosted
        if(sprinkles && frosting == Frosting.unfrosted)
        {
            throw new IllegalArgumentException("Donut must have frosting to have sprinkles");
        }
    }

    ///////////////////////////////////////////////////
    //toString() method by Professor George Rice at UTA
    @Override
    public String toString() {
        return name + " (" 
              + ((frosting != Frosting.unfrosted) ? "frosted with " : "") + frosting
              + ((filling != Filling.unfilled) ? " filled with " : " ") + filling
              + ((sprinkles) ? ", add sprinkles" : "")
              + ")\t$" + price;
    }

    ////////////////////////
    // Save and Load Methods

    // load method
    public Donut(BufferedReader in) throws IOException {
        // along with what all products load
        //  -recall that load is another constructor, so just super() here
        super(in);

        // load in what a donut has
        this.frosting = Frosting.valueOf(in.readLine());
        this.filling = Filling.valueOf(in.readLine());
        this.sprinkles = Boolean.parseBoolean(in.readLine());
    }

    // save method
    @Override
    public void save(BufferedWriter out) throws IOException {
        // save that it's a donut
        out.write(ID + '\n');
        
        // along with what all products also save
        super.save(out);

        // save what a donut should have
        out.write("" + frosting + '\n');
        out.write("" + filling + '\n');
        out.write("" + sprinkles + '\n');
    }

}