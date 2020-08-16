package com.wolox.wchallenge.service.impl;

import com.wolox.wchallenge.constant.PlaceHolder;
import com.wolox.wchallenge.dto.PhotoDto;
import com.wolox.wchallenge.service.PhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Override
    public List<PhotoDto> list() {
        return listOfPhotos(PlaceHolder.PHOTOS);
    }

    @Override
    public List<PhotoDto> photosByUser(Long idUser) {
        return listOfPhotos(PlaceHolder.PHOTOS_BY_USER + idUser);
    }

    public List<PhotoDto> listOfPhotos(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PhotoDto[]> response = restTemplate.getForEntity(url, PhotoDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}
