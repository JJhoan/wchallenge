package com.wolox.wchallenge.service.impl;

import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.model.PrivilegesManagement;
import com.wolox.wchallenge.repository.IPrivilegeManagementRepository;
import com.wolox.wchallenge.security.ApplicationUserPermission;
import com.wolox.wchallenge.service.PrivilegeManagementService;
import com.wolox.wchallenge.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PrivilegeManagementServiceImpl implements PrivilegeManagementService {

    private final IPrivilegeManagementRepository privilegeManagementRepository;
    private final IUserService userService;

    public PrivilegeManagementServiceImpl(IPrivilegeManagementRepository privilegeManagementRepository, IUserService userService) {
        this.privilegeManagementRepository = privilegeManagementRepository;
        this.userService = userService;
    }

    @Override
    public PrivilegesManagement shareByUser(Long idUser, Long idAlbum, Set<ApplicationUserPermission> applicationUserPermissions) {
        PrivilegesManagement privilegesManagement = new PrivilegesManagement(idUser, idAlbum, applicationUserPermissions);
        return privilegeManagementRepository.save(privilegesManagement);
    }

    @Override
    public PrivilegesManagement findPermissions(Long idUser, Long idAlbum) {
        return privilegeManagementRepository.findByIdUserAndIdAlbum(idUser, idAlbum);
    }

    @Override
    public PrivilegesManagement updatePermissions(PrivilegesManagement privilegesManagement) {
        return privilegeManagementRepository.saveAndFlush(privilegesManagement);
    }

    @Override
    public List<UserDto> usersByAlbumAndPermission(Long idAlbum, ApplicationUserPermission applicationUserPermission) {
        List<Long> idUsers = privilegeManagementRepository.usersWithPermissionsInAlbum(idAlbum, applicationUserPermission);
        return idUsers.stream()
                .map(userService::getUser)
                .collect(Collectors.toList());
    }

}
