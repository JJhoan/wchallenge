package com.wolox.wchallenge.model;

import com.google.common.collect.Sets;
import com.wolox.wchallenge.security.ApplicationUserPermission;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class AccessUserAlbum {

    @Id
    @GeneratedValue
    private Long id;

    private Long idUser;

    private Long idAlbum;

    @ElementCollection(targetClass = ApplicationUserPermission.class)
    @Enumerated(EnumType.STRING)
    @Column
    private Set<ApplicationUserPermission> permissions;

    public AccessUserAlbum(Long idUser,Long idAlbum, ApplicationUserPermission... applicationUserPermissions) {
        this.idUser = idUser;
        this.idAlbum = idAlbum;
        this.permissions = Sets.newHashSet(applicationUserPermissions);
    }

    public AccessUserAlbum(Long idUser, Long idAlbum, List<ApplicationUserPermission> applicationUserPermissions) {
        this.idUser = idUser;
        this.idAlbum = idAlbum;
        this.permissions = Sets.newHashSet(applicationUserPermissions);
    }


}