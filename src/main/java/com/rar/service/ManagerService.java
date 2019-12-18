package com.rar.service;

import com.rar.model.Manager;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ManagerService {

    ResponseEntity<Manager> findById(Long id);

    ResponseEntity<Manager> save(Manager manager);

    ResponseEntity<List<Manager>> findAll();

    void deleteById(long id);

    ResponseEntity<List<Map<String,String>>> getAllMembers(Long project_id);

    void assignValues(long manager_id,long project_id);
}
