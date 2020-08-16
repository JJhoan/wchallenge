package com.wolox.wchallenge.repository;

import com.wolox.wchallenge.model.PrivilegesManagement;
import com.wolox.wchallenge.security.ApplicationUserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPrivilegeManagementRepository extends JpaRepository<PrivilegesManagement, Long> {

    PrivilegesManagement findByIdUserAndIdAlbum(Long idUser, Long idAlbum);

    @Query("select a.idUser from PrivilegesManagement a " +
            "where a.idAlbum = :idAlbum " +
            "and :permission member of a.permissions ")
    List<Long> usersWithPermissionsInAlbum(@Param("idAlbum") Long idAlbum,
                                           @Param("permission") ApplicationUserPermission permission );

}
