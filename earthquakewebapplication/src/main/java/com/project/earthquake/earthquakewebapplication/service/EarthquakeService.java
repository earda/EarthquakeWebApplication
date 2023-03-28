package com.project.earthquake.earthquakewebapplication.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.earthquake.earthquakewebapplication.EarthquakeEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EarthquakeService {
    private static final String earthquake_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";

    private RestTemplate restTemp = new RestTemplate();
    private ObjectMapper objectMap = new ObjectMapper();

    public List<EarthquakeEntity> getEarthquakeData(String startTime, String endTime, String minLatitude, String maxLatitude, String minLongitude, String maxLongitude) throws IOException, ParseException {
        String url = earthquake_URL + String.format("?format=geojson&starttime=%s&endtime=%s",startTime,endTime);
        if(!StringUtils.isEmpty(minLatitude)){ //it is not mandatory to enter location information
            url += String.format("&minlatitude=%s",minLatitude);
        }
        if(!StringUtils.isEmpty(maxLatitude)){
            url += String.format("&maxlatitude=%s",maxLatitude);
        }
        if(!StringUtils.isEmpty(minLongitude)){
            url += String.format("&minlongitude=%s",minLongitude);
        }
        if(!StringUtils.isEmpty(maxLongitude)){
            url += String.format("&maxlongitude=%s",maxLongitude);
        }
        url += "&limit=10";
//        System.out.println(url);
        String response = restTemp.getForObject(url, String.class);
        JsonNode rootNode = objectMap.readTree(response);
        JsonNode featureNode = rootNode.path("features");
        List<EarthquakeEntity> earthquakes = new ArrayList<>();
        for (JsonNode featuresNode : featureNode) {
            JsonNode propertiesNode = featuresNode.path("properties");
            String country = propertiesNode.path("place").asText().split(", ")[0];
            String magnitude = propertiesNode.path("mag").asText().split(", ")[0];
            String date = propertiesNode.path("time").asText().split(", ")[0];
           //Date dateDeneme = formatter.parse(date);
            Instant dateFormatted = Instant.ofEpochMilli(Long.parseLong(date));
            System.out.print(dateFormatted);
            Date time = Date.from(dateFormatted);
            EarthquakeEntity earthquakeEntity = new EarthquakeEntity(country,magnitude,time);
            earthquakes.add(earthquakeEntity);
        }
        return earthquakes;
    }
}
