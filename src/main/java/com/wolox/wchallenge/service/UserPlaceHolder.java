package com.wolox.wchallenge.service;

import com.wolox.wchallenge.model.Usersito;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserPlaceHolder {

    public List<Usersito> listUsers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Usersito[]> response =  restTemplate.getForEntity(
                "https://jsonplaceholder.typicode.com/users",
                Usersito[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public Usersito findByUser(String userName) {
        return listUsers().stream().filter(usersito -> usersito.getUsername().equals(userName)).findFirst().orElseThrow(IllegalAccessError::new);
    }
}
