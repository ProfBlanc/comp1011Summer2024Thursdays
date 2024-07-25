package ca.georgiancollege.comp1011summer2024thursdays;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIUtility {

    String sendRequest(String uri){
        String json = "";

    try {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        json = response.body();
    }
    catch (Exception e){
        System.err.println("Cannot retrieve request with URI of " + uri);
    }

        return json;
    }
}
