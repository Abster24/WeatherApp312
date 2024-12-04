package cweatherapp;

import java.util.Scanner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input city: ");
        String city = input.nextLine();
        try {
            // Test the function
            String[] weatherDetails = cweatherapp.api.Fetch.getWeatherDetails(city);
            System.out.println("City: " + weatherDetails[0]);
            System.out.println("State: " + weatherDetails[1]);
            System.out.println("Country: " + weatherDetails[2]);
            System.out.println("Temperature: " + weatherDetails[3]);
            System.out.println("Currently " + weatherDetails[4]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
