public class Hotel {
    private String hotelName;
    private String hotelAddress;
    private String hotelPhone;
    private String hotelEmail;
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

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
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
