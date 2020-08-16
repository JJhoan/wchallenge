package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UserController.USERS)
public class UserController {

    public static final String USERS = "/users";

    public static final String ALL = "/all";

    public final IUserService IUserService;

    public UserController(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    @GetMapping(value = ALL)
    public List<UserDto> all() {
        return IUserService.list();
    }
}
