import com.google.gson.*;

import client_keys.EncryptionUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

     // Check if the token is null or if the current time is past the expiry time
    public boolean expiredToken() {
       
        return accessToken == null || Instant.now().isAfter(tokenExpiryTime);
    }

    public void refreshToken() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String requestBody = "grant_type=client_credentials";

            String auth = CLIENT_ID + ":" + CLIENT_SECRET;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            String authHeader = "Basic " + encodedAuth;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TOKEN_URL))
                    .header("Authorization", authHeader) // Add the Authorization header
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            if (jsonResponse.has("access_token")) {
                accessToken = jsonResponse.get("access_token").getAsString();
                int expiresIn = jsonResponse.get("expires_in").getAsInt();

                tokenExpiryTime = Instant.now().plusSeconds(expiresIn);
//  debugging
//                System.out.println("New Access Token: " + accessToken);
//                System.out.println("Token Expires At: " + tokenExpiryTime);
            } else {
                System.err.println("Failed to obtain access token: " + response.body());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Hotel> runHotelAPI(int radius, String cityName) {
        this.checkAndRefreshToken();
        try {
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://test.api.amadeus.com/v1/reference-data/locations/hotels/by-city?cityCode=" + cityName + "&radius=" + radius + "&radiusUnit=MILE&hotelSource=ALL"))
                    .header("Authorization", "Bearer " + this.accessToken)
                    .header("Content-Type", "application/json")
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(postResponse.body(), JsonObject.class);
            JsonArray dataArray = jsonObject.getAsJsonArray("data");

            if (dataArray != null && dataArray.size() > 0) {
                for (JsonElement element : dataArray) {
                    Hotel hotel = new Hotel();
                    JsonObject hotelObject = element.getAsJsonObject();

                    // Extract and set hotel name
                    if (hotelObject.has("name")) {
                        String hotelName = hotelObject.get("name").getAsString();
                        hotel.setHotelName(hotelName);
                    }

                    // Extract and set hotel address (countryCode)
                    if (hotelObject.has("address") && hotelObject.getAsJsonObject("address").has("countryCode")) {
                        String hotelAddress = hotelObject.getAsJsonObject("address").get("countryCode").getAsString();
                        hotel.setHotelAddress(hotelAddress);
                    }

                    // Extract and set hotel distance
                    if (hotelObject.has("distance") && hotelObject.getAsJsonObject("distance").has("value")) {
                        double hotelDistance = hotelObject.getAsJsonObject("distance").get("value").getAsDouble();
                        hotel.setHotelDistance(hotelDistance);
                    }

                    hotelHandler.addHotel(hotel);
                }
            } else {
                System.out.println("Hotel data is missing or incomplete.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotelHandler.getHotels();
    }

}
