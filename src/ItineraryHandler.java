import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ItineraryHandler implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final String FILE_PATH = "itineraries.ser";
	private static ArrayList<Itinerary> itineraryList = new ArrayList<Itinerary>();
	private static PlaceHandler placesList = new PlaceHandler();
	
	public void addItinerary(Itinerary itinerary) {
		itineraryList.add(itinerary);
	}
	
	public void deleteItinerary(String tripName) {
		Itinerary itineraryToRemove = null;
        for (Itinerary itinerary : itineraryList) {
            if (itinerary.getTripName().equals(tripName)) {
                itineraryToRemove = itinerary;
                break;
            }
        }
        if (itineraryToRemove != null) {
            itineraryList.remove(itineraryToRemove);
            System.out.println("Itinerary '" + tripName + "' has been removed.");
        } else {
            System.out.println("Itinerary '" + tripName + "' not found.");
        }
	}
	
	public void displayItineraryList() {
		for (Itinerary itinerary: itineraryList) {
			System.out.println(itinerary.getTripName());
			itinerary.getPlacesList().displayAllPlaces();
		}
	}

	public void saveItinerariesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(itineraryList);
            System.out.println("Itineraries saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void loadItinerariesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            itineraryList = (ArrayList<Itinerary>) ois.readObject();
            System.out.println("Itineraries loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved itineraries found.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

	public ArrayList<Itinerary> getItineraryList() {
		return itineraryList;
	}
}
