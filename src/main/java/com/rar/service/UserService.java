package com.rar.service;

import com.rar.model.UserInfo;
import org.springframework.http.ResponseEntity;

public interface UserService {

    UserInfo save(UserInfo userInfo);
/*
    UserInfo update(long id, UserInfo userInfo);

    void deleteById(long id);

    List<UserInfo> findAll();*/

    public ResponseEntity userSave(UserInfo userInfo);


}