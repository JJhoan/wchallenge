package com.wolox.wchallenge.controller;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void givenAuthRequestOnCommentsByNameService_shouldSucceedWith200() {
        Map<String, String> urlParams = new ImmutableMap.Builder<String, String >().put("name", "id labore ex et quam laborum").build();
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromUriString(CommentController.COMMENTS + CommentController.COMMENTS_BY_NAME);
        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnCommentsByNameService_shouldFailWith404WhenTheNameNotExist() {
        Map<String, String> urlParams = new ImmutableMap.Builder<String, String >().put("name", "hello").build();
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromUriString(CommentController.COMMENTS + CommentController.COMMENTS_BY_NAME);
        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

}