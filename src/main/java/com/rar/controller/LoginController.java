package com.rar.controller;

import com.rar.config.CheckValidity;
import com.rar.dto.LoginUserDetails;
import com.rar.repository.UserRepository;
import com.rar.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Api(value="Login Management System")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private CheckValidity validity;

    @Autowired
    private UserRepository userRepository;

    /**
     * @param token (google token)
     * @return object that contains user details
     * @throws Exception that displays that the user who tries to login is not a valid user.
     */
    @ApiOperation(value = "Login by nineleaps gmail account")
    @PostMapping(value = "/login")
    public ResponseEntity<LoginUserDetails> getToken(@RequestHeader(value = "Authorization") String token) throws IOException, ParseException {
            return new ResponseEntity(loginService.login(token), HttpStatus.OK);
    }


    /**
     * @param token jwt token
     * @return list of users.
     */
    @ApiOperation(value = "Get the list of users")
    @GetMapping(value = "/listUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LoginUserDetails>> listUser(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return new ResponseEntity(loginService.findAll(),HttpStatus.OK);
    }

}