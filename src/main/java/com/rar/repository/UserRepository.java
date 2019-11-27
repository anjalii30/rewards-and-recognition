package com.rar.repository;

import com.rar.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserInfo,Long> {


    @Query(value = "DELETE from users where email = ?1", nativeQuery = true)
    void deleteByEmail(String email);

    @Query(value="select name from users where email=?1",nativeQuery = true)
    String getName(String email);

    @Query(value = "SELECT * from users where email = ?1", nativeQuery = true)
    Optional<UserInfo> findByEmail(String email);

    @Query(value="select user_id from users where email=?1", nativeQuery = true)
     Long getIdByEmail(String email);

    @Query(value="select * from users",nativeQuery = true)
    List findAllUsers();


}
