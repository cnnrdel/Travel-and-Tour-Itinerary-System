public class Hotel {
    private String hotelName;
    private String hotelAddress;
    private double hotelDistance;

    // Getters and Setters
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public double getHotelDistance() {
        return hotelDistance;
    }

    public void setHotelDistance(double hotelDistance) {
        this.hotelDistance = hotelDistance;
    }

    @Override
    public String toString() {
        return String.format("Hotel Details:\n" +
                        "Hotel Name: %s\n" +
                        "Hotel Address: %s\n" +
                        "Hotel Distance: %.2f miles\n",
                hotelName != null ? hotelName : "Not available",
                hotelAddress != null ? hotelAddress : "Not available",
                hotelDistance);
    }

}
