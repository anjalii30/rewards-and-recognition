package com.rar.service;

import com.rar.pojo.LoginUserDetails;
import com.rar.entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface LoginService {

    public Object login(String token) throws Exception;

    Optional<UserInfo> findById(Long id);

    Optional<UserInfo> findByEmail(String email);

    UserInfo saveLogin(UserInfo userInfo);

    List<LoginUserDetails> findAll();

    void deleteById(Long id);

    void deleteByEmail(String email);

    Long getIdByName(String user_email);
}
