package com.rar.service;


import com.rar.model.Manager;
import com.rar.model.UserInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ManagerService {

    Optional<Manager> findById(Long id);

    Manager save(Manager manager);

    List<Manager> findAll();

    void deleteById(long id);

    List getEmployees(Long manager_id);

    List<Map<String,String>> getAllMembers(Long manager_id);
}
