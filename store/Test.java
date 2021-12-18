import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    // Attributes
    Store store;
    private static Scanner in = new Scanner(System.in);

    // Main (Shortened)
    public static void main(String[] args) {
        Test test = new Test();
        test.cli();
    }

    // Methods
    public void cli() {

        // Ask for the name of the store and create it
        System.out.print("What is the name of the store? ");
        String name = in.nextLine();

        String ignore = "";

        store = new Store(name);

        // Always loops
        while(true)
        {
            // Have the customer choose what to do in the store
            System.out.println(store);
            System.out.print("Options:\n 0) Exit\n 1) Add Java\n 2) Add Donut\n\nChoice? ");
            int choice = in.nextInt();
            ignore = in.nextLine();

            // if Exit
            if(choice == 0) 
            {
                System.out.println("Have a great day!\n");
                break;
            }

            // invalid answer
            if(choice < 0 || choice > 2)
            {
                System.err.println("\n\n### Error: Invalid Option: " + choice + "\n\n");
                continue;
            }

            // Ask for the name, price, and cost of the product being added
            System.out.print("Name: ");
            name = in.nextLine();
            System.out.print("Cost: ");
            double cost = in.nextDouble();
            System.out.print("Price: ");
            double price = in.nextDouble();

            // If the customer chose to add a new coffee
            if(choice == 1)
            {
                System.out.println("\nBrew Options\n==============");

                for(int i = 0; i < Darkness.values().length; ++i)
                {
                    System.out.println(i + ") " + Darkness.values()[i]);
                }

                System.out.println("\nWhich darkness? ");
                int darkness = in.nextInt();
                ignore = in.nextLine();

                Java java = new Java(name, price, cost, Darkness.values()[darkness]);

                // Keep adding shots until something
                while(true)
                {
                    System.out.println("Shot Options\n==============");

                    for(int i = 0; i < Shot.values().length; ++i)
                    {
                        System.out.println(i + ") " + Shot.values()[i]);
                    }

                    System.out.print("\nAdd shot (-1 when done)? ");
                    int shot = in.nextInt();
                    ignore = in.nextLine();

                    // if they selected -1 or something else
                    if(shot < 0 || shot >= Shot.values().length) break;

                    java.addShot(Shot.values()[shot]);
                }

                // Add the new coffee to the store and repeat
                store.addProduct(java);
                continue;

            }

            // If the customer chose to add a donut
            if(choice == 2)
            {
                // Have the customer select a frosting
                System.out.println("\nFrosting Options\n==============");
                for(int i = 0; i < Frosting.values().length; ++i)
                {
                    System.out.println(i + ") " + Frosting.values()[i]);
                }
                System.out.print("\nWhich frosting? ");
                int frosting = in.nextInt();
                ignore = in.nextLine();

                // Have the customer select a Filling option
                for(int i = 0; i < Filling.values().length; ++i)
                {
                    System.out.println(i + ") " + Filling.values()[i]);
                }
                System.out.print("\nWhich filling? ");
                int filling = in.nextInt();
                ignore = in.nextLine();

                // Ask if the customer wans sprinkles
                System.out.print("\nSprinkles (yes or no)? ");
                String sprinkles = in.nextLine();

                // Add the new donut to the store
                store.addProduct(new Donut(name, price, cost,
                                            Frosting.values()[frosting], 
                                            Filling.values()[filling], 
                                            sprinkles.toUpperCase().charAt(0) == 'Y'));

                
            }
        }

    }

}