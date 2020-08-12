package com.wolox.wchallenge.service.lists;

import com.wolox.wchallenge.dto.UserDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private final List<UserDto> userDtoList;

    public UserList() {
        userDtoList = new ArrayList<>();
    }

    public List<UserDto> getDto() {
        return userDtoList;
    }
}
