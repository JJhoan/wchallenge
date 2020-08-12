package com.wolox.wchallenge.service.placeHolder;

import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.dto.PhotoDto;
import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.service.lists.PhotoList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PhotoPlaceHolderImpl implements PhotoPlaceHolder {

    @Override
    public List<PhotoDto> list() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PhotoDto[]> response = restTemplate.getForEntity(PlaceHolder.PHOTOS, PhotoDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    @Override
    public List<PhotoDto> photosByUser(List<AlbumDto> albumDtoList) {
        List<PhotoDto> photoDtos = new ArrayList<>();
        List<PhotoDto> list = list();
        return list().stream()
                .filter(p -> albumDtoList.stream()
                        .map(AlbumDto::getId)
                        .anyMatch(id -> id.equals(p.albumId)))
                .collect(Collectors.toList());
    }
}
