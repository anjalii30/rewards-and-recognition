package com.rar.service;

import com.rar.entity.Roles;
import java.util.List;
import java.util.Optional;

public interface RolesService {

    Optional<Roles> findById(Long id);

    Roles save(Roles roles);

    List<Roles> findAll();

    void deleteById(long id);
}
