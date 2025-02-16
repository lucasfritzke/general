package com.care_connect.demo.service;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GoogleMapsService {

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    private final RestClient googleClient = RestClient.create("https://maps.googleapis.com/maps/api/geocode/json");


    public double[] getLatLong(String address) {

        String url = "?address=" + address + "&key=" + googleMapsApiKey+"&fields=geometry/location,status";
        String response = googleClient.get().uri(url).retrieve().body(String.class);


        return extractLatLong(response);
    }

    private double[] extractLatLong(String json) {
        // Usando Gson para converter JSON
        JsonObject root = JsonParser.parseString(json).getAsJsonObject();

        // Verificar se o status é "OK"
        if (!"OK".equals(root.get("status").getAsString())) {
            throw new RuntimeException("Erro ao buscar localização: " + root.get("status").getAsString());
        }

        // Acessar a latitude e longitude dentro de results[0].geometry.location
        JsonObject location = root.getAsJsonArray("results")
                .get(0).getAsJsonObject()
                .getAsJsonObject("geometry")
                .getAsJsonObject("location");

        double lat = location.get("lat").getAsDouble();
        double lng = location.get("lng").getAsDouble();

        return new double[]{lat, lng};
    }
}

