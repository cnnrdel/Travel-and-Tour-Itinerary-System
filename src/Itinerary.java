import java.util.ArrayList;
import java.util.*;

public class Itinerary {
	private String tripName;
	private static PlaceHandler placesList;
	private static int counter = 0;
	private int uniqueCounter = 0;
	private String notes;
	
	public Itinerary() {
		this.tripName = "Itinerary " + String.valueOf(uniqueCounter);
		counter++;
		uniqueCounter = counter;
	}
	
	public String getTripName() {
		return tripName;
	}
	
	public void setTripName(String name) {
		tripName = name;
	}
	
	public PlaceHandler getPlacesList(){
		return placesList;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
