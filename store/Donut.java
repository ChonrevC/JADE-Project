public class Donut extends Product {

    // Attributes
    protected Frosting frosting;
    protected boolean sprinkles;
    protected Filling filling;

    // Constructor
    public Donut(String name, double price, double cost, Frosting frosting, boolean sprinkles, Filling filling) {
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
              + ((frosting != Frosting.Unfrosted) ? "frosted with " : "") + frosting
              + ((filling != Filling.Unfilled) ? " filled with " : " ") + filling
              + ((sprinkles) ? " add sprinkles" : "")
              + ") $" + price;
    }

}