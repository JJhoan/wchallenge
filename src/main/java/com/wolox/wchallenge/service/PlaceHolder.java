package com.wolox.wchallenge.service;

import org.springframework.jmx.access.InvalidInvocationException;

final class PlaceHolder {

    private PlaceHolder() {
        throw new InvalidInvocationException("Invalid invocation");
    }

    public static final String URL_PLACE_HOLDER = "https://jsonplaceholder.typicode.com";

    public static final String USERS = URL_PLACE_HOLDER + "/users";

    public static final String PHOTOS = URL_PLACE_HOLDER + "/photos";

    public static final String ALBUMS = URL_PLACE_HOLDER + "/albums";


}
