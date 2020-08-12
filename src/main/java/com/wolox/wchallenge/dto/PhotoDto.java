package com.wolox.wchallenge.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PhotoDto {

    public Long albumId;

    public Long Id;

    public String title;

    public String url;

    public String thumbnailUrl;
}
