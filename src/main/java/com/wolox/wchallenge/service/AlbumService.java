package com.wolox.wchallenge.service;

import com.wolox.wchallenge.dto.AccessUserAlbumDto;
import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.model.AccessUserAlbum;
import com.wolox.wchallenge.security.ApplicationUserPermission;

import java.util.List;

public interface AlbumService {

    List<AlbumDto> list();

    List<AlbumDto> findAlbumsByUser(Long id);

    AlbumDto getAlbum(Long idAlbum);

    AlbumDto findAlbumByOwner(Long idAlbum, Long idUser);

}
