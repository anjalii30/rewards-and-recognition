package com.rar.service;

import com.rar.dto.EditUserDetails;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity userSave(EditUserDetails editUserDetails);

    ResponseEntity<EditUserDetails> listById(Long id);

    ResponseEntity<EditUserDetails> update(long id, EditUserDetails editUserDetails);

    ResponseEntity getCoinsDetails(String email);
}
