package com.rar.controller;

import com.rar.DTO.EditUserDetails;
import com.rar.exception.RecordNotFoundException;
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
    public ResponseEntity<EditUserDetails> listById(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "User Id to get user object", required = true)@PathVariable Long id){
        validity.check(token);
        if(userRepository.existsById(id))
        return new ResponseEntity(userService.listById(id),HttpStatus.OK);
        else
            throw new RecordNotFoundException("user id not found");
    }

    /**
     * @param token jwt token
     * @param id user id
     * @param editUserDetails user object
     * @return object of reward after updating.
     */
    @ApiOperation(value = "Update the reward by id")
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<EditUserDetails> update(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "User Id to update user details", required = true)@PathVariable Long id, @ApiParam(value = "User object ", required = true) @Valid @RequestBody EditUserDetails editUserDetails) {
            validity.check(token);
            if (userRepository.existsById(id))
                return new ResponseEntity(userService.update(id, editUserDetails), HttpStatus.OK);
            else
                throw new RecordNotFoundException("user id not found");
    }

    /**
     *
     * @param token
     * @return The details of won coins
     */
    @ApiOperation(value = "get details of coins according to rewards")
    @GetMapping("/getMyCoins")
    public ResponseEntity getCoinsDetails(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return new ResponseEntity(userService.getCoinsDetails(email),HttpStatus.OK);
    }
}
