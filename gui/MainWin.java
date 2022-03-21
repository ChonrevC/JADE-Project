package gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

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

        ///////////////////////////////////////////////////////////////////////////////
        // MENU stuff at the top

        // Add a menu bar at the top of the window
        JMenuBar menuBar = new JMenuBar();

        //////////////////////////////////////
        // add all the menus onto the menu bar with their associated names
        
        // on the file menu, add in a menu item to quit
        JMenu mFile = new JMenu("File");
        JMenuItem mQuit = new JMenuItem("Quit");
        
        // on the create menu, add in the item options to create donut & Java
        JMenu mCreate = new JMenu("Create");
        JMenuItem mJava = new JMenuItem("Java");
        JMenuItem mDonut = new JMenuItem("Donut");

        // On the help menu, create a menu item for about
        JMenu mHelp = new JMenu("Help");
        JMenuItem mAbout = new JMenuItem("About");


        //////////////////////////////////////////////////////
        // Make events happen when you click on the menu items
        //  - These events will be our own functions
        mQuit.addActionListener(event -> onQuitClick());
        mJava.addActionListener(event -> onJavaClick());
        mDonut.addActionListener(event -> onDonutClick());
        mAbout.addActionListener(event -> onAboutClick());
        
        ///////////////////////////////////////////////////////
        // Attach these menu items to their corresponding menus
        mQuit.add(mFile);
        mJava.add(mCreate);
        mDonut.add(mCreate);
        mAbout.add(mHelp);

        ///////////////////////////////////////
        // Attach all the menus to the menu bar
        menuBar.add(mFile);
        menuBar.add(mCreate);
        menuBar.add(mHelp);

        // Finalize the menu bar & set it to the top
        setJMenuBar(menuBar);

        //////////////////////////////////////////////////////////////////////////////
        // TOOLBAR
        //  - Below the menu bar
        //  - These are the centerpiece & main buttons for the store

        // Create toolbar and give it a name
        JToolBar toolBar = new JToolBar("JADE Controls");

        // Create the 3 buttons with icons in "resources" folder

        // the create Java button
        JButton bJava = new JButton(new ImageIcon());
            bJava.setActionCommand("Create new Java");
            bJava.setToolTipText("Create a new Coffee Selection");
            toolbar.add(bJava);
            bJava.addActionListener(event -> onJavaClick());
        
        // the create Donut button
        JButton bDonut = new JButton(new ImageIcon());
            bDonut.setActionCommand("Create new donut");
            bDonut.setToolTipText("Create a new donut selection");
            toolbar.add(bDonut);
            bDonut.addActionListener(event -> onDonutClick());

        // the about button
        JButton bAbout = new JButton(new ImageIcon());
            bAbout.setActionCommand("About JADE Project");
            bAbout.setToolTipText("About the JADE Project");
            toolbar.add(bAbout);
            bAbout.addActionListener(event -> onAboutClick());

        // Now attach this toolbar to the top of the window below the menu
        getContentPane().add(toolBar, BorderLayout.PAGE_START);

    }



}
