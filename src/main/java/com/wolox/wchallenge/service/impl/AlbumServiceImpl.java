package com.wolox.wchallenge.service.impl;

import com.wolox.wchallenge.constant.PlaceHolder;
import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.repository.IPrivilegeManagementRepository;
import com.wolox.wchallenge.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class AlbumServiceImpl implements AlbumService {

    public final IPrivilegeManagementRepository privilegeManagementRepository;

    public AlbumServiceImpl(IPrivilegeManagementRepository privilegeManagementRepository) {
        this.privilegeManagementRepository = privilegeManagementRepository;
    }

    @Override
    public List<AlbumDto> list() {
        return listOfAlbums(PlaceHolder.ALBUMS);
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
