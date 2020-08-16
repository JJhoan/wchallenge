package com.wolox.wchallenge.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class PhotoDto implements Serializable    {

    public Long Id;

    public Long albumId;

    public String title;

    public String url;

    public String thumbnailUrl;

}
