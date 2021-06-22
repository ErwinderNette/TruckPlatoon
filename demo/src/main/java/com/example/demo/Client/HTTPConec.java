package com.example.demo.Client;

import com.example.demo.Client.model.Truck;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPConec {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static Truck register(int position) throws IOException, InterruptedException, JSONException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/addTruck/" + position))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(response.body());

        Truck truck = new Truck(
                jsonObject.getInt("id"),
                jsonObject.getInt("frontId"),
                jsonObject.getInt("backId"),
                jsonObject.getInt("speed"),
                jsonObject.getBoolean("leader")
        );
        return truck;
    }

    public static void connect(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/SocketDa/" + id))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }

}
