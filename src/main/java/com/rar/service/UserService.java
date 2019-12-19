package com.rar.service;

import com.rar.DTO.EditUserDetails;
import com.rar.model.UserInfo;
import org.springframework.http.ResponseEntity;

public interface UserService {

    UserInfo save(UserInfo userInfo);

    ResponseEntity userSave(EditUserDetails editUserDetails);

    EditUserDetails listById(long id);

    EditUserDetails update(long id, EditUserDetails editUserDetails);
}
