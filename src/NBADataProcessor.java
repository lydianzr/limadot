/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectds;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class NBADataProcessor {

    private static final String API_KEY = "b7a77024-f158-47a3-a706-d8a565ea2661";
    private static final String API_URL = "https://api.balldontlie.io/v1/players";

    public void fetchDataAndStoreToCSV() {
        JSONArray playersArray = getPlayerData();

        if (playersArray.length() == 0) {
            System.out.println("No player data retrieved. Exiting.");
            return;
        }

        try (FileWriter fileWriter = new FileWriter("playerhiha.csv");
             CSVWriter csvWriter = new CSVWriter(fileWriter)) {

            // Write header
            String[] header = {"ID", "Name", "Position", "Height", "Weight", "Jersey Number", "College", "Country", "Draft Year", "Draft Round", "Draft Number", "Team ID", "Conference", "Division", "City", "Team Name", "Full Team Name", "Abbreviation"};
            csvWriter.writeNext(header);

            for (int i = 0; i < playersArray.length(); i++) {
                JSONObject playerObject = playersArray.getJSONObject(i);
                String[] playerData = {
                        String.valueOf(playerObject.getInt("id")),
                        playerObject.getString("first_name") + " " + playerObject.getString("last_name"),
                        playerObject.getString("position"),
                        convertHeight(playerObject.getString("height")),
                        playerObject.getString("weight"),
                        playerObject.optString("jersey_number", "N/A"),
                        playerObject.optString("college", "N/A"),
                        playerObject.optString("country", "N/A"),
                        getDraftYear(playerObject),
                        String.valueOf(playerObject.optInt("draft_round", -1)),
                        String.valueOf(playerObject.optInt("draft_number", -1)),
                        String.valueOf(playerObject.getJSONObject("team").getInt("id")),
                        playerObject.getJSONObject("team").getString("conference"),
                        playerObject.getJSONObject("team").getString("division"),
                        playerObject.getJSONObject("team").getString("city"),
                        playerObject.getJSONObject("team").getString("name"),
                        playerObject.getJSONObject("team").getString("full_name"),
                        playerObject.getJSONObject("team").getString("abbreviation")
                };
                csvWriter.writeNext(playerData);
            }

            System.out.println("Player list saved to player_list.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONArray getPlayerData() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", API_KEY)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            return jsonResponse.getJSONArray("data");
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray(); // Return empty array in case of error
        }
    }

    private String convertHeight(String height) {
        // Convert height from feet-inches format to inches
        String[] parts = height.split("-");
        int feet = Integer.parseInt(parts[0]);
        int inches = Integer.parseInt(parts[1]);
        int totalInches = feet * 12 + inches;
        return totalInches + " inches";
    }

    private String getDraftYear(JSONObject playerObject) {
        // Handle null value for draft_year
        int draftYear = playerObject.optInt("draft_year", -1);
        return (draftYear != -1) ? String.valueOf(draftYear) : "Not available";
    }

    public static void main(String[] args) {
        NBADataProcessor dataProcessor = new NBADataProcessor();
        dataProcessor.fetchDataAndStoreToCSV();
    }
}

