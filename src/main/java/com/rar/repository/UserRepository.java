package com.rar.repository;

import com.rar.model.UserInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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


    @Modifying
    @Transactional
    @Query(value="insert into users (email,name) values (:email, :name)",nativeQuery = true)
    void insertUser( String email, String name);

    @Query(value="select user_id from users where email=?1",nativeQuery = true)
    Long getUserId(String email);

    @Modifying
    @Transactional
    @Query(value="insert into user_designation (user_id,designation_id) values (:user_id, :designation_id)",nativeQuery = true)
    void insertUserDesignation( Long user_id, Long designation_id);


    @Modifying
    @Transactional
    @Query(value="insert into user_roles (user_id,role_id) values (:user_id, :role_id)",nativeQuery = true)
    void insertUserRoles( Long user_id, Long role_id);


    @Modifying
    @Transactional
    @Query(value="insert into managers (manager_email) values (:manager_email)",nativeQuery = true)
    void insertManager(String manager_email);

    @Query(value="select manager_id from managers where manager_email=?1",nativeQuery = true)
    Long findManagerId(String email);
    @Query(value="select name from users where user_id=?1",nativeQuery = true)
    String getNameById(Long user_id);

    @Query(value="select image_url from users where user_id=?1",nativeQuery = true)
    String getImage(Long user_id);

    @Modifying
    @Transactional
    @Query(value="insert into manager_projects (manager_id,project_id) values (:manager_id,:project_id)",nativeQuery = true)
    void insertManagerProjects(Long manager_id,Long project_id);

    @Modifying
    @Transactional
    @Query(value="insert into user_projects (user_id,project_id) values (:user_id,:project_id)",nativeQuery = true)
    void insertUserProjects(Long user_id,Long project_id);

    @Query(value="select manager_id from manager_projects where project_id=?1",nativeQuery = true)
    Long getManagerIdFromProjectId(Long project_id);

    @Modifying
    @Transactional
    @Query(value="insert into user_manager (user_id,manager_id) values (:user_id,:manager_id)",nativeQuery = true)
    void insertUserManager(Long user_id,Long manager_id);


    @Query(value="select * from users",nativeQuery = true)
    List<UserInfo> getAll();

    @Query(value="select designation_id from user_designation where user_id=?1",nativeQuery = true)
    long findDesignationId(long user_id);

    @Query(value = "select count(manager_id) from managers where manager_email=?1", nativeQuery = true)
    int isManager(String email);

    @Modifying
    @Transactional
    @Query(value="update user_designation set designation_id= ?2 where user_id=?1",nativeQuery = true)
    void updateDesignation(Long user_id,Long designation_id);

    @Modifying
    @Transactional
    @Query(value="delete from manager_projects where manager_id=?1",nativeQuery = true)
    void deleteManagerProjects(Long manager_id);

    @Modifying
    @Transactional
    @Query(value="delete from user_projects where user_id=?1",nativeQuery = true)
    void deleteUserProjects(Long user_id);

    @Modifying
    @Transactional
    @Query(value="delete from user_manager where user_id=?1",nativeQuery = true)
    void deleteUserManagers(Long user_id);
}


