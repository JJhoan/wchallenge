package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.controller.exception.PhotoNotFoundException;
import com.wolox.wchallenge.dto.PhotoDto;
import com.wolox.wchallenge.service.PhotoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    public PhotoController(PhotoService PhotoService) {
        this.photoService = PhotoService;
    }

    @GetMapping(value = ALL)
    @ApiOperation(value = "Giving all the photos", response = PhotoDto[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved photos"),
            @ApiResponse(code = 404, message = "Photo not found")
    })
    public ResponseEntity<List<PhotoDto>> all() {
        List<PhotoDto> all = photoService.list();
        if(all == null || all.isEmpty()) {
            throw new PhotoNotFoundException("Not exist photos.");
        }
        return ResponseEntity.ok(all);
    }

    @GetMapping(value = PHOTOS_BY_USER )
    @ApiOperation(value = "Giving all photos by user", response = PhotoDto[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved photos"),
            @ApiResponse(code = 404, message = "Photos not found for user")
    })
    public ResponseEntity<List<PhotoDto>> photosByUser(@PathVariable Long idUser) {
        List<PhotoDto> photos = photoService.photosByUser(idUser);
        if(photos == null || photos.isEmpty()) {
            throw new PhotoNotFoundException("Not exist photos for the user " + idUser + ".");
        }
        return ResponseEntity.ok(photos);
    }
}
