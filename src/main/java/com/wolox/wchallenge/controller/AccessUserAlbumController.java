package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.AccessUserAlbumDto;
import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.service.AccessUserAlbumService;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AccessUserAlbumController.ACCESS_ALBUM)
public class AccessUserAlbumController {

    public static final String ACCESS_ALBUM = "/access";
    public static final String SHARE_BY_USER = "/shared";

    public final AlbumService albumService;
    public final IUserService userService;
    public final AccessUserAlbumService accessUserAlbumService;

    public AccessUserAlbumController(AlbumService albumService, IUserService userService, AccessUserAlbumService accessUserAlbumService) {
        this.albumService = albumService;
        this.userService = userService;
        this.accessUserAlbumService = accessUserAlbumService;
    }

    @PostMapping(value = SHARE_BY_USER)
    @PreAuthorize("hasAccess(#accessUserAlbumDto.getIdAlbum())")
    public void shareByUser(@RequestBody AccessUserAlbumDto accessUserAlbumDto) {
        AlbumDto album = albumService.getAlbum(accessUserAlbumDto.getIdAlbum());
        UserDto user = userService.findUserById(accessUserAlbumDto.getIdUser());
        accessUserAlbumService.shareByUser(user.getId(), album.getId(), accessUserAlbumDto.getPermissions());
    }

}
