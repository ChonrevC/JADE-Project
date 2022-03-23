package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Java extends Product{

    /////////////
    // Attributes
    public static final String ID = "store.Java";   // add this so the store knows to load a java

    protected Darkness darkness;
    protected ArrayList<Shot> shots;
    
    //////////////
    // Constructor
    //      - requires the coffee's name, price, cost, and darkness to create
    public Java(String name, double price, double cost, Darkness darkness) {
        
        // All products have a name, price, and cost
        super(name, price, cost);

        // Coffees specifically have a certain darkness and an amount of shots
        this.darkness = darkness;
        shots = new ArrayList<>();

    }

    //////////
    // Methods
    public void addShot(Shot shot) {
        shots.add(shot);
    }

    /////////
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

    //////////////////////
    // Save & Load Methods
    public Java(BufferedReader in) throws IOException {
        // along with what all products load in
        super(in);

        // load in the qualities of a coffee
        this.darkness = Darkness.valueOf(in.readLine());
        this.shots = new ArrayList<>();
        
        // load in all the shots a coffee has based on the number of shots saved
        int numShots = Integer.parseInt(in.readLine());
        for(int i = 0; i < numShots; i++)
        {
            shots.add(Shot.valueOf(in.readLine()));
        }
    }

    @Override
    public void save(BufferedWriter out) throws IOException {
        // save that this is a java
        out.write(ID + '\n');

        // along with what all products save
        super.save(out);

        // save the qualities a java has
        out.write("" + darkness + '\n');
        out.write("" + shots.size() + '\n');    // save the shots size so we know the amount of shots to load in

        // for all shots in the shots arrayList, save it
        for(Shot s : shots)     out.write("" + s + '\n');
    }

}