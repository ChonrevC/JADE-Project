package gui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import store.Darkness;
import store.Donut;
import store.Filling;
import store.Frosting;
import store.Java;
// import store.Product;
import store.Shot;
import store.Store;


////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
public class MainWin extends JFrame{
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // PRIVATE VARIABLES
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private Store store;            // for containing the store
    private JLabel status;          // for the status bar & Exceptions
    private JLabel output;          // for showing the output of the store
    private File newFile;

    // Constants for jade files
    private final String NAME = "JADE";
    private final String EXT = "jade";
    // private final String VERSION = "1.1";
    private final String FILE_VERSION = "1.0";
    private final String MAGIC_COOKIE = "jadeCookie";       // used to tell a file is compatible
    

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // MAIN METHOD
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        MainWin myStore = new MainWin("JADE");
        myStore.setVisible(true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR for MainWin
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public MainWin(String winTitle) {
        
        // create a window for the JADE store
        super(winTitle);
        store = new Store("JADE");
        
        // make it so that it exits process whenever it closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the size of the window
        setSize(1000, 800);

        ////////////////////////////////////////////////////////////////////////////////////////////////
        // MENU stuff at the top
        ////////////////////////////////////////////////////////////////////////////////////////////////

        // Add a menu bar at the top of the window
        JMenuBar menuBar = new JMenuBar();

        //////////////////////////////////////
        // add all the menus onto the menu bar with their associated names
        
        // on the file menu, add in a menu item to quit
        JMenu mFile = new JMenu("File");
        JMenuItem mQuit = new JMenuItem("Leave");
        // Add in items to create new, open, save, and save as
        JMenuItem mNew = new JMenuItem("New");
        JMenuItem mOpen = new JMenuItem("Open");
        JMenuItem mSave = new JMenuItem("Save");
        JMenuItem mSaveAs = new JMenuItem("Save As");
        
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
        // add in new, open, save, & save as action listeners
        mNew.addActionListener(event -> onNewClick());
        mOpen.addActionListener(event -> onOpenClick());
        mSave.addActionListener(event -> onSaveClick());
        mSaveAs.addActionListener(event -> onSaveAsClick());
        
        ///////////////////////////////////////////////////////
        // Attach these menu items to their corresponding menus
        mFile.add(mQuit);
        mFile.add(mNew);
        mFile.add(mOpen);
        mFile.add(mSave);
        mFile.add(mSaveAs);

        mCreate.add(mJava);
        mCreate.add(mDonut);

        mHelp.add(mAbout);

        ///////////////////////////////////////
        // Attach all the menus to the menu bar
        menuBar.add(mFile);
        menuBar.add(mCreate);
        menuBar.add(mHelp);

        // Finalize the menu bar & set it to the top
        setJMenuBar(menuBar);

        ////////////////////////////////////////////////////////////////////////////////////////////////
        // TOOLBAR
        ////////////////////////////////////////////////////////////////////////////////////////////////
        //  - Below the menu bar
        //  - These are the centerpiece & main buttons for the store

        // Create toolbar and give it a name
        JToolBar toolBar = new JToolBar("JADE Controls");

        // Create the buttons with icons in "resources" folder

        // the new Store button
        JButton bNew = new JButton(new ImageIcon("gui/resources/newStore.png"));
            bNew.setActionCommand("Create a new store");
            bNew.setToolTipText("Create a new store with new items");
            toolBar.add(bNew);
            bNew.addActionListener(event -> onNewClick());

        // the open store button
        JButton bOpen = new JButton(new ImageIcon("gui/resources/openStore.png"));
            bOpen.setActionCommand("Open a store");
            bOpen.setToolTipText("Open a previously created store");
            toolBar.add(bOpen);
            bOpen.addActionListener(event -> onOpenClick());

        // the save store button
        JButton bSave = new JButton(new ImageIcon("gui/resources/saveStore.png"));
            bSave.setActionCommand("Save the store");
            bSave.setToolTipText("Save the current store");
            toolBar.add(bSave);
            bOpen.addActionListener(event -> onSaveClick());
        
        // the save as button
        JButton bSaveAs = new JButton(new ImageIcon("gui/resources/saveAsStore.png"));
            bSaveAs.setActionCommand("Saves store to new file");
            bSaveAs.setToolTipText("Saves the current store to a new file");
            toolBar.add(bSaveAs);
            bSaveAs.addActionListener(event -> onSaveAsClick());

        // separate file buttons from others
        toolBar.add(Box.createHorizontalStrut(20));

        // the create Java button
        JButton bJava = new JButton(new ImageIcon("gui/resources/coffee.png"));
            bJava.setActionCommand("Create new Java");
            bJava.setToolTipText("Create a new Coffee Selection");
            toolBar.add(bJava);
            bJava.addActionListener(event -> onJavaClick());
        
        // the create Donut button
        JButton bDonut = new JButton(new ImageIcon("gui/resources/donut.png"));
            bDonut.setActionCommand("Create new donut");
            bDonut.setToolTipText("Create a new donut selection");
            toolBar.add(bDonut);
            bDonut.addActionListener(event -> onDonutClick());

        // use to separate buttons by a certain amount of space
        toolBar.add(Box.createHorizontalStrut(20));

        // the about button
        JButton bAbout = new JButton(new ImageIcon("gui/resources/question.png"));
            bAbout.setActionCommand("About JADE Project");
            bAbout.setToolTipText("About the JADE Project");
            toolBar.add(bAbout);
            bAbout.addActionListener(event -> onAboutClick());

        // Now attach this toolbar to the top of the window below the menu
        getContentPane().add(toolBar, BorderLayout.PAGE_START);

        ////////////////////////////////////////////////////////////////////////////////////////////////
        // DISPLAY outputs in the center of the window
        ////////////////////////////////////////////////////////////////////////////////////////////////
        output = new JLabel();
            output.setFont(new Font("SansSerif", Font.BOLD, 12));
            add(output, BorderLayout.CENTER);
        
        /////////////////////////////////////////////////
        // Provide a status bar at the bottom of the page
        status = new JLabel();
            add(status, BorderLayout.PAGE_END);

        /////////////////////////////////////
        // initialize what will be displaying
        updateDisplay();

        /////////////////////////////////////////
        // Make everything in this JFrame visible
        setVisible(true);

    } // end of Constructor

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // ACTION LISTENERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////
    // Action Listener to create a new store
    protected void onNewClick() {
        String storeName = NAME;
        
        // check if user is already in a store
        if(store != null) JOptionPane.showInputDialog(this, "What is your store called?");

        // check if a store name was properly given
        if(storeName != null)
        {
            // make the store the new store with its name
            store = new Store(storeName);

            // create a new file for the store and make it untitled
            newFile = new File("Untitled.jade");
        }
        // update the display to show the empty contents of the new store
        updateDisplay();
    }

    //////////////////////////////////
    // Action listener to open a store
    protected void onOpenClick() {

        // create a file chooser to select a file to open
        final JFileChooser FC = new JFileChooser(newFile);

        // filter the files so only those ending with the extension are shown
        FileFilter jadeFilter = new FileNameExtensionFilter(NAME + " files", EXT);

        // attach the filter to the file chooser
        FC.addChoosableFileFilter(jadeFilter);
        FC.setFileFilter(jadeFilter);

        // check if the file chooser was approved
        int approval = FC.showOpenDialog(this);
        if(approval == JFileChooser.APPROVE_OPTION)
        {
            // create a variable to hold the selected file
            File filename = FC.getSelectedFile();

            // read the selected file with a try-with-resources to load it
            try (BufferedReader br = new BufferedReader(new FileReader(filename)))
            {
                // a saved jade file should have a magic cookie as the first line
                String magicCookie = br.readLine();

                // check if this magic cookie matches the magic cookie for the JADE store
                if(!magicCookie.equals(MAGIC_COOKIE))
                {
                    throw new RuntimeException("ERROR: Not a " + NAME + " file");
                }

                // check to see if versions is up to date
                String fileVersion = br.readLine();
                if(!fileVersion.equals(FILE_VERSION))
                {
                    throw new RuntimeException("File " + NAME + " out of date.");
                }

                // because of the try, if this fails, it will show an error in a window

                // create a variable to hold the store trying to open
                Store openStore = new Store(br);

                // replace the current store with the store trying to open
                store = openStore;

                // replace the current filename with the filename of the store trying to open
                newFile = filename;

            } catch(Exception e) {
                // print what went wrong
                e.printStackTrace(System.err);

                // open up a window telling the user it failed to open
                JOptionPane.showMessageDialog(
                    this,
                    "File " + newFile + " unable to open.\n" + e,
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
                );
            }

            // update the display to show the contents of the opened store
            updateDisplay();
        }

    }

    //////////////////////////////////
    // Action Listener to save a store
    protected void onSaveClick() {

        // use try with resources to save to the current file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFile)))
        {
            // make the first line the magic cookie
            bw.write(MAGIC_COOKIE + '\n');
            
            // make the second line the current compatible file version
            bw.write(FILE_VERSION + '\n');

            // make the rest of the file the save function of the store
            store.save(bw);

        } catch(Exception e) {
            // if failed, show user a window showing it failed to save
            JOptionPane.showMessageDialog(
                this,
                "Unable to save to " + newFile + '\n' + e,
                "Failed",
                JOptionPane.ERROR_MESSAGE
            );
        }

    }

    /////////////////////////////////////////
    // Action Listener to save as a new store
    protected void onSaveAsClick() {
        // create a file chooser variable to select a file
        final JFileChooser FC = new JFileChooser(newFile);

        // filter all the files to only show jade files
        FileFilter jadeFilter = new FileNameExtensionFilter(NAME + " files", EXT);

        // set the file filter to the file chooser
        FC.addChoosableFileFilter(jadeFilter);
        FC.setFileFilter(jadeFilter);

        // check if the file chosen to save to was accepted
        int approval = FC.showSaveDialog(this);
        if(approval == JFileChooser.APPROVE_OPTION)
        {
            // make a variable to hold the selected file
            // File filename = FC.getSelectedFile();

            // if the new file name doesn't end with jade, make it
            if(!newFile.getAbsolutePath().endsWith("." + EXT))
            {
                newFile = new File(newFile.getAbsolutePath() + "." + EXT);
            }

            // Now that the new file is created, save the current store to the file
            onSaveClick();
        }
    }

    ///////////////////////////////////////
    // Action listener to create a new Java
    protected void onJavaClick() {

        // test for any exceptions inputted when creating one
        try
        {
            // Have user give name, price, cost, & darkness of the drink, then create it
            //  - uses custom getString() & getDouble() methods
            String name = getString("What's your coffee's name?");
            double price = getDouble("Price of drink?");
            double cost = getDouble("How much money did it take to make?");
            Darkness darkness = (Darkness) selectFromArray("Darkness?",Darkness.values());
            Java java = new Java(name, price, cost, darkness);

            // add any shots the user wants to the drink
            while(true)
            {
                Shot shot = (Shot) selectFromArray("Want a shot?", Shot.values());
                if(shot.equals(Shot.none)) break;
                java.addShot(shot);
            }

            // add the new coffee to the shop & to the display
            store.addProduct(java);
            updateDisplay();

            // catch(CancelDialogException) is a custom exception 
            //  - ignores if user cancels
        } catch(CancelDialogException e) {} catch(Exception e) {
            status.setText("Failed to create drink: " + e.getMessage());
        }
    }

    ////////////////////////////////////////
    // Action listener to create a new donut
    protected void onDonutClick() {

        try
        {
            // get the donut's name, price, cost, frosting, & filling
            String name = getString("What's your donut's name?");
            double price = getDouble("Price of donut?");
            double cost = getDouble("How much money did it take to make?");
            Frosting frosting = (Frosting) selectFromArray("Select your frosting.", Frosting.values());
            Filling filling = (Filling) selectFromArray("Select your filling.", Filling.values());
            
            // Ask if they want sprinkles only if donut is not unfrosted
            boolean hasSprinkles;
            if(!frosting.equals(Frosting.unfrosted))
            {
                // create an array to show options for sprinkles, then ask user if they want sprinkles in a window
                String[] options = {"No sprinkles", "Sprinkles"};
                
                hasSprinkles = JOptionPane.showOptionDialog(this, "Want sprinkles?", "", JOptionPane.DEFAULT_OPTION,
                                                                JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == 1;
            }

            // if donut is unfrosted, don't have sprinkles
            else hasSprinkles = false;
            
            // add this new product to the store & show it in the store
            store.addProduct(new Donut(name, price, cost, frosting, filling, hasSprinkles));
            updateDisplay();

        } catch(CancelDialogException e) {} catch(Exception e) {
            status.setText("Failed to create donut: " + e.getMessage());
        }

    }

    //////////////////////////////////////////
    // Action listener to bring up the credits
    protected void onAboutClick() {

        // create a new blank window to hold the about section
        JDialog aboutWindow = new JDialog();

        // set the layout of the new window
        aboutWindow.getContentPane().setLayout(new BoxLayout(aboutWindow.getContentPane(), BoxLayout.PAGE_AXIS));

        // set the title of the window
        aboutWindow.setTitle("Java and Donut Express (JADE) Project");

        // set the size of the new window
        aboutWindow.setSize(600, 600);

        //////////////////////////////////////
        // Try & show the logo for the project. If can't, then don't
        try
        {
            // create a BufferedImage variable to hold a picture
            BufferedImage picture = ImageIO.read(new File("gui/resources/logo.png"));

            // place the image in its own label
            JLabel logo = new JLabel(new ImageIcon(picture));

            // place the image at the top center of the window
            logo.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            // attach the logo to the window
            aboutWindow.add(logo);

        } catch(IOException e) {}

        ////////////////////////////////////////
        // create a title in a JLabel using HTML
        JLabel title = new JLabel("<html>"
                                + "<p<font size =+3>Java and Donut Express (JADE) Project</font></p>"
                                + "<p></p>"
                                + "</html>");
        
        // set the title label at the center on its line, & attach the label
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        aboutWindow.add(title);

        ////////////////////////////////////////////////////
        // show copyright information in a JLabel using HTML
        JLabel copyright = new JLabel("<html>"
                                + "<p>Version 1.0</p>"
                                + "<p>Licensed under Gnu GPL 3.0</p>"
                                + "<p></p>"
                                + "<p><font size =+2>Credits:</font></p>"
                                + "<p>toString() functions by Professor G. Rice</p>"
                                + "<br/>"
                                + "</html>");

        // set the copyright label at the center of its line & attach it to the window
        copyright.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        aboutWindow.add(copyright);

        //////////////////////////////////
        // for any art not mine, put here:
        //////////////////////////////////

        ///////////////////////////////
        // add an ok button to exit out
        JButton ok = new JButton("OK");
        
        // set the ok button to the center of its line
        ok.setAlignmentX(JButton.CENTER_ALIGNMENT);

        // once the user clicks ok,the about window disappears
        ok.addActionListener(event -> aboutWindow.setVisible(false));

        // pack() makes it so that the window size changes as we add more info
        aboutWindow.pack();

        // Make the window visible to the user
        aboutWindow.setVisible(true);
    }

    ////////////////////////////////////////////
    // Action listener to exit the store/program
    protected void onQuitClick() {

        // the dispose() function releases everything in the winow
        dispose();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // CUSTOM METHODS & UTILITIES
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ///////////////////////////////////////////
    // custom exception for if user hits cancel
    protected class CancelDialogException extends Exception {
        // Constructor for custom exception
        public CancelDialogException() {
            super("Canceled!");
        }
    }

    ////////////////////////////////////////////////////////////
    // custom getString that provides a window to input a string
    //  - throws the custom exception if cancel is hit
    private String getString(String stringMessage) throws CancelDialogException {

        // store the message to user in a variable
        String output = stringMessage;

        while(true)
        {
            try
            {
                // output the message to user in a window
                output = JOptionPane.showInputDialog(this, output);

                // if nothing was put in, throw the exception
                if(output == null || output.length() == 0) throw new CancelDialogException();

                // note that since JOptionPane is also in the loop, break will exit the window
                break;
            } catch(CancelDialogException e) {
                throw e;    // here due to the throwing of the custom exception. Breaks out of loop
            } catch(Exception e) {
                // if the string isn't valid, repeat message and input
                output = "Sorry, this isn't valid\n" + stringMessage;
            }
        }
        
        // return what the user inputted in for the string
        return output;

    }

    ////////////////////////////////////////////////////////////
    // custom getDouble that provides a window to input a double
    private double getDouble(String doubleMessage) throws CancelDialogException {

        // create variable to hold temp message & result
        String message = doubleMessage;
        double result = 0.0;

        // always repeat till break or throw
        while(true)
        {
            try
            {
                // show the message in a window & have user input answer
                message = JOptionPane.showInputDialog(this, message);

                // if there's no answer, just cancel
                if(message == null) throw new CancelDialogException();
                
                // change the user answer into a double & break loop
                result = Double.parseDouble(message);
                break;

            } catch(CancelDialogException e) {
                throw e;
            } catch(Exception e) {
                message = "Invalid Answer\n" + doubleMessage;
            }
        }

        // return what was held inside the result
        return result;

    }

    ///////////////////////////////////////////////////////////////
    // Return an object, since this method extends to any data type
    // custom selection method that selects something from an array
    private Object selectFromArray(String message, Object[] options) throws CancelDialogException {

        // show the array in a combo box for selection
        JComboBox<Object> combo = new JComboBox<>();

        // set the style of the combo box (in this case, default)
        combo.setModel(new DefaultComboBoxModel<Object>(options));

        // create buttons in the combo box for user to click 
        int button = JOptionPane.showConfirmDialog(this, combo, message, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        // if the user selects cancel
        if(button == JOptionPane.CANCEL_OPTION) throw new CancelDialogException();

        // else return what the user selected
        return combo.getSelectedItem();

    }

    //////////////////////////////////////////////////////
    // function that shows/changes what the store displays
    private void updateDisplay() {

        // the JLabel setText displays things in a window via HTML
        //  - So we use the toString() function & replace symbols
        //     things in it to make it into HTML
        output.setText("<html>" + store.toString().replaceAll("<", "&lt;")
                                                  .replaceAll(">", "&gt;")
                                                  .replaceAll("\n", "<br/")
                                                                            + "</html>");

    }
    
}
