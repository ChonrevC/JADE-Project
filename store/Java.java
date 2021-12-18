import java.util.ArrayList;

public class Java extends Product{

    // Attributes
    protected Darkness darkness;
    protected ArrayList<Shot> shots;
    
    // Constructor
    public Java(String name, double price, double cost, Darkness darkness) {
        
        super(name, price, cost);
        this.darkness = darkness;

        shots = new ArrayList<>();

    }

    // Methods
    public void addShot(Shot shot) {
        shots.add(shot);
    }

    @Override
    public String toString() {
        String result = name + "(" + darkness + " with ";

        if(shots.size()== 0) result += "no shots";
        else
        {
            String separate = "";
            for(Shot s : shots)
            {
                result += separate + s;
                separate = ", ";
            }
        }

        result += ") $" + price;
        return result;
    }

}