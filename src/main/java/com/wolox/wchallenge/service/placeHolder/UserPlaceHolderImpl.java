package com.wolox.wchallenge.service.placeHolder;

import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.service.lists.UserList;
import org.apache.catalina.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserPlaceHolderImpl implements UserPlaceHolder {

    @Override
    public List<UserDto> list() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto[]> response = restTemplate.getForEntity(PlaceHolder.USERS, UserDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    @Override
    public UserDto findUser(String username) throws ChangeSetPersister.NotFoundException {
        return list().stream()
                .filter(userDto -> userDto.getUsername().equals(username))
                .findFirst()
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}
