package com.rar.service;

import com.rar.model.EditUserDetails;
import com.rar.model.UserInfo;
import org.springframework.http.ResponseEntity;

public interface UserService {

    UserInfo save(UserInfo userInfo);


    public ResponseEntity userSave(UserInfo userInfo);

    public EditUserDetails listById(long id);

    EditUserDetails update(long id, EditUserDetails editUserDetails);
}
