package com.home.HomeEnvironment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HomeEnvironmentApplication {
	public static void main(String[] args) {
		try {
			SpringApplication.run(HomeEnvironmentApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

