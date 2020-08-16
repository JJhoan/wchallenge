package com.wolox.wchallenge.repository;

import com.wolox.wchallenge.model.AccessUserAlbum;
import com.wolox.wchallenge.security.ApplicationUserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccessUserAlbumRepository extends JpaRepository<AccessUserAlbum, Long> {

    AccessUserAlbum findByIdUserAndIdAlbum(Long idUser, Long idAlbum);

    @Query("select a.idUser from AccessUserAlbum a " +
            "where a.idAlbum = :idAlbum " +
            "and :permission member of a.permissions ")
    List<Long> usersWithPermissionsInAlbum(@Param("idAlbum") Long idAlbum,
                                           @Param("permission") ApplicationUserPermission permission );

}
