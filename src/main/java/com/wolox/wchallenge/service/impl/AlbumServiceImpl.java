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
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AlbumDto[]> response = restTemplate.getForEntity(PlaceHolder.ALBUMS, AlbumDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    @Override
    public List<AlbumDto> findAlbumsByUser(Long id) {
        return list().stream()
                .filter(albumDto -> albumDto.getUserId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public AlbumDto getAlbum(Long idAlbum) {
        return list().stream()
                .filter(albumDto -> albumDto.getId().equals(idAlbum))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("The Album " + idAlbum + " does not exist"));
    }

    @Override
    public AlbumDto findAlbumByOwner(Long idAlbum, Long idUser) {
        return list().stream()
                .filter(albumDto -> albumDto.getId().equals(idAlbum) && albumDto.getUserId().equals(idUser))
                .findFirst().orElse(null);
    }


}
