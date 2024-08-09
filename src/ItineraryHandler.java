import java.util.ArrayList;

public class ItineraryHandler {
	private static ArrayList<Itinerary> itineraryList;
	private static PlaceHandler placesList;
	
	private static ItineraryHandler ih_single_instance = null;
	
	private ItineraryHandler() {
		
	}
	
	public static synchronized ItineraryHandler getInstance() {
		if (ih_single_instance == null) {
			ih_single_instance = new ItineraryHandler();
		}
		return ih_single_instance;
	}
	
	public void addItinerary(Itinerary itinerary) {
		itineraryList.add(itinerary);
	}
	
	public void deleteItinerary() {
		
	}
	
	public void displayItineraryList() {
		for (Itinerary itinerary: itineraryList) {
			System.out.println(itinerary.getTripName());
			itinerary.getPlacesList().displayAllPlaces();
		}
	}
}
