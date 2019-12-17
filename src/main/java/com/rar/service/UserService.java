package com.rar.service;

import com.rar.DTO.EditUserDetails;
import com.rar.entity.UserInfo;
import org.springframework.http.ResponseEntity;

public interface UserService {

    UserInfo save(UserInfo userInfo);


    public ResponseEntity userSave(EditUserDetails editUserDetails);

    public EditUserDetails listById(long id);

    EditUserDetails update(long id, EditUserDetails editUserDetails);
}
