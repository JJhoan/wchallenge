package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.dto.PhotoDto;
import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.service.placeHolder.AlbumPlaceHolder;
import com.wolox.wchallenge.service.placeHolder.PhotoPlaceHolder;
import com.wolox.wchallenge.service.placeHolder.UserPlaceHolder;
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

    public static final String PHOTOS_BY_USER = "/photosByUser/{username}";

    public final PhotoPlaceHolder photoPlaceHolder;

    public final UserPlaceHolder userPlaceHolder;

    public final AlbumPlaceHolder albumPlaceHolder;

    public PhotoController(PhotoPlaceHolder photoPlaceHolder, UserPlaceHolder userPlaceHolder, AlbumPlaceHolder albumPlaceHolder) {
        this.photoPlaceHolder = photoPlaceHolder;
        this.userPlaceHolder = userPlaceHolder;
        this.albumPlaceHolder = albumPlaceHolder;
    }

    @GetMapping(value = ALL)
    public List<PhotoDto> all() {
        return photoPlaceHolder.list();
    }

    @GetMapping(value = PHOTOS_BY_USER )
    public List<PhotoDto> photoByUser(@PathVariable String username) throws ChangeSetPersister.NotFoundException {
        UserDto userDto = userPlaceHolder.findUser(username);
        List<AlbumDto> albumDtoList = albumPlaceHolder.findAlbumByUser(userDto.getId());
        return photoPlaceHolder.photosByUser(albumDtoList);
    }
}
