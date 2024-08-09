// import javax.swing.JFrame;

// public class MainGUI extends JFrame {
// 	public MainGUI() {
// 	}
	
		
// }

// package test;

// package src;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class MainGUI {

    private JFrame frame;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private GridBagConstraints gbc_btnPlus;
    private int buttonCount = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainGUI window = new MainGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        // Create the main panel
        JPanel mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel, BorderLayout.NORTH);
        GridBagLayout gbl_mainPanel = new GridBagLayout();
        gbl_mainPanel.columnWidths = new int[]{0, 0};
        gbl_mainPanel.rowHeights = new int[]{0, 0};
        gbl_mainPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_mainPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        mainPanel.setLayout(gbl_mainPanel);

        // Add JLabel
        JLabel lblNewLabel = new JLabel("Global Getaways");
        lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 32));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        mainPanel.add(lblNewLabel, gbc_lblNewLabel);

        // Create the panel that will hold the buttons
        buttonPanel = new JPanel();
        GridBagLayout gbl_buttonPanel = new GridBagLayout();
//        GridBagLayout.
        buttonPanel.setLayout(gbl_buttonPanel);
        
        // constraints for itineraries being at top of JScroll
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
//        frame.getContentPane().add(scrollPane, BorderLayout.NORTH);


        
        // Add the + button
        JButton btnNewButton = new JButton("+");
        gbc_btnPlus = new GridBagConstraints();
        gbc_btnPlus.insets = new Insets(10, 10, 10, 10);  // Add space around the + button
        gbc_btnPlus.gridx = 0;
        gbc_btnPlus.gridy = 1;
        gbc_btnPlus.anchor = GridBagConstraints.WEST;  // Align to the left
        mainPanel.add(btnNewButton, gbc_btnPlus);

        // Add action listener to the + button
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewButton();
                System.out.println("Adding Button for Itinerary #" + buttonCount);
            }
        });
    }

    /**
     * Adds a new button to the buttonPanel.
     */
    protected void addNewButton() {
        JButton newButton = new JButton("Itinerary #" + (++buttonCount));
        // ItineraryHandler ih = new ItineraryHandler;
        Itinerary newItinerary = new Itinerary();
        
        // Get screen width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        
        // Set the preferred size of the button based on the screen width
        int buttonWidth = screenWidth - 100; // Subtract some pixels to fit within screen
        newButton.setPreferredSize(new Dimension(buttonWidth, 30)); // Width: calculated width, Height: 30px
        
        GridBagConstraints gbc_newButton = new GridBagConstraints();
        gbc_newButton.insets = new Insets(10, 10, 10, 10);  // Add space around the buttons
        gbc_newButton.gridx = 0;
        gbc_newButton.gridy = buttonCount;
        gbc_newButton.anchor = GridBagConstraints.WEST;  // Align to the left
        gbc_newButton.fill = GridBagConstraints.HORIZONTAL; // Make it fill the horizontal space
        gbc_newButton.weightx = 1.0; // Allow it to grow horizontally if needed
        
        /*
         *  Itinerary Code
         */
        // buttonItineraryMap.put(newButton, newItinerary);
        newButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("Clicked: " + newButton.getLabel() + "!");
       		    handleButtonClick(newItinerary);
        	}
        });
        

        buttonPanel.add(newButton, gbc_newButton);

        // Resize the buttonPanel based on the new button
        buttonPanel.setPreferredSize(new Dimension(scrollPane.getViewport().getWidth(), buttonPanel.getPreferredSize().height + 40));
        
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
    private void handleButtonClick(Itinerary i) {
        System.out.println("Creating ItineraryGUI");
    	ItineraryGUI itineraryGUI = new ItineraryGUI(i);
        itineraryGUI.setVisible(true);
    }
}

