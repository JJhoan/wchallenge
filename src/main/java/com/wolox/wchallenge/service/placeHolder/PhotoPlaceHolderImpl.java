package com.wolox.wchallenge.service.placeHolder;

import com.wolox.wchallenge.dto.PhotoDto;
import com.wolox.wchallenge.service.lists.PhotoList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PhotoPlaceHolderImpl implements PhotoPlaceHolder {

    @Override
    public List<PhotoDto> list() {
        RestTemplate restTemplate = new RestTemplate();
        PhotoList response = restTemplate.getForObject(PlaceHolder.PHOTOS, PhotoList.class);
        return Objects.requireNonNull(response).getDto();
    }
}
