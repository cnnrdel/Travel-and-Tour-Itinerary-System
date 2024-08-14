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
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class MainGUI {
    private GridBagLayout gbl_buttonPanel;
    private JLabel titleLbl;
    private GridBagConstraints gbc;
    private JButton addItineraryClick;
    private JFrame frame;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private GridBagConstraints gbc_btnPlus;
    private int buttonCount = 0;
    private GridBagConstraints gbc_lblNewLabel;
    private GridBagLayout gbl_mainPanel;
    private JPanel mainPanel;
    private Map<Itinerary, JButton> itineraryButtonMap = new HashMap<>();

    /**
     * Launch the application.
     */
    public JFrame getFrame(){
        return frame;
    }

    /**
     * Create the application.
     */
    public MainGUI(ItineraryHandler handler) {
        initialize(handler);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(ItineraryHandler handler) {
        handler.loadItinerariesFromFile();
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                handler.saveItinerariesToFile();
                System.exit(0);
            }
        });

        /*****************************
         *          DECORATE
         ***************************/
        mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel, BorderLayout.NORTH);
        gbl_mainPanel = new GridBagLayout();
        gbl_mainPanel.columnWidths = new int[]{0, 0};
        gbl_mainPanel.rowHeights = new int[]{0, 0};
        gbl_mainPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_mainPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        mainPanel.setLayout(gbl_mainPanel);

        // Add JLabel
        titleLbl = new JLabel("Global Getaways");
        titleLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 32));
        gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        mainPanel.add(titleLbl, gbc_lblNewLabel);

        // Create the panel that will hold the buttons
        buttonPanel = new JPanel();
        gbl_buttonPanel = new GridBagLayout();
        buttonPanel.setLayout(gbl_buttonPanel);

        // constraints for itineraries being at top of JScroll
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        

        /*
         * ADD ITINERRAY BUTTON
         */
        addItineraryClick = new JButton("+");
        gbc_btnPlus = new GridBagConstraints();
        gbc_btnPlus.insets = new Insets(10, 10, 10, 10);  // Add space around the + button
        gbc_btnPlus.gridx = 0;
        gbc_btnPlus.gridy = 1;
        gbc_btnPlus.anchor = GridBagConstraints.WEST;  // Align to the left
        mainPanel.add(addItineraryClick, gbc_btnPlus);

        // Add action listener to the add itinerary button
        addItineraryClick.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItinerary(handler);
                System.out.println("Adding Button for Itinerary #" + buttonCount);
            }
        });
        
        
        // Load existing itineraries and create buttons for them
        int i = 0;
        for (Itinerary itinerary : handler.getItineraryList()) {
            addNewButton1(itinerary);
            i++;
            System.out.println(i);
        }
    }

    /**
     * ADD NEW ITINERARY TO MAIN GUI
     */
    protected void addItinerary(ItineraryHandler handler) {
        JButton newButton = new JButton("Itinerary #" + (++buttonCount));
        Itinerary newItinerary = new Itinerary();
        handler.addItinerary(newItinerary);
        
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
         *  OPEN ITINERARY
         */
        newButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("Clicked: " + newButton.getLabel() + "!");
       		    openItineraryClick(newItinerary);
        	}
        });
    }
     /* */
        protected void addNewButton1(Itinerary newItinerary) {
            JButton newButton = new JButton(newItinerary.getName());
            
            
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
            
            
             //  Itinerary Code
             
            newButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Clicked: " + newButton.getLabel() + "!");
                       openItineraryClick(newItinerary);
                }
            });
        

        buttonPanel.add(newButton, gbc_newButton);
        itineraryButtonMap.put(newItinerary, newButton);
        buttonPanel.setPreferredSize(new Dimension(scrollPane.getViewport().getWidth(), buttonPanel.getPreferredSize().height + 40));        
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    /*
     * CONSTRUCT ITINERARY GUI
     */
    private void openItineraryClick(Itinerary i) {
        System.out.println("Creating ItineraryGUI");
    	ItineraryGUI itineraryGUI = new ItineraryGUI(i, this);
        itineraryGUI.setVisible(true);
    }

    /*
     * DELETE ITINERARY
     */
    protected void removeItineraryButton(Itinerary itinerary) {
        JButton buttonToRemove = itineraryButtonMap.get(itinerary);
        if (buttonToRemove != null) {
            buttonPanel.remove(buttonToRemove);
            buttonPanel.revalidate();
            buttonPanel.repaint();
            itineraryButtonMap.remove(itinerary);
            buttonCount--;
        }
    }


    /*
     * UPDATE ITINERARY
     */
    public void updateItineraryButtonLabel(Itinerary itinerary, String newLabel) {
        JButton buttonToUpdate = itineraryButtonMap.get(itinerary);
        if (buttonToUpdate != null) {
            buttonToUpdate.setText(newLabel);
        }
    }
}

