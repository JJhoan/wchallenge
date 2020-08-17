package com.wolox.wchallenge.service.impl;

import com.wolox.wchallenge.constant.PlaceHolder;
import com.wolox.wchallenge.dto.CommentDto;
import com.wolox.wchallenge.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public List<CommentDto> findCommentsByName(String name) {
        return listOfComments(PlaceHolder.COMMENTS_BY_NAME + name);
    }

    public List<CommentDto> listOfComments(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CommentDto[]> response = restTemplate.getForEntity(url, CommentDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

}
