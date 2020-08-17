package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.controller.exception.CommentNotFoundException;
import com.wolox.wchallenge.dto.CommentDto;
import com.wolox.wchallenge.service.CommentService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "commentsByName", notes = "Retrieve all comments by name, " +
            "generate a exception when there is not comments with those name.")
    public ResponseEntity<List<CommentDto>> commentsByName(@PathVariable String name) {
        List<CommentDto> comments = commentService.findCommentsByName(name);
        if(comments == null || comments.isEmpty()) {
            throw new CommentNotFoundException("Not exist comments with those name.");
        }
        return ResponseEntity.ok(comments);
    }
}
