/*
package com.rar.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.xml.ws.ResponseWrapper;
import java.io.IOException;

@Component
public class ResponseWrappingFilter extends GenericFilterBean {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        // Perform the rest of the chain, populating the response.
        chain.doFilter(request, response);

        // No way to read the body from the response here. getBody() doesn't exist.
        response.setBody(new ResponseWrapper(response.getStatus(), response.getBody()));
    }
}

*/
