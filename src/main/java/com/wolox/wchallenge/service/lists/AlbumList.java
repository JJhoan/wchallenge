package com.wolox.wchallenge.service.lists;

import com.wolox.wchallenge.dto.AlbumDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class AlbumList {
    private final List<AlbumDto> albumDtoList;

    public AlbumList() {
        albumDtoList = new ArrayList<>();
    }

    public List<AlbumDto> getDto() {
        return albumDtoList;
    }
}