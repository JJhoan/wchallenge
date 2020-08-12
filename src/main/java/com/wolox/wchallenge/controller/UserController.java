package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.service.placeHolder.UserPlaceHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UserController.USERS)
public class UserController {

    public static final String USERS = "/users";

    public static final String ALL = "/all";

    public final UserPlaceHolder userPlaceHolder;

    public UserController(UserPlaceHolder userPlaceHolder) {
        this.userPlaceHolder = userPlaceHolder;
    }

    @GetMapping(value = ALL)
    public List<UserDto> all() {
        return userPlaceHolder.list();
    }
}
