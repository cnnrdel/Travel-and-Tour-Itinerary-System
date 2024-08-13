import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BookHotelGUI extends JFrame {
    private Itinerary itinerary;
    private HotelAPI hotelAPI;
    private DefaultListModel<String> hotelDisplay;
    private HotelHandler hotelHandler = new HotelHandler();
    private JList<String> hotelList;

    public BookHotelGUI(Itinerary itinerary) {
        this.itinerary = itinerary;
        this.hotelAPI = new HotelAPI();
        this.hotelHandler = new HotelHandler();

        String cityName = askForCityName();
        if (cityName != null && !cityName.isEmpty()) {
            hotelHandler.setHotels(hotelAPI.runHotelAPI(5, cityName));
            initialize();
        } else {
            JOptionPane.showMessageDialog(this, "City name cannot be empty.");
            dispose();
        }
    }

    private String askForCityName() {
        return JOptionPane.showInputDialog(this, "Enter the city name (NYC):", "City Name", JOptionPane.QUESTION_MESSAGE);
    }

    private void initialize() {
        setTitle("Book Hotel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());

        hotelDisplay = new DefaultListModel<>();
        for (Hotel hotel : hotelHandler.getHotels()) {
            hotelDisplay.addElement(hotel.getHotelName() + " - " + hotel.getHotelAddress());
        }

        hotelList = new JList<>(hotelDisplay);
        JScrollPane scrollPane = new JScrollPane(hotelList);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        /*************************
         *      BOOK BUTTON
         *************************/
        JButton btnBook = new JButton("Book");
        btnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = hotelList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Hotel selectedHotel = hotelHandler.getHotels().get(selectedIndex);
                    itinerary.setHotel(selectedHotel);
                    JOptionPane.showMessageDialog(BookHotelGUI.this, "Hotel booked: " + selectedHotel.getHotelName());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(BookHotelGUI.this, "Please select a hotel to book.");
                }
            }
        });

        getContentPane().add(btnBook, BorderLayout.SOUTH);
    }
}
