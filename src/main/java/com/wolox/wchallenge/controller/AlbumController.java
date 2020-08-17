package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.controller.exception.AlbumNotFoundException;
import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.service.AlbumService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AlbumController.ALBUMS)
public class AlbumController {

    public static final String ALBUMS = "/albums";
    public static final String ALL = "/all";

    public final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(value = ALL)
    @ApiOperation(value = "retrieveAllAlbums", notes = "Retrieve all albums, generate a exception when albums not exist.")
    public ResponseEntity<List<AlbumDto>> all() {
        List<AlbumDto> all = albumService.list();
        if(all == null || all.isEmpty()) {
            throw new AlbumNotFoundException("Not exist albums.");
        }
        return ResponseEntity.ok(all);
    }

}
