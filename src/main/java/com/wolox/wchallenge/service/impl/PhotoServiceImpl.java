package com.wolox.wchallenge.service.impl;

import com.google.common.collect.ImmutableMap;
import com.wolox.wchallenge.constant.PlaceHolder;
import com.wolox.wchallenge.controller.PhotoController;
import com.wolox.wchallenge.controller.UserController;
import com.wolox.wchallenge.dto.PhotoDto;
import com.wolox.wchallenge.service.PhotoService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Override
    public List<PhotoDto> list() {
        return listOfPhotos(PlaceHolder.PHOTOS);
    }

    @Override
    public List<PhotoDto> photosByUser(Long idUser) {
        Map<String, String> urlParams = new ImmutableMap.Builder<String, String >().put("idUser", idUser.toString()).build();
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromUriString(PlaceHolder.USERS + PhotoController.PHOTOS_BY_USER + "/albums");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PhotoDto[]> response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, null, PhotoDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public List<PhotoDto> listOfPhotos(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PhotoDto[]> response = restTemplate.getForEntity(url, PhotoDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}
