package com.wolox.wchallenge.service.placeHolder;

import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.service.lists.UserList;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class UserPlaceHolderImpl implements UserPlaceHolder {

    @Override
    public List<UserDto> list() {
        RestTemplate restTemplate = new RestTemplate();
        UserList response = restTemplate.getForObject(PlaceHolder.PHOTOS, UserList.class);
        return Objects.requireNonNull(response).getDto();
    }
}
