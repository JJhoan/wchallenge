package com.wolox.wchallenge.security;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public class WebSecurity {
    public boolean checkUserId(Authentication authentication,  HttpServletRequest request) {
        System.out.println("Hello");
        return true;
    }
}
