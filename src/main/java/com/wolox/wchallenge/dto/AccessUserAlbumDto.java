package com.wolox.wchallenge.dto;

import com.wolox.wchallenge.security.ApplicationUserPermission;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Data
@Getter
public class AccessUserAlbumDto implements Serializable {

    private Long idAlbum;
    private Long idUser;
    private List<ApplicationUserPermission> permissions = new ArrayList<ApplicationUserPermission>(EnumSet.allOf(ApplicationUserPermission.class));;

    public AccessUserAlbumDto() {
        System.out.println("God");
    }

}