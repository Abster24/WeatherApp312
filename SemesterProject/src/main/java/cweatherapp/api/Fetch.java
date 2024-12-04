package cweatherapp.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Fetch {
    private static final String API_KEY = "API KEY";
    private static final String BASE_URL = "https://api.weatherstack.com/current";


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

            // Parse the response
            String responseBody = response.body().string();
            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();

            // default values
            String city = "Unknown";
            String country = "Unknown";
            String temperature = "N/A";
            String weather = "N/A";

            // Extract city name
            if (json.has("location") && json.getAsJsonObject("location").has("name") &&
                    !json.getAsJsonObject("location").get("name").isJsonNull()) {
                city = json.getAsJsonObject("location").get("name").getAsString();
            }

            // country name
            if (json.has("location") && json.getAsJsonObject("location").has("country") &&
                    !json.getAsJsonObject("location").get("country").isJsonNull()) {
                country = json.getAsJsonObject("location").get("country").getAsString();
            }

            // temperature
            if (json.has("current") && json.getAsJsonObject("current").has("temperature") &&
                    !json.getAsJsonObject("current").get("temperature").isJsonNull()) {
                temperature = json.getAsJsonObject("current").get("temperature").getAsString() + " °F";
            }

            //Weather Description
            if (json.has("current") && json.getAsJsonObject("current").has("weather_descriptions") &&
                    !json.getAsJsonObject("current").get("weather_descriptions").isJsonNull()) {
                weather = json.getAsJsonObject("current").get("weather_descriptions").getAsString().toLowerCase();
            }

            // Return as an array
            return new String[]{city, country, temperature, weather};
        }
    }
}
