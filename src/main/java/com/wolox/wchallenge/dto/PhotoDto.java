package com.wolox.wchallenge.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class PhotoDto implements Serializable    {

    public Long albumId;

    public Long Id;

    public String title;

    public String url;

    public String thumbnailUrl;
}
