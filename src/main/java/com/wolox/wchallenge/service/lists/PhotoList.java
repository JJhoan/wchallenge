package com.wolox.wchallenge.service.lists;

import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.dto.PhotoDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhotoList implements Serializable {
    private List<PhotoDto> photoDtoList;

    public PhotoList() {
        photoDtoList = new ArrayList<>();
    }

    public List<PhotoDto> getDto() {
        return photoDtoList;
    }
}
