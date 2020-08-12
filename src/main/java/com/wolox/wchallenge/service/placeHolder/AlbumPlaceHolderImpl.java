package com.wolox.wchallenge.service.placeHolder;

import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.service.lists.AlbumList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AlbumPlaceHolderImpl implements AlbumPlaceHolder {

    @Override
    public List<AlbumDto> list() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AlbumDto[]> response = restTemplate.getForEntity(PlaceHolder.ALBUMS, AlbumDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    @Override
    public List<AlbumDto> findAlbumByUser(Long id) {
        return list().stream()
                .filter(albumDto -> albumDto.userId.equals(id))
                .collect(Collectors.toList());
    }


}
