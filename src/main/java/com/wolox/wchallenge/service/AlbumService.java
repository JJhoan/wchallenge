package com.wolox.wchallenge.service;

import com.wolox.wchallenge.dto.AlbumDto;

import java.util.List;

public interface AlbumService {

    List<AlbumDto> list();

    AlbumDto getAlbum(Long idAlbum);

}
