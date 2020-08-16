package com.wolox.wchallenge.service.impl;

import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.model.AccessUserAlbum;
import com.wolox.wchallenge.repository.IAccessUserAlbumRepository;
import com.wolox.wchallenge.security.ApplicationUserPermission;
import com.wolox.wchallenge.service.AccessUserAlbumService;
import com.wolox.wchallenge.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccessUserAlbumServiceImpl implements AccessUserAlbumService {

    private final IAccessUserAlbumRepository accessUserAlbumRepository;
    private final IUserService userService;

    public AccessUserAlbumServiceImpl(IAccessUserAlbumRepository accessUserAlbumRepository, IUserService userService) {
        this.accessUserAlbumRepository = accessUserAlbumRepository;
        this.userService = userService;
    }

    @Override
    public AccessUserAlbum shareByUser(Long idUser, Long idAlbum, Set<ApplicationUserPermission> applicationUserPermissions) {
        AccessUserAlbum accessUserAlbum = new AccessUserAlbum(idUser, idAlbum, applicationUserPermissions);
        return accessUserAlbumRepository.save(accessUserAlbum);
    }

    @Override
    public AccessUserAlbum findPermissions(Long idUser, Long idAlbum) {
        return accessUserAlbumRepository.findByIdUserAndIdAlbum(idUser, idAlbum);
    }

    @Override
    public AccessUserAlbum updatePermissions(AccessUserAlbum accessUserAlbum) {
        return accessUserAlbumRepository.saveAndFlush(accessUserAlbum);
    }

    @Override
    public List<UserDto> usersByAlbumAndPermission(Long idAlbum, ApplicationUserPermission applicationUserPermission) {
        List<Long> idUsers = accessUserAlbumRepository.usersWithPermissionsInAlbum(idAlbum, applicationUserPermission);
        return idUsers.stream()
                .map(userService::getUser)
                .collect(Collectors.toList());
    }

}
