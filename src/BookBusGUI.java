import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookBusGUI extends JFrame {
    private BusAPI busAPI = new BusAPI();
    private Itinerary itinerary;
    private BusHandler busHandler = new BusHandler();
    private DefaultListModel<String> listModel;
    private JList<String> busList;

    public BookBusGUI(Itinerary itinerary) {
        this.itinerary = itinerary;
        String date = askForDate();
        if (date != null && !date.isEmpty()) {
            busHandler.setBuses(busAPI.runBusAPI(date));
            initialize();
        } else {
            JOptionPane.showMessageDialog(this, "Date cannot be empty.");
            dispose();
        }
    }

    private String askForDate() {
        return JOptionPane.showInputDialog(this, "Enter the date (YYYY-MM-DD):", "Date", JOptionPane.QUESTION_MESSAGE);
    }

    private void initialize() {
        setTitle("Book Bus");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        for (Bus bus : busHandler.getBuses()) {
            listModel.addElement(bus.getFromPoint() + " to " + bus.getToPoint() + " - Departure: " + bus.getDepartureTime());
        }

        busList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(busList);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton btnBook = new JButton("Book");
        btnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = busList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Bus selectedBus = busHandler.getBuses().get(selectedIndex);
                    itinerary.setBus(selectedBus);
                    JOptionPane.showMessageDialog(BookBusGUI.this, "Bus booked: " + selectedBus.getFromPoint() + " to " + selectedBus.getToPoint());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(BookBusGUI.this, "Please select a bus to book.");
                }
            }
        });
        panel.add(btnBook);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });
        panel.add(btnClose);
    }
}
