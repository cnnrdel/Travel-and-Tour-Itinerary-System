public class Flight {
    private String origLoc;
    private String destLoc;
    private String price;
    private int persons;
    private String departureTime;
    private String arrivalTime;
    private String airline;
    private String cabin;
    private String currency;
    private String numberOfStops;
    private String flightNumber;
    private String aircraft;
    private String description;

    public void displayFlights(){
        System.out.println("Flight Details:");
        System.out.println("Origin: " + origLoc);
        System.out.println("Destination: " + destLoc);
        System.out.println("Price: " + price);
        System.out.println("Persons: " + persons);
        System.out.println("Departure Time: " + departureTime);
        System.out.println("Arrival Time: " + arrivalTime);
        System.out.println("Airline: " + airline);
        System.out.println("Cabin: " + cabin);
        System.out.println("Currency: " + currency);
        System.out.println("Number of Stops: " + numberOfStops);
        System.out.println("FlightNumber: " + flightNumber);
        System.out.println("Aircraft: " + aircraft);
        System.out.println("Description: " + this.getDescription());
        System.out.println();
    }

    public String getDescription() {
        String description = String.format(
                "Flight from %s to %s. Departure: %s, Arrival: %s. Airline: %s, Flight Number: %s, Aircraft: %s, ",
                origLoc, destLoc, departureTime, arrivalTime, airline, flightNumber, aircraft
        );
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(String numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getAirline(){
        return airline;
    }

    public void setAirline(String airline){
        this.airline = airline;
    }

    public String getOrigLoc() {
        return origLoc;
    }

    public void setOrigin(String origLoc) {
        this.origLoc = origLoc;
    }

    public String getDestLoc() {
        return destLoc;
    }

    public void setDestination(String destLoc) {
        this.destLoc = destLoc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPersons() {
        return persons;
    }

    public void setNumberOfPersons(int persons) {
        this.persons = persons;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
