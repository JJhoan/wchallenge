package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.controller.exception.AlbumNotFoundException;
import com.wolox.wchallenge.controller.exception.PrivilegeManagementConstraintException;
import com.wolox.wchallenge.controller.exception.PrivilegeManagementNotFoundException;
import com.wolox.wchallenge.controller.exception.UserNotFoundException;
import com.wolox.wchallenge.dto.PrivilegeManagementDto;
import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.model.PrivilegesManagement;
import com.wolox.wchallenge.repository.PrivilegesManagementMapper;
import com.wolox.wchallenge.security.ApplicationUserPermission;
import com.wolox.wchallenge.service.PrivilegeManagementService;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PrivilegesManagementController.ACCESS_ALBUM)
public class PrivilegesManagementController {

    public static final String ACCESS_ALBUM = "/access";
    public static final String SHARE_BY_USER = "/shared";
    public static final String UPDATE_PERMISSIONS = "/permission";
    public static final String FILTERED_USERS = "/{idAlbum}/{permission}";

    public final AlbumService albumService;
    public final IUserService userService;
    public final PrivilegeManagementService privilegeManagementService;
    public final PrivilegesManagementMapper privilegesManagementMapper;

    public PrivilegesManagementController(
            AlbumService albumService,
            IUserService userService,
            PrivilegeManagementService privilegeManagementService,
            PrivilegesManagementMapper privilegesManagementMapper) {
        this.albumService = albumService;
        this.userService = userService;
        this.privilegeManagementService = privilegeManagementService;
        this.privilegesManagementMapper = privilegesManagementMapper;
    }

    @PostMapping(value = SHARE_BY_USER)
    @PreAuthorize("hasAccess(#privilegeManagementDto.getIdAlbum())")
    @ApiOperation(value = "shareAlbumWithOtherUsers",
            notes = "Save the privileges of a user on an album, " +
                    "generate a exception when : albums not exist, users not exist or there is a duplicate key.")
    public ResponseEntity<PrivilegeManagementDto> shareByUser(@RequestBody PrivilegeManagementDto privilegeManagementDto) {
        AlbumDto album = albumService.getAlbum(privilegeManagementDto.getIdAlbum());
        if (album == null) {
            throw new AlbumNotFoundException("Not exist album.");
        }
        UserDto user = userService.getUser(privilegeManagementDto.getIdUser());
        if (user == null) {
            throw new UserNotFoundException("Not exist user.");
        }
        try {
            PrivilegeManagementDto privilegeManagement =
                    privilegesManagementMapper.mapToDto(privilegeManagementService.shareByUser(
                            user.getId(),
                            album.getId(),
                            privilegeManagementDto.getPermissions()));
            return ResponseEntity.ok(privilegeManagement);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new PrivilegeManagementConstraintException("The user already have the privileges for the album.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = UPDATE_PERMISSIONS)
    @PreAuthorize("hasAccess(#privilegeManagementDto.getIdAlbum())")
    @ApiOperation(value = "updatePermissions",
            notes = "Update the privileges of a user on an album, generate a exception when privileges not exist.")
    public ResponseEntity<PrivilegeManagementDto> updatePermissions(@RequestBody PrivilegeManagementDto privilegeManagementDto) {
        PrivilegesManagement privilegesManagement =
                privilegeManagementService.findPermissions(
                        privilegeManagementDto.getIdUser(),
                        privilegeManagementDto.getIdAlbum());
        if(privilegesManagement == null) {
            throw new PrivilegeManagementNotFoundException("Not exist privileges for the user.");
        }
        PrivilegesManagement newPrivilegesManagement = new PrivilegesManagement(
                privilegesManagement.getId(),
                privilegesManagement.getIdUser(),
                privilegesManagement.getIdAlbum(),
                privilegeManagementDto.getPermissions());
        try {
            return ResponseEntity.ok(privilegesManagementMapper.mapToDto(
                    privilegeManagementService.updatePermissions(newPrivilegesManagement)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = FILTERED_USERS)
    @ApiOperation(value = "filterUsersWithPermissionsInAAlbum",
            notes = "Return the privileges of a user on an album, " +
                    "generate a exception when not exist users with those privileges.")
    public ResponseEntity<List<UserDto>> filteredUsers(
            @PathVariable Long idAlbum,
            @PathVariable ApplicationUserPermission permission) {
        List<UserDto> userDtoList =
                privilegeManagementService.usersByAlbumAndPermission(
                        idAlbum,
                        permission);
        if(userDtoList == null || userDtoList.isEmpty()) {
            throw new PrivilegeManagementNotFoundException("No exist users with those privileges");
        }
        return ResponseEntity.ok(userDtoList);
    }
}
