package com.wolox.wchallenge.service;

import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserPlaceHolderImpl implements UserPlaceHolder{

    @Override
    public List<User> listUsers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response =
                restTemplate.getForEntity(PlaceHolder.USERS, User[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}
