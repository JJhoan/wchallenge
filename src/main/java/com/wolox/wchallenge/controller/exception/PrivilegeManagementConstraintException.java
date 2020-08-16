package com.wolox.wchallenge.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PrivilegeManagementConstraintException extends RuntimeException {
    public PrivilegeManagementConstraintException(String message) {
        super(message);
    }
}
