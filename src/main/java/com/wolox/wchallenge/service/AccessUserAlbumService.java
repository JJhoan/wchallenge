package com.wolox.wchallenge.service;

import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.model.AccessUserAlbum;
import com.wolox.wchallenge.security.ApplicationUserPermission;

import java.util.List;
import java.util.Set;

public interface AccessUserAlbumService {

    int id = 1;

    AccessUserAlbum shareByUser(Long idUser, Long idAlbum, Set<ApplicationUserPermission> applicationUserPermissions);

    AccessUserAlbum findPermissions(Long idUser, Long idAlbum);

    AccessUserAlbum updatePermissions(AccessUserAlbum accessUserAlbum);

    List<UserDto> usersByAlbumAndPermission(Long idAlbum, ApplicationUserPermission applicationUserPermission);

}
