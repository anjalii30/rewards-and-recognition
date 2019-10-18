package com.rar.repository;

import com.rar.model.Manager;
import com.rar.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long> {


    @Query(value = "SELECT manager_id from managers where manager_email = ?1", nativeQuery = true)
    Long findByEmail(String email);

    @Query(value="select name,email,image_url from users where user_id in (select user_id from user_manager where manager_id=?1)",nativeQuery = true)
    List getEmployees(Long manager_id);

    @Query(value="select user_id,name from users where user_id in (select user_id from user_manager where manager_id=?1)",nativeQuery = true)
    List<Map<String,String>> getAllMembers(Long manager_id);
}
