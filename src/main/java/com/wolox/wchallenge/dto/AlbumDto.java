package com.wolox.wchallenge.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AlbumDto implements Serializable {

    public Long userId;

    public Long id;

    public String title;
}
