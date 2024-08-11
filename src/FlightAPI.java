import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import client_keys.EncryptionUtil;

import com.google.gson.JsonArray;

public class FlightAPI {
    private static final String TOKEN_URL = "https://test.api.amadeus.com/v1/security/oauth2/token";
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;
    private static final Gson gson = new Gson();

    private String accessToken = null;
    private Instant tokenExpiryTime = null;

    public FlightAPI() {
        try {
            CLIENT_ID = EncryptionUtil.readEncryptedDataFromFile("src/client_id.txt");
            CLIENT_SECRET = EncryptionUtil.readEncryptedDataFromFile("src/client_secret.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


// At this point, the 'flight' object is populated with the available data

    public void checkAndRefreshToken() {
        if (expiredToken()) {
            refreshToken();
        }
    }

    public boolean expiredToken() {
        // Check if the token is null or if the current time is past the expiry time
        return accessToken == null || Instant.now().isAfter(tokenExpiryTime);
    }

    public void refreshToken() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            // Prepare the request body
            String requestBody = "grant_type=client_credentials&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET;

            // Create the request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TOKEN_URL))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Send the request and receive the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the response
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            if (jsonResponse.has("access_token")) {
                accessToken = jsonResponse.get("access_token").getAsString();
                int expiresIn = jsonResponse.get("expires_in").getAsInt();

                // Calculate the token expiry time
                tokenExpiryTime = Instant.now().plusSeconds(expiresIn);

                System.out.println("New Access Token: " + accessToken);
                System.out.println("Token Expires At: " + tokenExpiryTime);
            } else {
                // Handle error response
                System.err.println("Failed to obtain access token: " + response.body());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runFlightAPI(String origLoc, String destLoc, String departDate, int numOfAdults) {
        this.checkAndRefreshToken();
        try {
            // Create the HttpRequest object
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode="+ origLoc+"&destinationLocationCode=" + destLoc +"&departureDate=" + departDate +"&adults="+ numOfAdults +"&nonStop=false&max=15"))
                    .header("Authorization", "Bearer " + this.accessToken)
                    .header("Content-Type", "application/json")
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
//            System.out.println(postResponse.body()); // test if API works

            // Parse JSON into JsonObject
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(postResponse.body(), JsonObject.class);

// Extract flight offer details
            JsonArray dataArray = jsonObject.getAsJsonArray("data");
            if (dataArray != null && dataArray.size() > 0) {
                int count = 1; // To count the number of flights
                for (JsonElement element : dataArray) {
                    Flight flight = new Flight();
                    JsonObject firstFlightOffer = element.getAsJsonObject();
                    JsonArray itinerariesArray = firstFlightOffer.getAsJsonArray("itineraries");

                    if (itinerariesArray != null && itinerariesArray.size() > 0) {
                        JsonObject firstItinerary = itinerariesArray.get(0).getAsJsonObject();
                        JsonArray segmentsArray = firstItinerary.getAsJsonArray("segments");

                        if (segmentsArray != null && segmentsArray.size() > 0) {
                            // Extract destination IATA code
                            String destination = segmentsArray.get(segmentsArray.size() - 1).getAsJsonObject()
                                    .getAsJsonObject("arrival")
                                    .get("iataCode").getAsString();
                            flight.setDestination(destination);

                            // Extract origin IATA code
                            String origin = segmentsArray.get(0).getAsJsonObject()
                                    .getAsJsonObject("departure")
                                    .get("iataCode").getAsString();
                            flight.setOrigin(origin);

                            // Extract departure time
                            String departureTime = segmentsArray.get(0).getAsJsonObject()
                                    .getAsJsonObject("departure")
                                    .get("at").getAsString();
                            flight.setDepartureTime(departureTime);

                            // Extract arrival time
                            String arrivalTime = segmentsArray.get(segmentsArray.size() - 1).getAsJsonObject()
                                    .getAsJsonObject("arrival")
                                    .get("at").getAsString();
                            flight.setArrivalTime(arrivalTime);

                            // Extract airline code
                            String airline = segmentsArray.get(0).getAsJsonObject()
                                    .get("carrierCode").getAsString();
                            flight.setAirline(airline);

                            // Extract flight number
                            String flightNumber = segmentsArray.get(0).getAsJsonObject()
                                    .get("number").getAsString();
                            flight.setFlightNumber(flightNumber);

                            // Extract aircraft
                            String aircraft = segmentsArray.get(0).getAsJsonObject()
                                    .getAsJsonObject("aircraft")
                                    .get("code").getAsString();
                            flight.setAircraft(aircraft);

                            // Extract number of stops
                            String numberOfStops = String.valueOf(segmentsArray.size() - 1);
                            flight.setNumberOfStops(numberOfStops);

                            // Extract price and currency
                            JsonObject priceObject = firstFlightOffer.getAsJsonObject("price");
                            if (priceObject != null) {
                                String price = priceObject.get("grandTotal").getAsString();
                                String currency = priceObject.get("currency").getAsString();
                                flight.setPrice(price);
                                flight.setCurrency(currency);
                            }

                            // Get number of persons
                            JsonArray travelerPricingsArray = firstFlightOffer.getAsJsonArray("travelerPricings");
                            int numberOfPersons = travelerPricingsArray != null ? travelerPricingsArray.size() : 0;
                            flight.setNumberOfPersons(numberOfPersons);

                            // Extract cabin class and ensure it is unique
                            String cabin = null;
                            if (travelerPricingsArray != null && travelerPricingsArray.size() > 0) {
                                JsonObject firstTravelerPricing = travelerPricingsArray.get(0).getAsJsonObject();
                                JsonArray fareDetailsBySegmentArray = firstTravelerPricing.getAsJsonArray("fareDetailsBySegment");

                                if (fareDetailsBySegmentArray != null && fareDetailsBySegmentArray.size() > 0) {
                                    cabin = fareDetailsBySegmentArray.get(0).getAsJsonObject()
                                            .get("cabin").getAsString();
                                }
                            }
                            flight.setCabin(cabin);

                            System.out.println("Flight " + count + ":");
                            // Display the flight details
                            flight.displayFlights();

                            count++;
                        } else {
                            System.out.println("Segments data is missing or incomplete for flight " + count + ".");
                        }
                    } else {
                        System.out.println("Itineraries data is missing or incomplete for flight " + count + ".");
                    }
                }
            } else {
                System.out.println("Flight offers data is missing or incomplete.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
