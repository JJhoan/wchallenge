package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.service.UserPlaceHolder;
import org.apache.catalina.User;
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
    public List<User> all() {
        return userPlaceHolder.listUsers();
    }
}
