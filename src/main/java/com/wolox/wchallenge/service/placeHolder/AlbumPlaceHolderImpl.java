package com.wolox.wchallenge.service.placeHolder;

import com.wolox.wchallenge.dto.AlbumDto;
import com.wolox.wchallenge.service.lists.AlbumList;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class AlbumPlaceHolderImpl implements AlbumPlaceHolder {

    @Override
    public List<AlbumDto> list() {
        RestTemplate restTemplate = new RestTemplate();
        AlbumList response = restTemplate.getForObject(PlaceHolder.ALBUMS, AlbumList.class);
        return Objects.requireNonNull(response).getDto();
    }


}
