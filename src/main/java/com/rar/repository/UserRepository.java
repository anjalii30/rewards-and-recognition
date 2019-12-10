package com.rar.repository;

import com.rar.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository< UserInfo,Long> {


    @Query(value = "DELETE from users where email = ?1", nativeQuery = true)
    void deleteByEmail(String email);

    @Query(value="select name from users where email=?1",nativeQuery = true)
    String getName(String email);

    @Query(value = "SELECT * from users where email = ?1", nativeQuery = true)
    Optional<UserInfo> findByEmail(String email);

    @Query(value = "select count(manager_email) from managers where manager_email=?1", nativeQuery = true)
    Integer managerOrEmployee(String email);

    @Query(value="select user_id from users where email=?1", nativeQuery = true)
     Long getIdByEmail(String email);

    @Query(value="select * from users",nativeQuery = true)
    List findAllUsers();

    @Query(value="select name from users where user_id=?1",nativeQuery = true)
    String getNameById(Long user_id);

    @Query(value="select image_url from users where user_id=?1",nativeQuery = true)
    String getImage(Long user_id);


}
