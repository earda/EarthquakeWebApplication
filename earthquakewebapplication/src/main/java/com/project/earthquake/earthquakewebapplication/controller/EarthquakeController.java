package com.project.earthquake.earthquakewebapplication.controller;

import com.project.earthquake.earthquakewebapplication.EarthquakeEntity;
import com.project.earthquake.earthquakewebapplication.EarthquakeRequest;
import com.project.earthquake.earthquakewebapplication.service.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
public class EarthquakeController {

    @Autowired
    private EarthquakeService earthquakeService;

    @GetMapping("/earthquake")
    public String showEarthquakeForm(Model model) {
        model.addAttribute("earthquakeForm", new EarthquakeRequest());
        return "earthquake";
    }

    @PostMapping("/resultsPage")
    public String submitEarthquakeForm(@ModelAttribute("earthquakeForm") EarthquakeRequest earthquakeForm, Model model) throws IOException, ParseException {
        String startTime = earthquakeForm.getStartTime();
        String endTime = earthquakeForm.getEndTime();
        String minLatitude = earthquakeForm.getMinLatitude();
        String maxLatitude = earthquakeForm.getMaxLatitude();
        String minLongitude = earthquakeForm.getMinLongitude();
        String maxLongitude = earthquakeForm.getMaxLongitude();
        List<EarthquakeEntity> earthquakes = earthquakeService.getEarthquakeData(startTime,endTime,minLatitude,maxLatitude,minLongitude,maxLongitude);
        if(earthquakes.size() > 0){
            model.addAttribute("earthquakes", earthquakes);
        }

        return "resultsPage";
    }
}

