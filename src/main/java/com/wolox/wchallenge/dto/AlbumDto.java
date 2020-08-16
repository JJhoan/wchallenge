package com.wolox.wchallenge.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AlbumDto implements Serializable {

    private Long userId;
    private Long id;
    private String title;
}
