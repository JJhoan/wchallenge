package com.wolox.wchallenge;

import com.wolox.wchallenge.model.Usersito;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class WchallengeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WchallengeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Usersito[]> response =  restTemplate.getForEntity(
				"https://jsonplaceholder.typicode.com/users",
				Usersito[].class);
		List<Usersito> usersitos = Arrays.asList(Objects.requireNonNull(response.getBody()));

	}
}
