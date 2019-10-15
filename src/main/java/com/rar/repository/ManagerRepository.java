package com.rar.repository;

import com.rar.model.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long> {
}
