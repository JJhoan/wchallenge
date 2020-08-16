package com.wolox.wchallenge.security;

import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.model.AccessUserAlbum;
import com.wolox.wchallenge.service.AccessUserAlbumService;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.service.IUserService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;


public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private AccessUserAlbumService accessUserAlbumService;
    private IUserService userService;
    private AlbumService albumService;

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean hasAccess(Long idAlbum) {
        User user = (User) getPrincipal();
        AlbumDto album = albumService.getAlbum(idAlbum);
        if(album == null) {
            return false;
        }
        UserDto userDto = userService.findUserByUsername(user.getUsername());
        if(album.getUserId().equals(userDto.getId()) ) {
            return true;
        }
        AccessUserAlbum accessUserAlbum = accessUserAlbumService.findPermissions(userDto.getId(), idAlbum);
        return accessUserAlbum != null && accessUserAlbum.getPermissions().contains(ApplicationUserPermission.WRITE);
    }

    @Override
    public void setFilterObject(Object o) {

    }

    @Override
    public Object getFilterObject() {
        return null;
    }

    @Override
    public void setReturnObject(Object o) {

    }

    @Override
    public Object getReturnObject() {
        return null;
    }

    @Override
    public Object getThis() {
        return null;
    }

    public void setAccessUserAlbumService(AccessUserAlbumService accessUserAlbumService){
        this.accessUserAlbumService = accessUserAlbumService;
    }

    public void setUserService(IUserService userService){
        this.userService = userService;
    }

    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }
}
