package ca.georgiancollege.comp1011summer2024thursdays;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BallDontLieAPI {

    private String key = "6c49f0d5-395d-4b87-998e-9b0524aa8891";
    private Gson gson = new Gson();

    private String sendRequest(String uri){
        String json = "";

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("Authorization", key)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            json = response.body();
        }
        catch (Exception e){
            System.err.println("Cannot retrieve request with URI of " + uri);
        }

        return json;
    }

    public BallDontLieFetchTeams fetchTeams(String endpoint){
        String uri = "https://api.balldontlie.io/v1/teams/"+ endpoint;
        String json = sendRequest(uri);

        return gson.fromJson(json, BallDontLieFetchTeams.class);
    }
}
