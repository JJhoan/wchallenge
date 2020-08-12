package com.wolox.wchallenge.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;

    private String name;

    private String username;

    private String email;

}