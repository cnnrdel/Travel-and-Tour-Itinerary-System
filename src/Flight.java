public class Flight {
    private String origin;
    private String destination;
    private String price;
    private int numberOfPersons;
    private String departureTime;
    private String arrivalTime;
    private String airline;
    private String cabin;
    private String currency;
    private String numberOfStops;
    private String flightNumber;
    private String aircraft;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
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

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
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

    @Override
    public String toString() {
        return String.format("Flight Details:\n" +
                        "Origin: %s\n" +
                        "Destination: %s\n" +
                        "Currency: %s\n" +
                        "Price: %s\n" +
                        "Persons: %d\n" +
                        "Departure Time: %s\n" +
                        "Arrival Time: %s\n" +
                        "Airline: %s\n" +
                        "Cabin: %s\n" +
                        "Number of Stops: %s\n" +
                        "Flight Number: %s\n" +
                        "Aircraft: %s\n",
                origin, destination, currency, price, numberOfPersons, departureTime, arrivalTime,
                airline, cabin, numberOfStops, flightNumber, aircraft);
    }
}
