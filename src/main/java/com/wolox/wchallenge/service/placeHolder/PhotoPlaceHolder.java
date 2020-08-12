package com.wolox.wchallenge.service.placeHolder;

import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.dto.PhotoDto;
import com.wolox.wchallenge.dto.UserDto;

import java.util.List;

public interface PhotoPlaceHolder {

    List<PhotoDto> list();

    List<PhotoDto> photosByUser(List<AlbumDto> albumDtoList);
}
