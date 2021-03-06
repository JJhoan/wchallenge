package com.wolox.wchallenge.service;

import com.wolox.wchallenge.dto.PhotoDto;

import java.util.List;

public interface PhotoService {

    List<PhotoDto> list();

    List<PhotoDto> photosByUser(Long idUser);
}
