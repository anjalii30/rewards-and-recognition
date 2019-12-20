package com.rar.service;

import com.rar.DTO.EditUserDetails;
import com.rar.model.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity userSave(EditUserDetails editUserDetails);

    ResponseEntity<EditUserDetails> listById(long id);

    ResponseEntity<EditUserDetails> update(long id, EditUserDetails editUserDetails);

    ResponseEntity getCoinsDetails(String email);
}
