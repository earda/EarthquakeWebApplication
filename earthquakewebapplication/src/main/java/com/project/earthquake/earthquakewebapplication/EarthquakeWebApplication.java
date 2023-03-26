package com.project.earthquake.earthquakewebapplication;
import org.springframework.boot.CommandLineRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class EarthquakeWebApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EarthquakeWebApplication.class, args);
	}

	@Override
	public void run(String[] args) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();

		EarthquakeEntity earthquakeEntity = objectMapper.readValue("application.json", EarthquakeEntity.class);

		System.out.println(earthquakeEntity);
	}
}
