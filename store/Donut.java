package store;

public class Donut extends Product {

    // Attributes
    protected Frosting frosting;
    protected boolean sprinkles;
    protected Filling filling;

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

    //toString() method by Professor George Rice at UTA
    @Override
    public String toString() {
        return name + " (" 
              + ((frosting != Frosting.unfrosted) ? "frosted with " : "") + frosting
              + ((filling != Filling.unfilled) ? " filled with " : " ") + filling
              + ((sprinkles) ? ", add sprinkles" : "")
              + ")\t$" + price;
    }

}