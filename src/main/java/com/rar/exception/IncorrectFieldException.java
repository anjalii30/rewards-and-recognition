package com.rar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class IncorrectFieldException extends RuntimeException
{
    public IncorrectFieldException(String exception) {
        super(exception);
        ErrorResponse errorResponse=new ErrorResponse(400,exception);

    }
}
