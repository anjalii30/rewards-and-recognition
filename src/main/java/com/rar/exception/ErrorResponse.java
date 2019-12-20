package com.rar.exception;

import java.util.Collections;
import java.util.List;

public class ErrorResponse {

    private String status="error";
    private long errorCode;
    private String message;
    private List<String> details;

    //for MethodArgumentNotValidException
    public ErrorResponse(String message, List<String> details) {
        super();
        this.status="fail";
        this.message = message;
        this.details = details;
        this.errorCode=400;
    }

    //for Not found
    public ErrorResponse( long errorCode, String message) {

        this.errorCode = errorCode;
        this.message = message;
    this.details= Collections.singletonList("This id is not there in database..!!");

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }


}
