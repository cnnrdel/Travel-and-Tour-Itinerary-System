import java.util.ArrayList;

public class FlightHandler {
    private ArrayList<Flight> flights = new ArrayList<Flight>();

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void displayFlightList() {
        int count = 1;
        for (Flight flight : flights) {
            System.out.println("Flight " + count + ":");
            System.out.println(flight);
            count++;
        }
    }
}

