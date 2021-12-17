public class Product {
    
    // Attributes
    protected String name;
    protected double price;
    protected double cost;

    // Constructor
    public Product(String name, double price, double cost) {
        this.name = name;
        this.price = price;
        this.cost = cost;
    }

    // Methods
    public String name() {
        return name;
    }

    @Override
    public String toString) {
        return name + "($" + price + ", ";
    }

}