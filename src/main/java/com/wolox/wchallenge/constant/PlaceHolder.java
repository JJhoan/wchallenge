package com.wolox.wchallenge.constant;

import org.springframework.jmx.access.InvalidInvocationException;

public final class PlaceHolder {

    private PlaceHolder() {
        throw new InvalidInvocationException("Invalid invocation");
    }

    public static final String URL_PLACE_HOLDER = "https://jsonplaceholder.typicode.com";
    public static final String USERS = URL_PLACE_HOLDER + "/users";
    public static final String PHOTOS = URL_PLACE_HOLDER + "/photos";
    public static final String ALBUMS = URL_PLACE_HOLDER + "/albums";
    public static final String ALBUMS_BY_ID = ALBUMS + "?id=";
    public static final String ALBUMS_BY_USER = ALBUMS + "?userId=";
    public static final String USER_BY_USERNAME = USERS + "?username=";
    public static final String USERS_BY_ID = USERS + "?id=";
    public static final String PHOTOS_BY_USER = PHOTOS + "?userId=";

}
