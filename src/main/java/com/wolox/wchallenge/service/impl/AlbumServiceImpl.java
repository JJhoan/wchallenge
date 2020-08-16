package com.wolox.wchallenge.service.impl;

import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.repository.IAccessUserAlbumRepository;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.constant.PlaceHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    public final IAccessUserAlbumRepository IAccessUserAlbumRepository;

    public AlbumServiceImpl(IAccessUserAlbumRepository IAccessUserAlbumRepository) {
        this.IAccessUserAlbumRepository = IAccessUserAlbumRepository;
    }

    @Override
    public List<AlbumDto> list() {
        return listOfAlbums(PlaceHolder.ALBUMS);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<AlbumDto[]> response = restTemplate.getForEntity(PlaceHolder.ALBUMS, AlbumDto[].class);
//        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    @Override
    public List<AlbumDto> findAlbumsByUser(Long userId) {
        return listOfAlbums(PlaceHolder.ALBUMS_BY_USER + userId);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<AlbumDto[]> response = restTemplate.getForEntity(PlaceHolder.ALBUMS_BY_USER+userId, AlbumDto[].class);
//        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    @Override
    public AlbumDto getAlbum(Long idAlbum) {
        return album(PlaceHolder.ALBUMS_BY_ID + idAlbum);
    }

    public List<AlbumDto> listOfAlbums(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AlbumDto[]> response = restTemplate.getForEntity(url, AlbumDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public AlbumDto album(String url) {
        return listOfAlbums(url).stream().findFirst().orElse(null);
    }

}
