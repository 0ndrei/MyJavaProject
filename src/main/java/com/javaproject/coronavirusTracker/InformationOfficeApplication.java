package com.javaproject.coronavirusTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InformationOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(InformationOfficeApplication.class, args);
	}

}
