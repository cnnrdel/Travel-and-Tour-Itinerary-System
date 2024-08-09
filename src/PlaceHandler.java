import java.util.ArrayList;

public class PlaceHandler {
	private static ArrayList<Place> placesList;
	
	public void save(Place p) {
		placesList.add(p);
	}
	
	public void displayAllPlaces() {
		for(Place place: placesList) {
			System.out.println(place.getName());
		}
	}
}

