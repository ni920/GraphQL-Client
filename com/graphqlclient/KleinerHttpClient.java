package com.graphqlclient;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KleinerHttpClient {

    private static int port = 4711;

    public static void main(String[] args) {

        try {
            String uri = "http://localhost:" + port + "/MeineRessource?MeineFrage";
            HttpClient client = HttpClient.newHttpClient();	//siehe Factroy method
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).header("Content-type",  "application/json").POST(HttpRequest.BodyPublishers.ofString(" JSON: { \"key\" : \"value\" }")).build();
            //siehe builder pattern
            //HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

        } catch (IOException e) {
            if (e.getClass().equals(ConnectException.class)) {
                System.out.println("Starte Server!");
            } else {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
