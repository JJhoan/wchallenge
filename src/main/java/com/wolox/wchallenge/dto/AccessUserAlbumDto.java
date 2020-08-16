package com.wolox.wchallenge.dto;

import com.wolox.wchallenge.security.ApplicationUserPermission;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
public class AccessUserAlbumDto implements Serializable {

    private Long idAlbum;
    private Long idUser;
    private Set<ApplicationUserPermission> permissions = new HashSet<>(EnumSet.allOf(ApplicationUserPermission.class));

}