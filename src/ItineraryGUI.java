import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ItineraryGUI extends JFrame {
    private MainGUI mainGUI;
    private Itinerary itinerary;
    private JTextArea textField;
    private JTextField txtItinerary;
    private ItineraryHandler handler;

    /***********************
     *      CONSTRUCTORS
    ***********************/
    /*
     * EMPTY
     */
	public ItineraryGUI() {
	    // initialize();
	}

    /*
     * PROPER
     */
    public ItineraryGUI(Itinerary itinerary, MainGUI mainGUI, ItineraryHandler handler) {
        this.itinerary = itinerary;
        this.mainGUI = mainGUI;
        this.handler = handler;
        initialize(itinerary);
    }

    /********************************
     *          POPULATE GUI
     *******************************/
    private void initialize(Itinerary i) {
        // Set the title and default close operation
        setTitle("Itinerary Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        /*
         * BACK BUTTON
         */
        JButton btnBackButton = new JButton("←");
        gbc.gridx = 1;
        gbc.gridy = 0;
        getContentPane().add(btnBackButton, gbc);
        
        // back button add listener
        btnBackButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String itineraryName = txtItinerary.getText();
                String notes = textField.getText();

                i.setTripName(itineraryName);
                i.setNotes(notes);

                mainGUI.updateItineraryButtonLabel(itinerary, itineraryName);
                dispose();
            }
            /* 
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            */
        });
        
        /*
         * DELETE BUTTON
         */
        JButton btnDelete = new JButton("DELETE");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 6));
        btnDelete.setForeground(Color.RED);
        gbc.gridx = 2;
        gbc.gridy = 0;
        getContentPane().add(btnDelete, gbc);
        
        // ActionListener for Delete Button
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtItinerary.setText(""); 
                textField.setText(""); 
                mainGUI.removeItineraryButton(itinerary,handler);
                dispose();  
            }
        });

        /*****************************
         *       LABELS/TEXTFIELDS
         ****************************/
        /*
         * NOTES
         */
        JLabel lblNotes = new JLabel("Notes:");
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        getContentPane().add(lblNotes, gbc);
//
//        textField = new JTextField( 20);
//        textField.setText(i.getNotes());
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.gridwidth = 3;
//        gbc.gridheight = 3;
//        getContentPane().add(textField, gbc);

        textField = new JTextArea(5, 20); // Create a JTextArea with 5 rows and 20 columns
        textField.setText(i.getNotes());
        textField.setLineWrap(true); // Enable line wrapping
        textField.setWrapStyleWord(true); // Wrap at word boundaries
        textField.setPreferredSize(new Dimension(200, 100)); // Set the preferred size

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        getContentPane().add(new JScrollPane(textField), gbc); // Add the text area inside a scroll pane

        /*
         * TITLE
         */
        txtItinerary = new JTextField();
        txtItinerary.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
        txtItinerary.setText(i.getName()); // Set text based on Itinerary object
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        getContentPane().add(txtItinerary, gbc);

        /****************************
         * DECORATION
         ****************************/
        JPanel panel = new JPanel();
        GridBagLayout gbl_panel = new GridBagLayout();
        panel.setLayout(gbl_panel);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        getContentPane().add(panel, gbc);

//        JButton btnPlace1 = new JButton("P1");
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        panel.add(btnPlace1, gbc);

        JLabel lblArrow = new JLabel("→");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(lblArrow, gbc);

//        JButton btnAddPlace = new JButton("+");
//        gbc.gridx = 2;
//        gbc.gridy = 0;
//        panel.add(btnAddPlace, gbc);

        JPanel bottomPanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.PAGE_END; // Align at the bottom of the window
        getContentPane().add(bottomPanel, gbc);

        bottomPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcBottom = new GridBagConstraints();
        gbcBottom.insets = new Insets(5, 5, 5, 5);

        /************************************
         *          BOOKING
         ***********************************/
        /*
         * HOTEL
         */
        JButton bookHotel = new JButton("Hotel");
        gbcBottom.gridx = 0;
        gbcBottom.gridy = 0;
        bottomPanel.add(bookHotel, gbcBottom);

        bookHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookHotelGUI(itinerary).setVisible(true);
            }
        });

        /*
         * TRAIN
         */
        JButton btnBookTrain = new JButton("Train");
        gbcBottom.gridx = 1;
        gbcBottom.gridy = 0;
        bottomPanel.add(btnBookTrain, gbcBottom);
        btnBookTrain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookTrainGUI(itinerary).setVisible(true);

                // Ensure the text field is updated with the latest notes
//                textField.setText(itinerary.getNotes());
                System.out.println("ITINERARY: " + itinerary.getNotes());

//                txtItinerary.setText(itinerary.getNotes()); // Set text based on Itinerary objec
//                getContentPane().add(txtItinerary, gbc);
            }


        });

        /*
         * BUS
         */
        JButton btnBus = new JButton("Bus");
        gbcBottom.gridx = 2;
        gbcBottom.gridy = 0;
        bottomPanel.add(btnBus, gbcBottom);
        btnBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookBusGUI(itinerary).setVisible(true);
            }
        });

        /*
         * FLIGHT
         */
        JButton btnFlight = new JButton("Flight");
        gbcBottom.gridx = 3;
        gbcBottom.gridy = 0;
        bottomPanel.add(btnFlight, gbcBottom);
        btnFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookFlightGUI(itinerary).setVisible(true);
            }
        });


    }
}
