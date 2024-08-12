import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ItineraryGUI extends JFrame {

    private MainGUI mainGUI;
    private Itinerary itinerary;
    private JTextField textField;
    private JTextField txtItinerary;

		public ItineraryGUI() {
			// initialize();
		}

    public ItineraryGUI(Itinerary itinerary, MainGUI mainGUI) {
        this.itinerary = itinerary;
        this.mainGUI = mainGUI;
        initialize(itinerary);
    }

    private void initialize(Itinerary i) {
        // Set the title and default close operation
        setTitle("Itinerary Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        // Use GridBagLayout for better control over component placement
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Back Button
        JButton btnBackButton = new JButton("←");
        gbc.gridx = 1;
        gbc.gridy = 0;
        getContentPane().add(btnBackButton, gbc);
        
        // ActionListener for Back Button
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
        
        // Delete Button
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
                mainGUI.removeItineraryButton(itinerary);
                dispose();  
            }
        });

        // Notes Label
        JLabel lblNotes = new JLabel("Notes:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        getContentPane().add(lblNotes, gbc);

        // Notes TextField
        textField = new JTextField();
        textField.setText(i.getNotes());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        getContentPane().add(textField, gbc);

        // Itinerary NaTextFieldme 
        txtItinerary = new JTextField();
        txtItinerary.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
        txtItinerary.setText(i.getName()); // Set text based on Itinerary object
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        getContentPane().add(txtItinerary, gbc);

        // Panel for additional controls
        JPanel panel = new JPanel();
        GridBagLayout gbl_panel = new GridBagLayout();
        panel.setLayout(gbl_panel);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        getContentPane().add(panel, gbc);

        // Add more controls to the panel if needed
        JButton btnPlace1 = new JButton("P1");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(btnPlace1, gbc);

        JLabel lblArrow = new JLabel("→");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(lblArrow, gbc);

        JButton btnAddPlace = new JButton("+");
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(btnAddPlace, gbc);

    }
}
