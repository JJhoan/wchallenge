package com.wolox.wchallenge.repository;

import com.wolox.wchallenge.dto.PrivilegeManagementDto;
import com.wolox.wchallenge.model.PrivilegesManagement;
import org.springframework.stereotype.Component;

@Component
public class PrivilegesManagementMapper {

    public PrivilegesManagement mapToEntity(PrivilegeManagementDto privilegeManagementDto) {
        return new PrivilegesManagement(privilegeManagementDto.getIdUser(),
                privilegeManagementDto.getIdAlbum(),
                privilegeManagementDto.getPermissions());
    }

    public PrivilegeManagementDto mapToDto(PrivilegesManagement privilegesManagement) {
        PrivilegeManagementDto privilegeManagementDto = new PrivilegeManagementDto();
        privilegeManagementDto.setIdUser(privilegesManagement.getIdUser());
        privilegeManagementDto.setIdAlbum(privilegesManagement.getIdAlbum());
        privilegeManagementDto.setPermissions(privilegesManagement.getPermissions());
        return privilegeManagementDto;
    }
}
