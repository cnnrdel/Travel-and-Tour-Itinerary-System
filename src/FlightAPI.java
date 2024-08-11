import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class FlightAPI {
    private static final String TOKEN_URL = "https://test.api.amadeus.com/v1/security/oauth2/token";
//    private static final String CLIENT_ID = "jTzpVWGWrF8o3ikzD37No3PNzYciwQFX";
//    private static final String CLIENT_SECRET = "PyqprP1sgNl4njL6";
    private static final Gson gson = new Gson();

    private String accessToken = null;
    private Instant tokenExpiryTime = null;

    public static void main(String[] args) {
        FlightAPI api = new FlightAPI();
        api.checkAndRefreshToken();

        try {
            // Create the HttpRequest object
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=SYD&destinationLocationCode=BKK&departureDate=2024-08-11&adults=1&nonStop=false&max=250"))
                    .header("Authorization", "Bearer " + api.accessToken)
                    .header("Content-Type", "application/json")
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(postResponse.body());
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
}
