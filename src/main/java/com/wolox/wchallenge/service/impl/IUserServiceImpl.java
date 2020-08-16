package com.wolox.wchallenge.service.impl;

import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.service.IUserService;
import com.wolox.wchallenge.constant.PlaceHolder;
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
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto[]> response = restTemplate.getForEntity(PlaceHolder.USERS, UserDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    @Override
    public UserDto findUserById(Long idUser) {
        return list().stream()
                .filter(userDto -> userDto.getId().equals(idUser))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User with ID " + idUser + " does not exist"));
    }

    @Override
    public UserDto findUserByUsername(String username) {
        return list().stream()
                .filter(userDto -> userDto.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User " + username + " does not exist"));
    }

}
