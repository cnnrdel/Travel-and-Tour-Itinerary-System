import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BusAPI {
    private static final String APP_ID = "54267ae0"; // Replace with your actual app_id
    private static final String APP_KEY = "99bc6a239aea80afdf67509feab32799"; // Replace with your actual app_key
    private static final Gson gson = new Gson();

    public void runBusServiceAPI() {
        try {
            // Construct the URI with query parameters
            String url = "https://transportapi.com/v3/uk/bus/services.json?operator=FBRI&line_name=1&app_id=" + APP_ID + "&app_key=" + APP_KEY;
            URI uri = new URI(url);

            // Build the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .build();

            // Create HTTP client and send request
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Log the full response
//            System.out.println("Full API response: " + response.body());

            // Parse JSON response
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

            // Check if "member" key exists and is not null
            if (jsonObject.has("member") && !jsonObject.get("member").isJsonNull()) {
                JsonArray services = jsonObject.getAsJsonArray("member");

                // Iterate through the services and format the output
                StringBuilder html = new StringBuilder();
                for (int i = 0; i < services.size(); i++) {
                    JsonObject service = services.get(i).getAsJsonObject();

                    String operatorName = service.get("operator").getAsJsonObject().get("name").getAsString();
                    String lineName = service.get("line_name").getAsString();
                    html.append("<h4><b>").append(operatorName).append(": ").append(lineName).append("</b></h4>\n");

                    JsonArray directions = service.getAsJsonArray("directions");
                    html.append("<ul>\n");
                    for (int j = 0; j < directions.size(); j++) {
                        JsonObject direction = directions.get(j).getAsJsonObject();
                        String directionName = direction.get("name").getAsString();
                        String destinationDescription = direction.get("destination").getAsJsonObject().get("description").getAsString();

                        html.append("<li>").append(directionName).append(": to ").append(destinationDescription).append("</li>\n");
                    }
                    html.append("</ul>\n<hr>\n");
                }

                // Output the formatted HTML-like text (console output for simplicity)
                System.out.println(html.toString());
            } else {
                // Handle the case where "member" is null or doesn't exist
                System.out.println("No bus services found or 'member' key is missing in the response.");
            }

        } catch (Exception e) {
            System.err.println("Error parsing API response: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        BusAPI busServiceAPI = new BusAPI();
        busServiceAPI.runBusServiceAPI();
    }
}

