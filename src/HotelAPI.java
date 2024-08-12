import com.google.gson.*;

import client_keys.EncryptionUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

public class HotelAPI {
        private static final String TOKEN_URL = "https://test.api.amadeus.com/v1/security/oauth2/token";
        private static final Gson gson = new Gson();
        private static String CLIENT_ID;
        private static String CLIENT_SECRET;
        private HotelHandler hotelHandler = new HotelHandler();
        private String accessToken;
        private Instant tokenExpiryTime;

        public HotelAPI() {
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
        // Check if the token is null or if the current time is past the expiry time
        return accessToken == null || Instant.now().isAfter(tokenExpiryTime);
    }

    public void refreshToken() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            // Prepare the request body
            String requestBody = "grant_type=client_credentials";

            // Create the Authorization header
            String auth = CLIENT_ID + ":" + CLIENT_SECRET;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            String authHeader = "Basic " + encodedAuth;

            // Create the request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TOKEN_URL))
                    .header("Authorization", authHeader) // Add the Authorization header
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


    public void runHotelAPI(int radius, String cityName) {
        this.checkAndRefreshToken();
        try {
            // Create the HttpRequest object
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://test.api.amadeus.com/v1/reference-data/locations/hotels/by-city?cityCode=" + cityName + "&radius=" + radius +"&radiusUnit=MILE&hotelSource=ALL"))
                    .header("Authorization", "Bearer " + this.accessToken)
                    .header("Content-Type", "application/json")
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            //  System.out.println(postResponse.body()); // test if API works

            // Parse JSON into JsonObject
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(postResponse.body(), JsonObject.class);
            JsonArray dataArray = jsonObject.getAsJsonArray("data");

            if (dataArray != null && dataArray.size() > 0) {
                int count = 1;
                for (JsonElement element : dataArray) {
                    Hotel hotel = new Hotel();
                    JsonObject hotelObject = element.getAsJsonObject();

                    // Extract and set hotel name
                    String hotelName = hotelObject.get("name").getAsString();
                    hotel.setHotelName(hotelName);

                    // Extract and set hotel address (countryCode)
                    String hotelAddress = hotelObject.getAsJsonObject("address").get("countryCode").getAsString();
                    hotel.setHotelAddress(hotelAddress);

                    // Extract and set hotel distance
                    double hotelDistance = hotelObject.getAsJsonObject("distance").get("value").getAsDouble();
                    hotel.setHotelDistance(hotelDistance);
                    hotelHandler.addHotel(hotel);
                    count++;
                }
            } else {
                System.out.println("Hotel data is missing or incomplete.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        hotelHandler.displayHotelList();
    }
}
