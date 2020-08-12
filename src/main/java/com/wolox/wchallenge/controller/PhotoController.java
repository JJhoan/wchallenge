package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.PhotoDto;
import com.wolox.wchallenge.service.placeHolder.PhotoPlaceHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(PhotoController.PHOTOS)
public class PhotoController {

    public static final String PHOTOS = "/photos";

    public static final String ALL = "/all";

    public final PhotoPlaceHolder photoPlacheHolder;

    public PhotoController(PhotoPlaceHolder photoPlacheHolder) {
        this.photoPlacheHolder = photoPlacheHolder;
    }

    @GetMapping(value = ALL)
    public List<PhotoDto> all() {
        return photoPlacheHolder.list();
    }
}
