package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientStatus {

    public static void main(String[] args) throws IOException, InterruptedException {

        // new HttpClient
        HttpClient client = HttpClient.newHttpClient();
        // set HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://postman-echo.com/get"))
                .GET()
                .build();

        // set response
        HttpResponse<String> response = null;
        // send request & get response
        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            // print response.body
            System.out.println("Body: \n" + response.body());
        }
        else
        {
            // print error status code
            System.out.println("StatusCode: " + response.statusCode());
        }
    }
}
