package com.wolox.wchallenge.service.impl;

import com.wolox.wchallenge.constant.PlaceHolder;
import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class IUserServiceImpl implements IUserService {

    @Override
    public List<UserDto> list() {
        return listOfUsers(PlaceHolder.USERS);
    }

    @Override
    public UserDto getUser(Long idUser) {
        return user(PlaceHolder.USERS_BY_ID + idUser);
    }

    @Override
    public UserDto findUserByUsername(String username) {
        return user(PlaceHolder.USER_BY_USERNAME + username);
    }

    public List<UserDto> listOfUsers(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto[]> response = restTemplate.getForEntity(url, UserDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public UserDto user(String url) {
        return listOfUsers(url).stream().findFirst().orElse(null);
    }

}
