public class Bus {
    private String departureTime;
    private String arrivalTime;
    private String fromPoint;
    private String toPoint;
    private String destination;

    // Getters and setters
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

    public String getFromPoint() {
        return fromPoint;
    }

    public void setFromPoint(String fromPoint) {
        this.fromPoint = fromPoint;
    }

    public String getToPoint() {
        return toPoint;
    }

    public void setToPoint(String toPoint) {
        this.toPoint = toPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return String.format("Departure Time: %s\nArrival Time: %s\nFrom: %s\nTo: %s\nDestination: %s\n",
                departureTime, arrivalTime, fromPoint, toPoint, destination);
    }
}
