package com.wolox.wchallenge.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void givenAuthRequestOnUserAllService_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .getForEntity(UserController.USERS + UserController.ALL, String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
