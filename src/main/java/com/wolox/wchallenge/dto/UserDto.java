package com.wolox.wchallenge.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {

    private String id;

    private String name;

    private String email;

}
