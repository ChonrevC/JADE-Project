package gui;

import javax.swing.JFrame;

import store.Darkness;
import store.Donut;
import store.Filling;
import store.Frosting;
import store.Java;
import store.Product;
import store.Shot;
import store.Store;


/////////////////////////////////////////////////////
public class MainWin extends JFrame{
    
    ///////////////////////////////////////////////////////////////////
    // Private Variables
    private Store store;

    ///////////////////////////////////////////////////////////////////
    // MAIN Method
    public static void main(String[] args) {
        MainWin myStore = new MainWin("JADE");
        myStore.setVisible(true);
    }

    ///////////////////////////////////////////////////////////////////
    // Constructor for the main window
    public MainWin(String winTitle) {
        
        // create a window for the JADE store
        super(winTitle);
        store = new Store("JADE");
        
        // make it so that it exits process whenever it closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the size of the window
        setSize(800, 450);

    }

    ///////////////////////////////////////////////////////////////////////////////
    // MENU stuff at the top
    

}
