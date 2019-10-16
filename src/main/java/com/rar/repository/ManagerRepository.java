package com.rar.repository;

import com.rar.model.Manager;
import com.rar.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long> {


    @Query(value = "SELECT manager_id from managers where manager_email = ?1", nativeQuery = true)
    Long findByEmail(String email);
}
