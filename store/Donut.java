public class Donut extends Product {

    // Attributes
    protected Frosting frosting;
    protected boolean sprinkles;
    protected Filling filling;

    // Constructor
    public Donut(String name, double price, double cost, Frosting frosting, Filling filling, boolean sprinkles) {
        super(name, price, cost);
        this.frosting = frosting;
        this.sprinkles = sprinkles;
        this.filling = filling;

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
              + ") $" + price;
    }

}