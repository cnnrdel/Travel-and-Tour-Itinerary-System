import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookTrainGUI extends JFrame {

    private JTextField tfDateTime;
    private JTextField tfStationCode;
    private JList<String> trainList;
    private DefaultListModel<String> trainListModel;
    private TrainAPI trainAPI = new TrainAPI();
    private TrainHandler trainHandler = new TrainHandler();
//    private Itinerary itinerary = new Itinerary();
    private Itinerary itinerary;


    public BookTrainGUI(Itinerary itinerary) {
//        this.itinerary = new Itinerary();
        this.itinerary = itinerary;
        this.trainAPI = new TrainAPI();
        this.trainHandler = new TrainHandler();
        initialize();
    }

    private void initialize() {
        setTitle("Book Trains");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // DateTime label and text field
        JLabel lblDateTime = new JLabel("Date and Time (2024-08-12T20:30:00+01:00):");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblDateTime, gbc);

        tfDateTime = new JTextField(20);
        gbc.gridx = 1;
        add(tfDateTime, gbc);

        // Station Code label and text field
        JLabel lblStationCode = new JLabel("Station Code (RMD):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblStationCode, gbc);

        tfStationCode = new JTextField(10);
        gbc.gridx = 1;
        add(tfStationCode, gbc);

        // Fetch Trains button
        JButton btnFetchTrains = new JButton("Fetch Trains");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(btnFetchTrains, gbc);

        btnFetchTrains.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchTrains();
            }
        });

        trainListModel = new DefaultListModel<>();
        trainList = new JList<>(trainListModel);
        trainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(trainList);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);


        JButton btnBook = new JButton("Book");
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        add(btnBook, gbc);

        btnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookTrain();
            }
        });


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

    private void fetchTrains() {
        String dateTime = tfDateTime.getText();
        String stationCode = tfStationCode.getText();

        if (dateTime.isEmpty() || stationCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Fetch trains and update the list
            ArrayList<Train> trains = trainAPI.runTrainsAPI(dateTime, stationCode);
            trainHandler.setTrainList(trains);
            trainListModel.clear();

            for (Train train : trains) {
                trainListModel.addElement(train.toString());
            }

            if (trains.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No trains found for the given criteria.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching train data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void bookTrain() {
        String selectedTrain = trainList.getSelectedValue();
        System.out.println(selectedTrain);

        if (selectedTrain == null) {
            JOptionPane.showMessageDialog(this, "Please select a train to book.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Booked train: " + selectedTrain, "Booking Confirmation", JOptionPane.INFORMATION_MESSAGE);
        String currentNotes = itinerary.getNotes();
        System.out.println("notes:" + currentNotes);
        String updatedNotes = currentNotes + '\n' + selectedTrain;
        System.out.println("UPDated Notes:" + updatedNotes);
        itinerary.setNotes(updatedNotes);
        ItineraryGUI i = new ItineraryGUI();
        i.setVisible(true);
        System.out.println("notes:" + itinerary.getNotes());
        dispose();


    }

}