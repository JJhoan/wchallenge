package com.wolox.wchallenge.dto;

import com.wolox.wchallenge.security.ApplicationUserPermission;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PermissionInAnAlbum {

    private Long idUser;
    private String username;
    private Long idAlbum;
    private ApplicationUserPermission permission;

}
