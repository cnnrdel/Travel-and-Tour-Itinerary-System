import java.util.ArrayList;

public class HotelHandler {
    private ArrayList<Hotel> hotels = new ArrayList<Hotel>();

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void displayHotelList() {
        int count = 1;
        for (Hotel hotel : hotels) {
            System.out.println("Hotel " + count + ":");
            System.out.println(hotel);
            count++;
        }
    }
}
