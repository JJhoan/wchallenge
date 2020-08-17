package com.wolox.wchallenge.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
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

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhotoControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void givenAuthRequestOnAllPhotosService_shouldSucceedWith200()  {
        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .getForEntity(PhotoController.PHOTOS + PhotoController.ALL, String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnPhotosByUserService_shouldSucceedWith200()  {
        Map<String, String> urlParams = new ImmutableMap.Builder<String, String >().put("idUser", "1").build();
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromUriString(PhotoController.PHOTOS + PhotoController.PHOTOS_BY_USER);
        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnPhotosByUserServiceEmptyUser_shouldFailWith404()  {
        Map<String, String> urlParams = new ImmutableMap.Builder<String, String >().put("idUser", "0").build();
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromUriString(PhotoController.PHOTOS + PhotoController.PHOTOS_BY_USER);
        ResponseEntity<String> result = template.withBasicAuth("Bret", "Bret")
                .exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

}