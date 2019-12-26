package com.rar.controller;

import com.rar.DTO.LoginUserDetails;
import com.rar.exception.RecordNotFoundException;
import com.rar.model.UserInfo;
import com.rar.repository.UserRepository;
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
    public ResponseEntity<LoginUserDetails> getToken(@RequestHeader(value = "Authorization") String token) throws Exception {
            return new ResponseEntity(loginService.login(token), HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @param users object that contains user details
     * @return saved user object.
     */
    @ApiOperation(value = "Save the user")
    @PostMapping("/saveLoginUsers")
    public ResponseEntity<UserInfo> saveLogin(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "User object store in database table", required = true) @Valid @RequestBody UserInfo users){
           validity.check(token);
           return new ResponseEntity(loginService.saveLogin(users),HttpStatus.OK);
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
     * @param id user id
     * @return String that displays user has been successfully deleted.
     */
    @ApiOperation(value = "Delete the user by user id")
    @DeleteMapping("/deleteUsers/{id}")
    public ResponseEntity<String> delete(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "User Id to delete user object", required = true) @PathVariable long id){
            validity.check(token);
            if (userRepository.existsById(id)) {
                loginService.deleteById(id);
                return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
            }else
                throw new RecordNotFoundException("user id not found");
    }
}