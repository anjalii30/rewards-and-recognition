package com.rar.controller;

import com.rar.pojo.LoginUserDetails;
import com.rar.entity.UserInfo;
import com.rar.service.LoginService;
import com.rar.service.impl.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public Object getToken(@RequestHeader(value = "Authorization") String token) throws Exception {
        return loginService.login(token);
    }

    /**
     * @param token jwt token
     * @param users object that contains user details
     * @return saved user object.
     */
    @ApiOperation(value = "Save the user")
    @PostMapping("/saveLoginUsers")
    public UserInfo saveLogin(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "User object store in database table", required = true) @Valid @RequestBody UserInfo users){
        validity.check(token);
        return loginService.saveLogin(users);
    }

    /**
     * @param token jwt token
     * @return list of users.
     */
    @ApiOperation(value = "Get the list of users")
    @GetMapping(value = "/listUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LoginUserDetails> listUser(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return loginService.findAll();
    }



    /**
     * @param token jwt token
     * @param email employee email
     * @return object of user based on email id.
     */
    @ApiOperation(value = "Get the user by email id")
    @GetMapping("/listUsersByEmail/{email}")
    public Optional<UserInfo> findByEmail(@RequestHeader(value = "Authorization") String token , @ApiParam(value = "User email to get user object", required = true) String email) {
        validity.check(token);
        return loginService.findByEmail(email);
    }

    /**
     * @param token jwt token
     * @param id user id
     * @return String that displays user has been successfully deleted.
     */
    @ApiOperation(value = "Delete the user by user id")
    @DeleteMapping("/deleteUsers/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "User Id to delete user object", required = true) @PathVariable long id){
        validity.check(token);
        loginService.deleteById(id);
        return "Deleted Successfully";
    }
}