package com.rar.service;

import com.rar.DTO.LoginUserDetails;
import com.rar.model.UserInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LoginService {

    LoginUserDetails login(String token) throws Exception;

    ResponseEntity<UserInfo> findById(Long id);

    ResponseEntity<UserInfo> saveLogin(UserInfo userInfo);

    ResponseEntity<List<LoginUserDetails>> findAll();

    void deleteById(Long id);

    void deleteByEmail(String email);

    Long getIdByName(String user_email);
}
