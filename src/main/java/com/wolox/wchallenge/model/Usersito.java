package com.wolox.wchallenge.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Usersito implements Serializable {

    private Integer id;

    private String name;

    private String username;

    public Usersito() {
    }
}
