package com.fabinho.sasfusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = "com.fabinho")
public class SasfUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SasfUsersApplication.class, args);
	}

	 @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
