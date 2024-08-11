import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpHeaders;
import java.io.IOException;
import GSON;

public class FlightAPI {
    GSON gson = new GSON();
    public void refreshToken(){

    }

    public static void main(String[] args) {
        String apiKey = "jTzpVWGWrF8o3ikzD37No3PNzYciwQFX";
        String apiSecret = "PyqprP1sgNl4njL6";
        String jsonBody = "{"
                + "\"currencyCode\": \"USD\","
                + "\"originDestinations\": [{"
                + "\"id\": \"1\","
                + "\"originLocationCode\": \"NYC\","
                + "\"destinationLocationCode\": \"MAD\","
                + "\"departureDateTimeRange\": {"
                + "\"date\": \"2024-08-10\","
                + "\"time\": \"05:00:00\""
                + "}"
                + "}],"
                + "\"travelers\": [{"
                + "\"id\": \"1\","
                + "\"travelerType\": \"ADULT\""
                + "}],"
                + "\"sources\": [\"GDS\"],"
                + "\"searchCriteria\": {"
                + "\"maxFlightOffers\": 2,"
                + "\"flightFilters\": {"
                + "\"cabinRestrictions\": [{"
                + "\"cabin\": \"BUSINESS\","
                + "\"coverage\": \"MOST_SEGMENTS\","
                + "\"originDestinationIds\": [\"1\"]"
                + "}]"
                + "}"
                + "}"
                + "}";

        try {
            // Create the HttpRequest object
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://test.api.amadeus.com/v2/shopping/flight-offers"))
                    .header("Authorization", "Bearer fK43CCKGDvrbukdgxaq4RaWWgQws")  // Replace with your actual token
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(jsonBody))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(postResponse.body());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // refresh token function

    // check for token expired

}

