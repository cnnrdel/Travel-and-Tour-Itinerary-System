import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		MainGUI mainGUI = new MainGUI();
		JFrame frame = mainGUI.getFrame();
		frame.setVisible(true);

//		FlightAPI flightAPI = new FlightAPI();
//		flightAPI.runFlightAPI("NYC", "SYD", "2024-08-11", 4);

		HotelAPI hotelAPI = new HotelAPI();
		hotelAPI.runHotelAPI(4, "NYC");


    }
}

