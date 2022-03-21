package store;

import java.util.ArrayList;

public class Java extends Product{

    // Attributes
    protected Darkness darkness;
    protected ArrayList<Shot> shots;
    
    // Constructor
    //      - requires the coffee's name, price, cost, and darkness to create
    public Java(String name, double price, double cost, Darkness darkness) {
        
        // All products have a name, price, and cost
        super(name, price, cost);

        // Coffees specifically have a certain darkness and an amount of shots
        this.darkness = darkness;
        shots = new ArrayList<>();

    }

    // Methods
    public void addShot(Shot shot) {
        shots.add(shot);
    }

    @Override
    public String toString() {

        String result = name + " (" + darkness + " with ";

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

        result += ")\t$" + price;
        return result;
    }

}