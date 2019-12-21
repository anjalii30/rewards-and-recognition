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

    @Query(value = "select count(manager_id) from manager_projects where manager_id= (Select manager_id from managers where manager_email=?1)", nativeQuery = true)
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
    @Query(value="insert into user_designation (user_id,designation_id) values (:userId, :designationId)",nativeQuery = true)
    void insertUserDesignation( Long userId, Long designationId);

    @Modifying
    @Transactional
    @Query(value="insert into user_roles (user_id,role_id) values (:userId, :roleId)",nativeQuery = true)
    void insertUserRoles(Long userId, Integer roleId);

    @Modifying
    @Transactional
    @Query(value="insert into managers (manager_email) values (:managerEmail)",nativeQuery = true)
    void insertManager(String managerEmail);

    @Query(value="select manager_id from managers where manager_email=?1",nativeQuery = true)
    Long findManagerId(String email);
    @Query(value="select name from users where user_id=?1",nativeQuery = true)
    String getNameById(Long userId);

    @Query(value="select image_url from users where user_id=?1",nativeQuery = true)
    String getImage(Long userId);

    @Modifying
    @Transactional
    @Query(value="insert into manager_projects (manager_id,project_id) values (:managerId,:projectId)",nativeQuery = true)
    void insertManagerProjects(Long managerId,Long projectId);

    @Modifying
    @Transactional
    @Query(value="insert into user_projects (user_id,project_id) values (:userId,:projectId)",nativeQuery = true)
    void insertUserProjects(Long userId,Long projectId);

    @Query(value="select manager_id from manager_projects where project_id=?1",nativeQuery = true)
    List<Long> getManagerIdFromProjectId(Long projectId);

    @Modifying
    @Transactional
    @Query(value="insert into user_manager (user_id,manager_id) values (:userId,:managerId)",nativeQuery = true)
    void insertUserManager(Long userId,Long managerId);

    @Query(value="select * from users where user_id not in(select user_id from user_roles where role_id=2)",nativeQuery = true)
    List<UserInfo> getAll();

    @Query(value="select designation_id from user_designation where user_id=?1",nativeQuery = true)
    long findDesignationId(long userId);

    @Query(value = "select count(manager_id) from managers where manager_email=?1", nativeQuery = true)
    int isManager(String email);

    @Modifying
    @Transactional
    @Query(value="update user_designation set designation_id= ?2 where user_id=?1",nativeQuery = true)
    void updateDesignation(Long userId,Long designationId);

    @Modifying
    @Transactional
    @Query(value="delete from manager_projects where manager_id=?1",nativeQuery = true)
    void deleteManagerProjects(Long managerId);

    @Modifying
    @Transactional
    @Query(value="delete from user_projects where user_id=?1",nativeQuery = true)
    void deleteUserProjects(Long userId);

    @Modifying
    @Transactional
    @Query(value="delete from user_manager where user_id=?1",nativeQuery = true)
    void deleteUserManagers(Long userId);

    @Query(value="select email from users where user_id in(select user_id from user_roles where role_id=1)",nativeQuery = true)
    String[] getAllEmails();

    @Query(value = "select wallet from users where user_id=?1",nativeQuery = true)
    Long getWalletBalance(Long userId);

    @Modifying
    @Transactional
    @Query(value = "update users set wallet=?2 where user_id=?1",nativeQuery = true)
    void updateWalletBalance(Long wallet,Long userId);

    @Modifying
    @Transactional
    @Query(value = " update users set first_sign= false where user_id=?1",nativeQuery = true)
    void changeFirstSign(long id);

    @Query(value = "select email from users where user_id=?1",nativeQuery = true)
    String getUserEmail(long id);

    @Query(value = "select name from users where user_id=?1", nativeQuery = true)
    String getUserName(long userId);
}


