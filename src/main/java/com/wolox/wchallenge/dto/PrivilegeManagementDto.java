package com.wolox.wchallenge.dto;

import com.wolox.wchallenge.security.ApplicationUserPermission;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PrivilegeManagementDto implements Serializable {

    private Long id;
    private Long idAlbum;
    private Long idUser;
    private Set<ApplicationUserPermission> permissions = new HashSet<>(EnumSet.allOf(ApplicationUserPermission.class));

}