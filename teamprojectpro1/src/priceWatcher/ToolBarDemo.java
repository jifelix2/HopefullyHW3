package priceWatcher;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;
import java.util.Random;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
 
public class ToolBarDemo extends JPanel
                         implements ActionListener {
    protected JTextArea textArea;
    protected String newline = "\n";
    static final private String PREVIOUS = "hello";
    static final private String UP = "up";
    static final private String NEXT = "next";
    TextDemo textdemo = new TextDemo();
    protected JTextField textField;
    
    
    
    Item item = new Item();
	Random rand = new Random();
	double gen = 13.0;
	ConsoleUi ui = new ConsoleUi(item);
	String url = ("https://www.barnesandnoble.com/w/thrawn-timothy-zahn/1127203904?ean=9781984817617#");
    
 
    public ToolBarDemo() {
        super(new BorderLayout());
 
        //Create the toolbar.
        JToolBar toolBar = new JToolBar("Still draggable");
        addButtons(toolBar);
 
        //Create the text area used for output.  Request
        //enough space for 5 rows and 30 columns.
        textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
 
        //Lay out the main panel.
        setPreferredSize(new Dimension(450, 130));
        add(toolBar, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
    }
    private JLabel msgBar = new JLabel(" ");
	
    
    private void showMessage(String msg) {
        msgBar.setText(msg);
        
    }
 
    protected void addButtons(JToolBar toolBar) {
        JButton button = null;
 
        //first button
        button = makeNavigationButton("Back24", PREVIOUS,
                                      "Back to previous something-or-other",
                                      "Check");
        toolBar.add(button);
 
        //second button
        button = makeNavigationButton("Up24", UP,
                                      "Up to something-or-other",
                                      "add");
        toolBar.add(button);
 
        //third button
        button = makeNavigationButton("Forward24", NEXT,
                                      "Forward to something-or-other",
                                      "search");
        toolBar.add(button);
        button = makeNavigationButton("Forward24", NEXT,
                "Forward to something-or-other",
                "select first");
        toolBar.add(button);
        
        
        button = makeNavigationButton("Forward24", NEXT,
                "Forward to something-or-other",
                "select last");
        toolBar.add(button);
        
        
        button = makeNavigationButton("Forward24", NEXT,
                "Forward to something-or-other",
                "view web page ");
        toolBar.add(button);
        
        
        button = makeNavigationButton("Forward24", NEXT,
                "Forward to something-or-other",
                "remove ");
        toolBar.add(button);
        
        
        
    }
 
    protected JButton makeNavigationButton(String imageName,
                                           String actionCommand,
                                           String toolTipText,
                                           String altText) {
        //Look for the image.
        String imgLocation = "images/"
                             + imageName
                             + ".gif";
        URL imageURL = ToolBarDemo.class.getResource(imgLocation);
        
        //Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);
 
        if (imageURL != null) {                      //image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else {                                     //no image found
            button.setText(altText);
            System.err.println("Resource not found: "
                               + imgLocation);
        }
 
        return button;
    }
 
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String test = null;
        String description = null;
        int number = 0;
        // Handle each button.
        if (PREVIOUS.equals(cmd)) { //first button clicked
            description = "taken you to the previous <something>.";
            
    			try {
					 test =ui.changePrice(url, gen);
					 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			
            
            
            
        } else if (UP.equals(cmd)) { // second button clicked
        	JFrame frame = new JFrame("Provide URl ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
            //Add contents to the window.
             frame.add(new TextDemo());
     
            //Display the window.
            frame.pack();
            frame.setVisible(true);
            test = textField.getText();
            textArea.append(test+ newline);
            displayResult(test);
            
            
            
        } else if (NEXT.equals(cmd)) { // third button clicked
            description = "taken you to the next <something>.";
        }
        displayResult(test);
        
    }
    
    
 
    protected void displayResult(String actionDescription) {
        textArea.append(actionDescription + newline);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
   private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ToolBarDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new ToolBarDemo());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            createAndShowGUI();
           
            
            
            
            }
        });
    }
}
