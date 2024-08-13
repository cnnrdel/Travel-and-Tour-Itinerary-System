import java.util.ArrayList;
import java.io.Serializable;
import java.util.*;

public class Itinerary implements Serializable{
	private static final long serialVersionUID = 1L;
	private String tripName;
	private static PlaceHandler placesList = new PlaceHandler();
	private static int counter = 0;
	private int uniqueCounter = 0;
	private String notes;
	private Hotel hotel;
	private Bus bus;
	private Flight flight;
	private Train train;
	
	public Itinerary() {
		// this.tripName = "Itinerary " + String.valueOf(uniqueCounter);
		counter++;
		uniqueCounter = counter;

		// test
		this.tripName = "Itinerary " + counter;
	}

	public String getName() {
		return tripName;
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

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}
}
