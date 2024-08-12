import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

import com.google.gson.*;

import client_keys.EncryptionUtil;

public class FlightAPI {
    private static final String TOKEN_URL = "https://test.api.amadeus.com/v1/security/oauth2/token";
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;
    private static final Gson gson = new Gson();
    private TrainHandler trainHandler = new TrainHandler();

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

// Check if the token is null or if the current time is past the expiry time
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

            // Parse the response
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

    public void runTrainsAPI(String dateTime, String stationCode) {

        try {
            String fromOffset = "PT00:30:00";
            String toOffset = "PT01:30:00";
            int limit = 20;
            boolean live = true;
            String trainStatus = "passenger";
            String stationDetail = "origin,destination";
            String type = "arrival,departure,pass";

            String encodedStationCode = URLEncoder.encode(stationCode, StandardCharsets.UTF_8.toString());
            String encodedDateTime = URLEncoder.encode(dateTime, StandardCharsets.UTF_8.toString());
            String encodedFromOffset = URLEncoder.encode(fromOffset, StandardCharsets.UTF_8.toString());
            String encodedToOffset = URLEncoder.encode(toOffset, StandardCharsets.UTF_8.toString());
            String encodedStationDetail = URLEncoder.encode(stationDetail, StandardCharsets.UTF_8.toString());
            String encodedType = URLEncoder.encode(type, StandardCharsets.UTF_8.toString());

            // Construct the URI with query parameters
            String url = String.format(
                    "https://transportapi.com/v3/uk/train/station_timetables/crs%%3A%s.json?datetime=%s&from_offset=%s&to_offset=%s&limit=%d&live=%b&train_status=%s&station_detail=%s&type=%s&app_key=%s&app_id=%s",
                    encodedStationCode,
                    encodedDateTime,
                    encodedFromOffset,
                    encodedToOffset,
                    limit,
                    live,
                    trainStatus,
                    encodedStationDetail,
                    encodedType,
                    CLIENT_ID,
                    CLIENT_SECRET
            );

            URI uri = new URI(url);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //debugging
//            System.out.println("Raw API Response:");
//            System.out.println(response.body());

            // Parse JSON response
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray updates = jsonObject.getAsJsonObject("updates").getAsJsonArray("all");

            for (int i = 0; i < updates.size(); i++) {
                JsonObject update = updates.get(i).getAsJsonObject();

                Train train = new Train();

                // Extracts and sets attributes for the Train object
                String departureTime = update.has("aimed_departure_time") && !update.get("aimed_departure_time").isJsonNull() ? update.get("aimed_departure_time").getAsString() : "Unknown";
                JsonObject stationInfo = update.has("station_detail") && !update.get("station_detail").isJsonNull() ? update.getAsJsonObject("station_detail") : new JsonObject();
                String originName = stationInfo.has("origin") && !stationInfo.get("origin").isJsonNull() ? stationInfo.getAsJsonObject("origin").get("station_name").getAsString() : "Unknown";
                String destinationName = stationInfo.has("destination") && !stationInfo.get("destination").isJsonNull() ? stationInfo.getAsJsonObject("destination").get("station_name").getAsString() : "Unknown";
                String platform = update.has("platform") && !update.get("platform").isJsonNull() ? update.get("platform").getAsString() : "Unknown";
                String trainUid = update.has("train_uid") && !update.get("train_uid").isJsonNull() ? update.get("train_uid").getAsString() : "Unknown";

                // Sets the attributes
                train.setDepartureTime(departureTime);
                train.setOriginName(originName);
                train.setDestinationName(destinationName);
                train.setPlatform(platform);
                train.setTrainUid(trainUid);

                trainHandler.addTrain(train);
            }

        } catch (Exception e) {
            System.err.println("Error fetching train data: " + e.getMessage());
        }
        trainHandler.displayTrainList();

    }
}
