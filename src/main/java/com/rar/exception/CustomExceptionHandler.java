package com.rar.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{

      @ExceptionHandler(Exception.class)
     public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(500,"Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(404,ex.getMessage(), Collections.singletonList("This id is not there in database"));
       return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectFieldException.class)
    public final ResponseEntity<Object> handleIncorrectFieldException(IncorrectFieldException ex,WebRequest webRequest){
        ErrorResponse error = new ErrorResponse(400,ex.getMessage(), Collections.singletonList("Incorrect fields given"));
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        List<String> field=new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            field.add(fieldName);
            details.add(fieldName+" : "+errorMessage);
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        ObjectError objectError;
        String methodUsed=ex.getMethod().toUpperCase();
        Set<HttpMethod> httpMethods=ex.getSupportedHttpMethods();
        System.out.println(methodUsed+" "+ex.getSupportedHttpMethods()+" "+ex.getSupportedMethods());
        ErrorResponse error = new ErrorResponse(405,"Method Not Allowed", Collections.singletonList(methodUsed+" method is not allowed for this api..!! Use "+httpMethods+" method instead.."));
        return new ResponseEntity<>(error,HttpStatus.METHOD_NOT_ALLOWED);
    }

}
