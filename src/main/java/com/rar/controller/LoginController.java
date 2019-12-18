package com.rar.controller;

import com.rar.DTO.LoginUserDetails;
import com.rar.model.UserInfo;
import com.rar.service.LoginService;
import com.rar.service.impl.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@Api(value="Login Management System")

public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private CheckValidity validity;

    /**
     * @param token (google token)
     * @return object that contains user details
     * @throws Exception that displays that the user who tries to login is not a valid user.
     */
    @ApiOperation(value = "Login by gmail Id")
    @PostMapping(value = "/login")
    public ResponseEntity<LoginUserDetails> getToken(@RequestHeader(value = "Authorization") String token) throws Exception {
        try {
            return new ResponseEntity(loginService.login(token), HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * @param token jwt token
     * @param users object that contains user details
     * @return saved user object.
     */
    @ApiOperation(value = "Save the user")
    @PostMapping("/saveLoginUsers")
    public ResponseEntity<UserInfo> saveLogin(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "User object store in database table", required = true) @Valid @RequestBody UserInfo users){
       try {
           validity.check(token);
           return new ResponseEntity(loginService.saveLogin(users),HttpStatus.OK);
       }catch(Exception e){
           return ResponseEntity.status(HttpStatus.CONFLICT).build();
       }
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

    /**
     * @param token jwt token
     * @param email employee email
     * @return object of user based on email id.
     */
    @ApiOperation(value = "Get the user by email id")
    @GetMapping("/listUsersByEmail/{email}")
    public ResponseEntity<UserInfo> findByEmail(@RequestHeader(value = "Authorization") String token , @ApiParam(value = "User email to get user object", required = true) String email) {
        try{
        validity.check(token);
        return new ResponseEntity(loginService.findByEmail(email),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param token jwt token
     * @param id user id
     * @return String that displays user has been successfully deleted.
     */
    @ApiOperation(value = "Delete the user by user id")
    @DeleteMapping("/deleteUsers/{id}")
    public ResponseEntity<String> delete(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "User Id to delete user object", required = true) @PathVariable long id){
        try {
            validity.check(token);
            loginService.deleteById(id);
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}