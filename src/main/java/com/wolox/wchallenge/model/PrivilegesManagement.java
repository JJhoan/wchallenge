package com.wolox.wchallenge.model;

import com.google.common.collect.Sets;
import com.wolox.wchallenge.security.ApplicationUserPermission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class PrivilegesManagement {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private Long idUser;

    @NaturalId
    private Long idAlbum;

    @ElementCollection(targetClass = ApplicationUserPermission.class)
    @Enumerated(EnumType.STRING)
    @Column
    private Set<ApplicationUserPermission> permissions;

    public PrivilegesManagement(Long idUser, Long idAlbum, ApplicationUserPermission... applicationUserPermissions) {
        this.idUser = idUser;
        this.idAlbum = idAlbum;
        this.permissions = Sets.newHashSet(applicationUserPermissions);
    }

    public PrivilegesManagement(Long idUser, Long idAlbum, Set<ApplicationUserPermission> applicationUserPermissions) {
        this.idUser = idUser;
        this.idAlbum = idAlbum;
        this.permissions = applicationUserPermissions;
    }

    public PrivilegesManagement(Long id, Long idUser, Long idAlbum, Set<ApplicationUserPermission> applicationUserPermissions) {
        this.id = id;
        this.idUser = idUser;
        this.idAlbum = idAlbum;
        this.permissions = applicationUserPermissions;
    }


}