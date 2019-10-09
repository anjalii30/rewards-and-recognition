/*
package com.rar.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

*/
/**
 * @author woemler
 *//*

public class RestUtils {

    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return !status.equals(HttpStatus.NOT_FOUND) && (HttpStatus.Series.SERVER_ERROR.equals(series) || HttpStatus.Series.CLIENT_ERROR
                .equals(series));
    }

    public static HttpEntity<String> createRequest(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<String>(headers);
    }

    public static RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    public static <T> T fetchSingle(String url, Class<T> model, String id){
        HttpEntity<String> request = createRequest();
        ResponseEntity<String> response = getRestTemplate().exchange(
                url,
                HttpMethod.GET,
                request,
                String.class,
                id
        );
        try {
            if (RestUtils.isError(response.getStatusCode())) {
                RestError restError = getObjectMapper().readValue(response.getBody(), RestError.class);
                throw new RestClientException("[" + restError.getCode() + "] " + restError.getMessage());
            } else {
                return getObjectMapper().readValue(response.getBody(), model);
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static <T> T fetchSingle(String url, Class<T> model, Object[] parameters){
        HttpEntity<String> request = createRequest();
        ResponseEntity<String> response = getRestTemplate().exchange(
                url,
                HttpMethod.GET,
                request,
                String.class,
                parameters
        );
        try {
            if (RestUtils.isError(response.getStatusCode())) {
                RestError restError = getObjectMapper().readValue(response.getBody(), RestError.class);
                throw new RestClientException("[" + restError.getCode() + "] " + restError.getMessage());
            } else {
                return getObjectMapper().readValue(response.getBody(), model);
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fetchMultiple(String url, Class<T> model, Object[] parameters){
        HttpEntity<String> request = createRequest();
        ResponseEntity<String> response = getRestTemplate().exchange(
                url,
                HttpMethod.GET,
                request,
                String.class,
                parameters
        );
        try {
            if (RestUtils.isError(response.getStatusCode())) {
                RestError restError = getObjectMapper().readValue(response.getBody(), RestError.class);
                throw new RestClientException("[" + restError.getCode() + "] " + restError.getMessage());
            } else {
                return getObjectMapper().readValue(response.getBody(), getObjectMapper().getTypeFactory().constructCollectionType(List.class, model));

            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fetchMultiple(String url, Class<T> model){
        return fetchMultiple(url, model, new Object[]{});
    }

    public static class RestError {

        private HttpStatus status;
        private Integer code;
        private String message;
        private String developerMessage;
        private String moreInfoUrl;

        public RestError() { }

        public RestError(HttpStatus status, Integer code, String message, String developerMessage,
                         String moreInfoUrl) {
            if (status == null){
                throw new NullPointerException("HttpStatus argument cannot be null.");
            }
            this.status = status;
            this.code = code;
            this.message = message;
            this.developerMessage = developerMessage;
            this.moreInfoUrl = moreInfoUrl;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public String getDeveloperMessage() {
            return developerMessage;
        }

        public String getMoreInfoUrl() {
            return moreInfoUrl;
        }

    }

}
*/
