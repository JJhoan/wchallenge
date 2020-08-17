package com.wolox.wchallenge.service;

import com.wolox.wchallenge.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> list();

    UserDto getUser(Long idUser);

    UserDto findUserByUsername(String username);
}
