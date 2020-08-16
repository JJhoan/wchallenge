package com.wolox.wchallenge.repository;

import com.wolox.wchallenge.dto.AccessUserAlbumDto;
import com.wolox.wchallenge.model.AccessUserAlbum;
import org.springframework.stereotype.Component;

@Component
public class AccessUserAlbumMapper {

    public AccessUserAlbum mapToEntity(AccessUserAlbumDto accessUserAlbumDto) {
        return new AccessUserAlbum(accessUserAlbumDto.getIdUser(),
                accessUserAlbumDto.getIdAlbum(),
                accessUserAlbumDto.getPermissions());
    }

    public AccessUserAlbumDto mapToDto(AccessUserAlbum accessUserAlbum) {
        AccessUserAlbumDto accessUserAlbumDto = new AccessUserAlbumDto();
        accessUserAlbumDto.setIdUser(accessUserAlbum.getIdUser());
        accessUserAlbumDto.setIdAlbum(accessUserAlbum.getIdAlbum());
        accessUserAlbumDto.setPermissions(accessUserAlbum.getPermissions());
        return accessUserAlbumDto;
    }
}
