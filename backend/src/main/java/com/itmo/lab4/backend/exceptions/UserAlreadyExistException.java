package com.itmo.lab4.backend.exceptions;


import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {

    public UserAlreadyExistException() {
        super("User already exist");
    }

    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
