package com.wolox.wchallenge.service;

import com.wolox.wchallenge.model.AccessUserAlbum;
import com.wolox.wchallenge.security.ApplicationUserPermission;

import java.util.List;


public interface AccessUserAlbumService {

    AccessUserAlbum shareByUser(Long idUser, Long idAlbum, List<ApplicationUserPermission> applicationUserPermissions);

    AccessUserAlbum findPermissions(Long idUser, Long idAlbum);
}
