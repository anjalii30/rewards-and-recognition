package com.rar.controller;


import com.rar.model.UserInfo;
import com.rar.repository.UserRepository;
import com.rar.service.UserService;
import com.rar.utils.CheckValidity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckValidity validity;

    /**
     * @param token jwt token
     * @param userInfo UserInfo object
     * @return object of saved user.
     */
    @ApiOperation(value = "Save the user")
    @PostMapping("/saveUser")
    public ResponseEntity save(@RequestHeader(value = "Authorization") String token , @ApiParam(value = "user object store in database table", required = true) @Valid @RequestBody UserInfo userInfo){
        String email=validity.check(token);
        return new ResponseEntity<>(userService.userSave(userInfo), HttpStatus.OK) ;
    }
}
