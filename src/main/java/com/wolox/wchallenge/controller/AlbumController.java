package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AlbumController.ALBUMS)
public class AlbumController {

    public static final String ALBUMS = "/albums";

    public static final String ALL = "/all";

    public final AlbumService albumService;
    public final IUserService userService;

    public AlbumController(AlbumService albumService, IUserService userService) {
        this.albumService = albumService;
        this.userService = userService;
    }

    @GetMapping(value = ALL)
    public List<AlbumDto> all() {
        return albumService.list();
    }

}
