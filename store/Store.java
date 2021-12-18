import java.util.ArrayList;

public class Store {

    // Attributes
    protected String storeName;
    protected ArrayList<Product> products;

    // Constructor
    public Store(String storeName) {
        
        this.storeName = storeName;
        products = new ArrayList<>();
        
    }

    // Methods
    public String storeName() {
        return storeName;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public int numberOfProducts() {
        return products.size();
    }

    public String toString(int productIdx) {
        return products.get(productIdx).toString();
    }

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