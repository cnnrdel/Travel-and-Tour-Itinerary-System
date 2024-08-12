import java.util.ArrayList;

public class ItineraryHandler {
	private static ArrayList<Itinerary> itineraryList = new ArrayList<Itinerary>();
	private static PlaceHandler placesList = new PlaceHandler();
	
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
