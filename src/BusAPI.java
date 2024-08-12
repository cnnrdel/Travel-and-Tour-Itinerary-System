import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BusAPI {
    private BusHandler busHandler = new BusHandler();
    private static final String APP_ID = "54267ae0"; // Replace with your actual app_id
    private static final String APP_KEY = "99bc6a239aea80afdf67509feab32799"; // Replace with your actual app_key
    private static final Gson gson = new Gson();

    public void runBusAPI(String date) {
        try {
            // Construct the URI with basic query parameters
            String url = "https://transportapi.com/v3/uk/bus/service_timetables.json?operator=TNXB&service=74&direction=outbound&date=" + date + "&app_id=" + APP_ID + "&app_key=" + APP_KEY;
            URI uri = new URI(url);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);


            if (jsonObject.has("member") && jsonObject.get("member").isJsonArray()) {
                JsonArray journeys = jsonObject.getAsJsonArray("member");

                for (JsonElement journeyElement : journeys) {
                    JsonObject journey = journeyElement.getAsJsonObject();


                    JsonArray stops = journey.has("stops") && journey.get("stops").isJsonArray()
                            ? journey.getAsJsonArray("stops")
                            : new JsonArray();

                    // Create a Bus object for each stop and add it to the BusHandler
                    for (JsonElement stopElement : stops) {
                        JsonObject stop = stopElement.getAsJsonObject();
                        Bus bus = new Bus();

                        // Safely extract fields with null checks
                        JsonObject aimed = stop.has("aimed") && stop.get("aimed").isJsonObject()
                                ? stop.getAsJsonObject("aimed")
                                : new JsonObject();
                        JsonObject arrival = aimed.has("arrival") && aimed.get("arrival").isJsonObject()
                                ? aimed.getAsJsonObject("arrival")
                                : new JsonObject();
                        JsonObject departure = aimed.has("departure") && aimed.get("departure").isJsonObject()
                                ? aimed.getAsJsonObject("departure")
                                : new JsonObject();

                        bus.setDepartureTime(departure.has("time") && !departure.get("time").isJsonNull()
                                ? departure.get("time").getAsString()
                                : "N/A");
                        bus.setArrivalTime(arrival.has("time") && !arrival.get("time").isJsonNull()
                                ? arrival.get("time").getAsString()
                                : "N/A");
                        bus.setFromPoint(stop.has("name") && !stop.get("name").isJsonNull()
                                ? stop.get("name").getAsString()
                                : "N/A");
                        bus.setToPoint(stop.has("locality") && !stop.get("locality").isJsonNull()
                                ? stop.get("locality").getAsString()
                                : "N/A");
                        bus.setDestination(stop.has("stop_name") && !stop.get("stop_name").isJsonNull()
                                ? stop.get("stop_name").getAsString()
                                : "N/A");

                        busHandler.addBus(bus);
                    }
                }


                busHandler.printBuses();
            } else {
                System.out.println("No bus journeys found or 'member' key is missing in the response.");
            }

        } catch (Exception e) {
            System.err.println("Error parsing API response: " + e.getMessage());
        }
    }
}