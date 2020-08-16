package com.wolox.wchallenge.service;

import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.model.PrivilegesManagement;
import com.wolox.wchallenge.security.ApplicationUserPermission;

import java.util.List;
import java.util.Set;

public interface PrivilegeManagementService {

    PrivilegesManagement shareByUser(Long idUser, Long idAlbum, Set<ApplicationUserPermission> applicationUserPermissions);

    PrivilegesManagement findPermissions(Long idUser, Long idAlbum);

    PrivilegesManagement updatePermissions(PrivilegesManagement privilegesManagement);

    List<UserDto> usersByAlbumAndPermission(Long idAlbum, ApplicationUserPermission applicationUserPermission);

}
