import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import com.google.gson.*;

import client_keys.EncryptionUtil;

public class FlightAPI {
    private static final String TOKEN_URL = "https://test.api.amadeus.com/v1/security/oauth2/token";
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;
    private static final Gson gson = new Gson();
    private FlightHandler flightHandler = new FlightHandler();

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

    public void checkAndRefreshToken() {
        if (expiredToken()) {
            refreshToken();
        }
    }

    public boolean expiredToken() {
        return accessToken == null || Instant.now().isAfter(tokenExpiryTime);
    }

    public void refreshToken() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String requestBody = "grant_type=client_credentials&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TOKEN_URL))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            if (jsonResponse.has("access_token")) {
                accessToken = jsonResponse.get("access_token").getAsString();
                int expiresIn = jsonResponse.get("expires_in").getAsInt();

                tokenExpiryTime = Instant.now().plusSeconds(expiresIn);

                System.out.println("New Access Token: " + accessToken);
                System.out.println("Token Expires At: " + tokenExpiryTime);
            } else {
                System.err.println("Failed to obtain access token: " + response.body());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Flight> runFlightAPI(String origLoc, String destLoc, String departDate, int numOfAdults) {
        this.checkAndRefreshToken();
        try {
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode="+ origLoc+"&destinationLocationCode=" + destLoc +"&departureDate=" + departDate +"&adults="+ numOfAdults +"&nonStop=false&max=15"))
                    .header("Authorization", "Bearer " + this.accessToken)
                    .header("Content-Type", "application/json")
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = gson.fromJson(postResponse.body(), JsonObject.class);

            if (jsonObject.has("data") && jsonObject.get("data").isJsonArray()) {
                JsonArray dataArray = jsonObject.getAsJsonArray("data");

                if (dataArray.size() > 0) {
                    int count = 0;
                    for (JsonElement element : dataArray) {
                        JsonObject firstFlightOffer = element.getAsJsonObject();
                        JsonArray itinerariesArray = firstFlightOffer.has("itineraries") ? firstFlightOffer.getAsJsonArray("itineraries") : new JsonArray();

                        if (itinerariesArray.size() > 0) {
                            JsonObject firstItinerary = itinerariesArray.get(0).getAsJsonObject();
                            JsonArray segmentsArray = firstItinerary.has("segments") ? firstItinerary.getAsJsonArray("segments") : new JsonArray();

                            if (segmentsArray.size() > 0) {
                                Flight flight = new Flight();

                                String destination = segmentsArray.get(segmentsArray.size() - 1).getAsJsonObject()
                                        .getAsJsonObject("arrival")
                                        .get("iataCode").getAsString();
                                flight.setDestination(destination);

                                String origin = segmentsArray.get(0).getAsJsonObject()
                                        .getAsJsonObject("departure")
                                        .get("iataCode").getAsString();
                                flight.setOrigin(origin);

                                String departureTime = segmentsArray.get(0).getAsJsonObject()
                                        .getAsJsonObject("departure")
                                        .get("at").getAsString();
                                flight.setDepartureTime(departureTime);

                                String arrivalTime = segmentsArray.get(segmentsArray.size() - 1).getAsJsonObject()
                                        .getAsJsonObject("arrival")
                                        .get("at").getAsString();
                                flight.setArrivalTime(arrivalTime);

                                String airline = segmentsArray.get(0).getAsJsonObject()
                                        .get("carrierCode").getAsString();
                                flight.setAirline(airline);

                                String flightNumber = segmentsArray.get(0).getAsJsonObject()
                                        .get("number").getAsString();
                                flight.setFlightNumber(flightNumber);

                                String aircraft = segmentsArray.get(0).getAsJsonObject()
                                        .getAsJsonObject("aircraft")
                                        .get("code").getAsString();
                                flight.setAircraft(aircraft);

                                String numberOfStops = String.valueOf(segmentsArray.size() - 1);
                                flight.setNumberOfStops(numberOfStops);

                                JsonObject priceObject = firstFlightOffer.has("price") ? firstFlightOffer.getAsJsonObject("price") : new JsonObject();
                                if (priceObject.has("grandTotal")) {
                                    String price = priceObject.get("grandTotal").getAsString();
                                    String currency = priceObject.has("currency") ? priceObject.get("currency").getAsString() : "Unknown";
                                    flight.setPrice(price);
                                    flight.setCurrency(currency);
                                }

                                JsonArray travelerPricingsArray = firstFlightOffer.has("travelerPricings") ? firstFlightOffer.getAsJsonArray("travelerPricings") : new JsonArray();
                                int numberOfPersons = travelerPricingsArray.size();
                                flight.setNumberOfPersons(numberOfPersons);

                                String cabin = null;
                                if (travelerPricingsArray.size() > 0) {
                                    JsonObject firstTravelerPricing = travelerPricingsArray.get(0).getAsJsonObject();
                                    JsonArray fareDetailsBySegmentArray = firstTravelerPricing.has("fareDetailsBySegment") ? firstTravelerPricing.getAsJsonArray("fareDetailsBySegment") : new JsonArray();

                                    if (fareDetailsBySegmentArray.size() > 0) {
                                        cabin = fareDetailsBySegmentArray.get(0).getAsJsonObject()
                                                .get("cabin").getAsString();
                                    }
                                }
                                flight.setCabin(cabin);
                                flightHandler.addFlight(flight);

                            } else {
                                System.out.println("Segments data is missing or incomplete for flight " + count + ".");
                            }
                        } else {
                            System.out.println("Itineraries data is missing or incomplete for flight " + count + ".");
                        }
                        count++;
                    }
                } else {
                    System.out.println("No flight offers found.");
                }
            } else {
                System.out.println("Flight offers data is missing or incomplete.");
            }
        } catch (Exception e) {
            System.err.println("Error fetching flight data: " + e.getMessage());
            e.printStackTrace();
        }

        return flightHandler.getFlights();
    }

}
