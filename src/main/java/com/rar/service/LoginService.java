package com.rar.service;



import com.rar.model.UserInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LoginService {

    String login(String token) throws Exception;

    Optional<UserInfo> findById(Long id);

    Optional<UserInfo> findByEmail(String email);

    UserInfo saveLogin(UserInfo userInfo);

    List<Map<String, Object>> findAll();

    void deleteById(Long id);

    void deleteByEmail(String email);


    Long getIdByName(String user_email) throws Exception;
}
