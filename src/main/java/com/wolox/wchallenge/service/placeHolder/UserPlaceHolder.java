package com.wolox.wchallenge.service.placeHolder;

import com.wolox.wchallenge.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UserPlaceHolder {

    List<UserDto> list();

    UserDto findUser(String username) throws ChangeSetPersister.NotFoundException;
}
