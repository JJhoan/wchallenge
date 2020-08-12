package com.wolox.wchallenge.service.placeHolder;

import com.wolox.wchallenge.dto.AlbumDto;

import java.util.List;

public interface AlbumPlaceHolder {

    List<AlbumDto> list();

    List<AlbumDto> findAlbumByUser(Long id);
}
