package com.project.earthquake.earthquakewebapplication.controller;

import org.json.JSONObject;
import org.apache.commons.io.IOUtils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
public class EarthquakeController {
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getRadarData() throws IOException {
        ClassPathResource staticDataResource = new ClassPathResource("application.json");
        String staticDataString = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);

        return new ResponseEntity<>(
                new JSONObject(staticDataString).toMap(),
                HttpStatus.OK
        );
    }
}
