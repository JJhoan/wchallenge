package com.wolox.wchallenge.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PrivilegeManagementNotFoundException extends RuntimeException {
    public PrivilegeManagementNotFoundException(String message) {
        super(message);
    }
}
