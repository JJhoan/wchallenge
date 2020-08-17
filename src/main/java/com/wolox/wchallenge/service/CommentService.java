package com.wolox.wchallenge.service;

import com.wolox.wchallenge.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> findCommentsByName(String name);

}
