package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.dto.PhotoDto;
import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.service.AlbumService;
import com.wolox.wchallenge.service.PhotoService;
import com.wolox.wchallenge.service.IUserService;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    public final PhotoService PhotoService;

    public final IUserService IUserService;

    public final AlbumService AlbumService;

    public PhotoController(PhotoService PhotoService, IUserService IUserService, AlbumService AlbumService) {
        this.PhotoService = PhotoService;
        this.IUserService = IUserService;
        this.AlbumService = AlbumService;
    }

    @GetMapping(value = ALL)
    public List<PhotoDto> all() {
        return PhotoService.list();
    }

    @GetMapping(value = PHOTOS_BY_USER )
    public List<PhotoDto> photoByUser(@PathVariable Long idUser) {
        return PhotoService.photosByUser(idUser);
    }
}
