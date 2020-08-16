package com.wolox.wchallenge;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootApplication
@ExtendWith(SpringExtension.class)
public class WchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WchallengeApplication.class, args);
	}


}
