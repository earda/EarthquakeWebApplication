package com.project.earthquake.earthquakewebapplication.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.earthquake.earthquakewebapplication.EarthquakeEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EarthquakeService {
    private static final String earthquake_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";

    private RestTemplate restTemp = new RestTemplate();
    private ObjectMapper objectMap = new ObjectMapper();

    public List<EarthquakeEntity> getEarthquakeData() throws IOException, ParseException {
        String url = earthquake_URL + "?format=geojson&starttime=2023-01-01&endtime=2023-03-27&limit=12";
        String response = restTemp.getForObject(url, String.class);
        JsonNode rootNode = objectMap.readTree(response);
        JsonNode featureNode = rootNode.path("features");
        List<EarthquakeEntity> earthquakes = new ArrayList<>();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (JsonNode featuresNode : featureNode) {
            JsonNode propertiesNode = featuresNode.path("properties");
            String country = propertiesNode.path("place").asText().split(", ")[1];
            String location = propertiesNode.path("place").asText().split(", ")[0];
            String magnitude = propertiesNode.path("mag").asText().split(", ")[0];
            String date = propertiesNode.path("time").asText().split(", ")[0];
           //Date dateDeneme = formatter.parse(date);
            Instant dateDeneme = Instant.ofEpochMilli(Long.parseLong(date));
            System.out.print(dateDeneme);
            Date time = Date.from(dateDeneme);
            EarthquakeEntity earthquakeEntity = new EarthquakeEntity(country, location,magnitude,date,time);
            earthquakes.add(earthquakeEntity);
        }
        return earthquakes;
    }
}
