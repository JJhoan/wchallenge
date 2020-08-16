package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.AccessUserAlbumDto;
import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.model.AccessUserAlbum;
import com.wolox.wchallenge.repository.AccessUserAlbumMapper;
import com.wolox.wchallenge.security.ApplicationUserPermission;
import com.wolox.wchallenge.service.AccessUserAlbumService;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AccessUserAlbumController.ACCESS_ALBUM)
public class AccessUserAlbumController {

    public static final String ACCESS_ALBUM = "/access";
    public static final String SHARE_BY_USER = "/shared";
    public static final String UPDATE_PERMISSIONS = "/permission";
    public static final String FILTERED_USERS = "/{idAlbum}/{permission}";

    public final AlbumService albumService;
    public final IUserService userService;
    public final AccessUserAlbumService accessUserAlbumService;
    public final AccessUserAlbumMapper accessUserAlbumMapper;

    public AccessUserAlbumController(AlbumService albumService, IUserService userService, AccessUserAlbumService accessUserAlbumService, AccessUserAlbumMapper accessUserAlbumMapper) {
        this.albumService = albumService;
        this.userService = userService;
        this.accessUserAlbumService = accessUserAlbumService;
        this.accessUserAlbumMapper = accessUserAlbumMapper;
    }

    @PostMapping(value = SHARE_BY_USER)
    @PreAuthorize("hasAccess(#accessUserAlbumDto.getIdAlbum())")
    public ResponseEntity<AccessUserAlbumDto> shareByUser(@RequestBody AccessUserAlbumDto accessUserAlbumDto) {
        AlbumDto album = albumService.getAlbum(accessUserAlbumDto.getIdAlbum());
        UserDto user = userService.getUser(accessUserAlbumDto.getIdUser());
        AccessUserAlbumDto accessUserAlbum = accessUserAlbumMapper.mapToDto(accessUserAlbumService.shareByUser(user.getId(), album.getId(), accessUserAlbumDto.getPermissions()));
        return ResponseEntity.ok(accessUserAlbum);
    }

    @PutMapping(path = UPDATE_PERMISSIONS)
    @PreAuthorize("hasAccess(#accessUserAlbumDto.getIdAlbum())")
    public ResponseEntity<AccessUserAlbumDto> updatePermissions(@RequestBody AccessUserAlbumDto accessUserAlbumDto) {
        AccessUserAlbum accessUserAlbum = accessUserAlbumService.findPermissions(accessUserAlbumDto.getIdUser(), accessUserAlbumDto.getIdAlbum());
        AccessUserAlbum newAccessUserAlbum = new AccessUserAlbum(
                accessUserAlbum.getId(),
                accessUserAlbum.getIdUser(),
                accessUserAlbum.getIdAlbum(),
                accessUserAlbumDto.getPermissions());
        return ResponseEntity.ok(accessUserAlbumMapper.mapToDto(accessUserAlbumService.updatePermissions(newAccessUserAlbum)));
    }

    @GetMapping(value = FILTERED_USERS)
    public ResponseEntity<List<UserDto>> filteredUsers(@PathVariable Long idAlbum, @PathVariable ApplicationUserPermission permission) {
        List<UserDto> userDtoList =
                accessUserAlbumService.usersByAlbumAndPermission(
                        idAlbum,
                        permission);
        return ResponseEntity.ok(userDtoList);
    }
}
