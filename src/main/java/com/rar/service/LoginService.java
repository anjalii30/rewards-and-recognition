package com.rar.service;



import com.rar.model.UserInfo;

import java.util.List;
import java.util.Optional;

public interface LoginService {

    String login(String token) throws Exception;

    Optional<UserInfo> findById(Long id);

    Optional<UserInfo> findByemail(String email);

    UserInfo saveLogin(UserInfo userInfo);

    List<UserInfo> findAll();

    void deleteById(Long id);

    void deleteByemail(String email);


    Long getIdByName(String user_email) throws Exception;
}
