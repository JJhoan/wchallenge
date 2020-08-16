package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.controller.exception.PhotoNotFoundException;
import com.wolox.wchallenge.dto.PhotoDto;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.service.IUserService;
import com.wolox.wchallenge.service.PhotoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(PhotoController.PHOTOS)
public class PhotoController {

    public static final String PHOTOS = "/photos";
    public static final String ALL = "/all";
    public static final String PHOTOS_BY_USER = "/{idUser}";

    public final PhotoService photoService;
    public final IUserService userService;
    public final AlbumService albumService;

    public PhotoController(PhotoService PhotoService, IUserService userService, AlbumService AlbumService) {
        this.photoService = PhotoService;
        this.userService = userService;
        this.albumService = AlbumService;
    }

    @GetMapping(value = ALL)
    @ApiOperation(value = "retrieveAllPhotos", notes = "Retrieve all photos, generate a exception when photos not exist.")
    public ResponseEntity<List<PhotoDto>> all() {
        List<PhotoDto> all = photoService.list();
        if(all == null || all.isEmpty()) {
            throw new PhotoNotFoundException("Not exist photos.");
        }
        return ResponseEntity.ok(all);
    }

    @GetMapping(value = PHOTOS_BY_USER )
    public ResponseEntity<List<PhotoDto>> photosByUser(@PathVariable Long idUser) {
        List<PhotoDto> photos = photoService.photosByUser(idUser);
        if(photos == null || photos.isEmpty()) {
            throw new PhotoNotFoundException("Not exist photos for the user " + idUser + ".");
        }
        return ResponseEntity.ok(photos);

    }
}
