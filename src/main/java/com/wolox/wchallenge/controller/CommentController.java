package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.controller.exception.CommentNotFoundException;
import com.wolox.wchallenge.dto.CommentDto;
import com.wolox.wchallenge.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CommentController.COMMENTS)
public class CommentController {

    public static final String COMMENTS = "/comments";
    public static final String COMMENTS_BY_NAME = "/{name}";

    public final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = COMMENTS_BY_NAME)
    @ApiOperation(value = "Giving all the comments", response = CommentDto[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved comments"),
            @ApiResponse(code = 404, message = "Comments not found")
    })
    public ResponseEntity<List<CommentDto>> commentsByName(@PathVariable String name) {
        List<CommentDto> comments = commentService.findCommentsByName(name);
        if(comments == null || comments.isEmpty()) {
            throw new CommentNotFoundException("Not exist comments with those name.");
        }
        return ResponseEntity.ok(comments);
    }
}
