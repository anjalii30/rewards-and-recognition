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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
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
/*    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/
    /*@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
      *//*  List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }*//*
        Map errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();

            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        //return (ResponseEntity<Object>) errors;
        //ErrorResponse error = new ErrorResponse("Validation Failed", details);
        ErrorResponse error=new ErrorResponse()
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }*/

    @Override
   // @ResponseStatus(HttpStatus.BAD_REQUEST)
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

 /*   @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        //for(ObjectError error : ex.getHeaders()
        System.out.println(ex.getHeaders());
        ErrorResponse error = new ErrorResponse(404,"404 NOT FOUND", Collections.singletonList("Datat not found in database"));
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }*/
 /*   @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse error = new ErrorResponse(404,"404 NOT FOUND", Collections.singletonList("This method is not allowed for this api..!! "));
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }*/
}
