package com.wolox.wchallenge.security;

import com.wolox.wchallenge.controller.exception.AlbumNotFoundException;
import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.model.PrivilegesManagement;
import com.wolox.wchallenge.service.PrivilegeManagementService;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.service.UserService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;


public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private PrivilegeManagementService privilegeManagementService;
    private UserService userService;
    private AlbumService albumService;

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean hasAccess(Long idAlbum) {
        User user = (User) getPrincipal();
        AlbumDto album = albumService.getAlbum(idAlbum);
        if(album == null) {
            throw new AlbumNotFoundException("Not exist album.");
        }
        UserDto userDto = userService.findUserByUsername(user.getUsername());
        if(album.getUserId().equals(userDto.getId()) ) {
            return true;
        }
        PrivilegesManagement privilegesManagement = privilegeManagementService.findPermissions(userDto.getId(), idAlbum);
        return privilegesManagement != null && privilegesManagement.getPermissions().contains(ApplicationUserPermission.WRITE);
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

    public void setPrivilegeManagementService(PrivilegeManagementService privilegeManagementService){
        this.privilegeManagementService = privilegeManagementService;
    }

    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }
}
