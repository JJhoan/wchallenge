package com.wolox.wchallenge.service.impl;

import com.wolox.wchallenge.model.AccessUserAlbum;
import com.wolox.wchallenge.repository.IAccessUserAlbumRepository;
import com.wolox.wchallenge.security.ApplicationUserPermission;
import com.wolox.wchallenge.service.AccessUserAlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessUserAlbumServiceImpl implements AccessUserAlbumService {

    private final IAccessUserAlbumRepository accessUserAlbumRepository;

    public AccessUserAlbumServiceImpl(IAccessUserAlbumRepository accessUserAlbumRepository) {
        this.accessUserAlbumRepository = accessUserAlbumRepository;
    }

    @Override
    public AccessUserAlbum shareByUser(Long idUser, Long idAlbum, List<ApplicationUserPermission> applicationUserPermissions) {
        AccessUserAlbum accessUserAlbum = new AccessUserAlbum(idUser, idAlbum, applicationUserPermissions);
        return accessUserAlbumRepository.save(accessUserAlbum);
    }

    @Override
    public AccessUserAlbum findPermissions(Long idUser, Long idAlbum) {
        return accessUserAlbumRepository.findByIdUserAndIdAlbum(idUser, idAlbum);
    }

}
