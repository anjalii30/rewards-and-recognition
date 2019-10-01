package com.rar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidProjectException extends RuntimeException {

    public InvalidProjectException(String message){
        super(message);
    }
}
