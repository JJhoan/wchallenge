package com.wolox.wchallenge.service;

import com.wolox.wchallenge.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> list();

    UserDto findUserById(Long idUser);

    UserDto findUserByUsername(String username);
}
