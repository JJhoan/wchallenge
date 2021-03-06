package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.controller.exception.UserNotFoundException;
import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UserController.USERS)
public class UserController {

    public static final String USERS = "/users";
    public static final String ALL = "/all";

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = ALL)
    @ApiOperation(value = "Giving all users", response = UserDto[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved users"),
            @ApiResponse(code = 404, message = "users not found")
    })
    public ResponseEntity<List<UserDto>> all() {
        List<UserDto> all = userService.list();
        if(all == null || all.isEmpty()) {
            throw new UserNotFoundException("Not exist users.");
        }
        return ResponseEntity.ok(all);
    }
}
