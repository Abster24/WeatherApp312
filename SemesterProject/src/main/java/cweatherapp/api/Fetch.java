package cweatherapp.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cweatherapp.server.UserDAO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.List;

public class Fetch {
    private static final String API_KEY = "";
    private static final String BASE_URL = "https://api.weatherstack.com/current";


    /**
     *
     * @param userId
     */
    public static void fetchAndSaveWeatherForUser(int userId) {
        List<String> cities = UserDAO.getUserCities(userId);

        for (String city : cities) {
            try {
                String[] weatherDetails = getWeatherDetails(city);

                // Example: Print fetched weather data
                System.out.println("City: " + weatherDetails[0]);
                System.out.println("Country: " + weatherDetails[1]);
                System.out.println("Temperature: " + weatherDetails[2]);
                System.out.println("Condition: " + weatherDetails[3]);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Fetches weather data for a given city and returns an array with city, country, and temperature.
     *
     * @param cityName The name of the city to fetch weather data for.
     * @return A String array containing the city name, country name, and temperature in Fahrenheit.
     * @throws Exception If the request fails or the data is malformed.
     */
    public static String[] getWeatherDetails(String cityName) throws Exception {
        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + "?query=" + cityName + "&access_key=" + API_KEY + "&units=f";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Unexpected code " + response);
            }

            // Parse the response JSON
            String responseBody = response.body().string();
            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();

            // Initialize variables with default values
            String city = "Unknown";
            String country = "Unknown";
            String temperature = "N/A";
            String weather = "N/A";

            // Extract city name
            if (json.has("location") && json.getAsJsonObject("location").has("name") &&
                    !json.getAsJsonObject("location").get("name").isJsonNull()) {
                city = json.getAsJsonObject("location").get("name").getAsString();
            }

            // Extract country name
            if (json.has("location") && json.getAsJsonObject("location").has("country") &&
                    !json.getAsJsonObject("location").get("country").isJsonNull()) {
                country = json.getAsJsonObject("location").get("country").getAsString();
            }

            // Extract temperature
            if (json.has("current") && json.getAsJsonObject("current").has("temperature") &&
                    !json.getAsJsonObject("current").get("temperature").isJsonNull()) {
                temperature = json.getAsJsonObject("current").get("temperature").getAsString() + " °F";
            }

            //Extract Weather Description
            if (json.has("current") && json.getAsJsonObject("current").has("weather_descriptions") &&
                    !json.getAsJsonObject("current").get("weather_descriptions").isJsonNull()) {
                weather = json.getAsJsonObject("current").get("weather_descriptions").getAsString().toLowerCase();
            }

            // Return as an array
            return new String[]{city, country, temperature, weather};
        }
    }
}
