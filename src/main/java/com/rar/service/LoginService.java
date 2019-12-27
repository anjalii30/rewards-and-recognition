package com.rar.service;

import com.rar.dto.LoginUserDetails;
import com.rar.model.UserInfo;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface LoginService {

    LoginUserDetails login(String token) throws IOException, ParseException;

    ResponseEntity<UserInfo> findById(Long id);

    ResponseEntity<List<LoginUserDetails>> findAll();

    void deleteById(Long id);


    Long getIdByName(String userEmail);
}
