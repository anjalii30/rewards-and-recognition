package com.rar.controller;


import com.rar.pojo.EditUserDetails;
import com.rar.repository.UserRepository;
import com.rar.service.UserService;
import com.rar.service.impl.CheckValidity;
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
     * @param editUserDetails UserInfo object
     * @return object of saved user.
     */
    @ApiOperation(value = "Save the user")
    @PostMapping("/saveUser")
    public ResponseEntity save(@RequestHeader(value = "Authorization") String token , @ApiParam(value = "user object store in database table", required = true) @Valid @RequestBody EditUserDetails editUserDetails){
        validity.check(token);
        return new ResponseEntity<>(userService.userSave(editUserDetails), HttpStatus.OK) ;
    }


    /**
     * @param token jwt token
     * @param id user id
     * @return object of user based on id.
     */
    @ApiOperation(value = "Get the user details for editing by user id")
    @GetMapping("/listUser/{id}")
    public EditUserDetails listById(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "User Id to get user object", required = true)@PathVariable Long id){
        validity.check(token);
        return userService.listById(id);
    }

    /**
     * @param token jwt token
     * @param id user id
     * @param editUserDetails user object
     * @return object of reward after updating.
     */
    @ApiOperation(value = "Update the reward by id")
    @PutMapping("/updateUser/{id}")
    public EditUserDetails update(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "User Id to update user details", required = true)@PathVariable Long id, @ApiParam(value = "User object ", required = true) @Valid @RequestBody EditUserDetails editUserDetails){
        validity.check(token);
        return userService.update(id, editUserDetails);
    }
}
