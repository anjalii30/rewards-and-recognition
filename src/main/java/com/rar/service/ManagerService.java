package com.rar.service;


import com.rar.model.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerService {

    Optional<Manager> findById(Long id);

    Manager save(Manager manager);

    List<Manager> findAll();

    void deleteById(long id);

}
