package com.project.earthquake.earthquakewebapplication.controller;

import com.project.earthquake.earthquakewebapplication.EarthquakeEntity;
import com.project.earthquake.earthquakewebapplication.service.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
public class EarthquakeController   {
    @Autowired
    private EarthquakeService earthquakeService;

    @GetMapping("/earthquake")
    public ModelAndView getEarthquakeData() throws IOException {
        List<EarthquakeEntity> earthquakes = null;
        try {
            earthquakes = earthquakeService.getEarthquakeData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ModelAndView mav = new ModelAndView("earthquake");
        mav.addObject("earthquakes", earthquakes);
        return mav;
    }
}
