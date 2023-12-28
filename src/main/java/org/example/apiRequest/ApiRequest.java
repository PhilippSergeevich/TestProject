package org.example.apiRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ApiRequest {
    private static HttpClient client = HttpClient.newHttpClient();
    private static HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();

    public static String doRequest(String urlConnect) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = requestBuilder.uri(new URI(urlConnect)).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        return response.body();


    }
}
