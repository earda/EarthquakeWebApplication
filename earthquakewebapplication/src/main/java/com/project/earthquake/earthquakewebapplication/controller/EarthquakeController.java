package com.project.earthquake.earthquakewebapplication.controller;

import com.project.earthquake.earthquakewebapplication.EarthquakeEntity;
import com.project.earthquake.earthquakewebapplication.service.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class EarthquakeController   {
    @Autowired
    private EarthquakeService earthquakeService;

    @GetMapping("/")
    public List<EarthquakeEntity> getEarthquakeData() throws IOException {
        return earthquakeService.getEarthquakeData();
    }

    @RequestMapping(value="/earthquakes", method= RequestMethod.GET)
    public String getEarthquakes(Model model) throws IOException {
        model.addAttribute("earthquakes", getEarthquakeData());
        return "earthquakes" ;
    }
}
