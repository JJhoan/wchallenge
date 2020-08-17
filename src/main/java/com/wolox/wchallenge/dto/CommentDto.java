package com.wolox.wchallenge.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CommentDto implements Serializable {

    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;

}
