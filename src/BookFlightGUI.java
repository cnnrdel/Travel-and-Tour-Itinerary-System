import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookFlightGUI extends JFrame {

    private Itinerary itinerary;
    private JTextField tfOrigin;
    private JTextField tfDestination;
    private JTextField tfDepartureDate;
    private JTextField tfAdults;
    private JList<String> flightList;
    private DefaultListModel<String> flightListModel;
    private FlightAPI flightAPI;
    private FlightHandler flightHandler;

    public BookFlightGUI(Itinerary itinerary) {
        this.itinerary = itinerary;
        this.flightAPI = new FlightAPI();
        this.flightHandler = new FlightHandler();
        initialize();
    }

    private void initialize() {
        setTitle("Book Flights");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Origin label and text field
        JLabel lblOrigin = new JLabel("Origin (NYC):");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblOrigin, gbc);

        tfOrigin = new JTextField(10);
        gbc.gridx = 1;
        add(tfOrigin, gbc);

        // Destination label and text field
        JLabel lblDestination = new JLabel("Destination (SYD):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblDestination, gbc);

        tfDestination = new JTextField(10);
        gbc.gridx = 1;
        add(tfDestination, gbc);

        // Departure Date label and text field
        JLabel lblDepartureDate = new JLabel("Departure Date (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblDepartureDate, gbc);

        tfDepartureDate = new JTextField(10);
        gbc.gridx = 1;
        add(tfDepartureDate, gbc);

        // Number of Adults label and text field
        JLabel lblAdults = new JLabel("Number of Adults:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblAdults, gbc);

        tfAdults = new JTextField(10);
        gbc.gridx = 1;
        add(tfAdults, gbc);

        // Fetch Flights button
        JButton btnFetchFlights = new JButton("Fetch Flights");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnFetchFlights, gbc);

        btnFetchFlights.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchFlights();
            }
        });

        // Flight List
        flightListModel = new DefaultListModel<>();
        flightList = new JList<>(flightListModel);
        flightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(flightList);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // Book button
        JButton btnBook = new JButton("Book");
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        add(btnBook, gbc);

        btnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookFlight();
            }
        });

        // Close button
        JButton btnClose = new JButton("Close");
        gbc.gridx = 1;
        add(btnClose, gbc);

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });
    }

    private void fetchFlights() {
        String origin = tfOrigin.getText();
        String destination = tfDestination.getText();
        String departureDate = tfDepartureDate.getText();
        int numOfAdults;

        try {
            numOfAdults = Integer.parseInt(tfAdults.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number of adults.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (origin.isEmpty() || destination.isEmpty() || departureDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Fetch flights and update the list
            ArrayList<Flight> flights = flightAPI.runFlightAPI(origin, destination, departureDate, numOfAdults);
            flightHandler.setFlights(flights);
            flightListModel.clear();

            for (Flight flight : flights) {
                flightListModel.addElement(flight.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching flight data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void bookFlight() {
        String selectedFlight = flightList.getSelectedValue();

        if (selectedFlight == null) {
            JOptionPane.showMessageDialog(this, "Please select a flight to book.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Implement booking logic here
        // Example:
        JOptionPane.showMessageDialog(this, "Booked flight: " + selectedFlight, "Booking Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }
}
