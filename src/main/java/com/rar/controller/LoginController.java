package com.rar.controller;

import com.rar.model.UserInfo;
import com.rar.service.LoginService;
import com.rar.utils.CheckValidity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@CrossOrigin
@RestController

public class LoginController{

    @Autowired
    private LoginService loginService;

    @Autowired
    private CheckValidity validity;


    @PostMapping(value = "/login")
    public Object getToken(@RequestHeader(value = "Authorization") String token) throws Exception {
        String email=validity.check(token);
        return loginService.login(token);
    }

    @PostMapping("/saveUsers")
    public UserInfo saveLogin(@RequestHeader(value = "Authorization") String token, @RequestBody UserInfo users){
        String email=validity.check(token);
        return loginService.saveLogin(users);
    }


    @GetMapping(value = "/listUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String,Object>> listUser(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return loginService.findAll();
    }

    @GetMapping("/listUsers/{id}")
    public Optional<UserInfo> getById(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        String email=validity.check(token);

        return loginService.findById(id);
    }

    @GetMapping("/listUsersByEmail/{email}")
    public Optional<UserInfo> findByEmail(@RequestHeader(value = "Authorization") String token ,String email) {
        String email1=validity.check(token);
        return loginService.findByEmail(email);
    }

    @DeleteMapping("/deleteUsers/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
        String email=validity.check(token);
        loginService.deleteById(id);
        return "Deleted Successfully";
    }


    @GetMapping(value = "/hey", produces = MediaType.APPLICATION_JSON_VALUE)

    public @ResponseBody String hey(@RequestHeader(value = "Authorization") String token) throws Exception {


        String email=validity.check(token);
        return "Hii, You have successfully logged in..!!";


    }


}