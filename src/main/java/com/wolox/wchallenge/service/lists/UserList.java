package com.wolox.wchallenge.service.lists;

import com.wolox.wchallenge.dto.UserDto;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserList implements Serializable {
    private List<UserDto> userDtoList;

    public UserList() {
        userDtoList = new ArrayList<>();
    }

}
