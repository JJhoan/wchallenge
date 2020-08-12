package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.service.placeHolder.AlbumPlaceHolder;
import com.wolox.wchallenge.service.placeHolder.UserPlaceHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AlbumController.ALBUMS)
public class AlbumController {

    public static final String ALBUMS = "/albums";

    public static final String ALL = "/all";

    public final AlbumPlaceHolder albumPlaceHolder;

    public AlbumController(AlbumPlaceHolder albumPlaceHolder) {
        this.albumPlaceHolder = albumPlaceHolder;
    }

    @GetMapping(value = ALL)
    public List<AlbumDto> all() {
        return albumPlaceHolder.list();
    }

}
