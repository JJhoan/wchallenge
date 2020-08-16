package com.wolox.wchallenge.repository;

import com.wolox.wchallenge.model.AccessUserAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccessUserAlbumRepository extends JpaRepository<AccessUserAlbum, Long> {

    AccessUserAlbum findByIdUserAndIdAlbum(Long idUser, Long idAlbum);

}
