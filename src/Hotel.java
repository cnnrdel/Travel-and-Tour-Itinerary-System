public class Hotel {
    private String hotelName;
    private String hotelAddress;
    private String hotelPhone;
    private String hotelEmail;
    private double hotelDistance;

    // Generates a description of the hotel
    public String getDescription() {
        return String.format(
                "Hotel name is %s. Location: %s, Hotel Distance: %.2f miles",
                hotelName != null ? hotelName : "Not available",
                hotelAddress != null ? hotelAddress : "Not available",
                hotelDistance
        );
    }

    // Displays the hotel details
    public void displayHotelDetails() {
        System.out.println("Hotel Name: " + (hotelName != null ? hotelName : "Not available"));
        System.out.println("Hotel Address: " + (hotelAddress != null ? hotelAddress : "Not available"));
        System.out.println("Hotel Distance: " + hotelDistance + " miles");
        System.out.println("Description: " + this.getDescription());
    }

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
}
