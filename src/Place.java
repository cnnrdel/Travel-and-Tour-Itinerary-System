import java.io.Serializable;

public class Place implements Serializable{
	private static final long serialVersionUID = 1L;
	private String placeName;
	private String placeNotes;
	
	public Place(String name){
		placeName = name;
	}
	
	public void setName(String name) {
		placeName = name;
	}
	
	public String getName() {
		return placeName;
	}

	public void setNotes(String notes) {
		placeNotes = notes;
	}
	
	public String getNotes() {
		return placeNotes;
	}
}

