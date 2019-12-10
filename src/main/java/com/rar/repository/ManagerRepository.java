package com.rar.repository;

import com.rar.model.Manager;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long> {

    @Query(value = "SELECT manager_id from managers where manager_email = ?1", nativeQuery = true)
    Long findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "insert into managers (manager_email) values(:manager_email)",nativeQuery = true)
    void managerInsert(String manager_email);

    @Query(value="select name,email,image_url from users where user_id in (select user_id from user_manager where manager_id=?1)",nativeQuery = true)
    List getEmployees(Long manager_id);

    @Query(value="select user_id,name from users where user_id in (select user_id from user_manager where manager_id=?1)",nativeQuery = true)
    List<Map<String,String>> getAllMembers(Long manager_id);

    @Query(value="select user_id from user_manager where manager_id=?1 ",nativeQuery = true)
    Long[] getMembers(Long manager_id);

    @Modifying
    @Transactional  
    @Query(value="insert into manager_projects values (:manager_id, :project_id)",nativeQuery = true)
    void assignValues(Long manager_id, Long project_id);


    @Query(value="select manager_email from managers",nativeQuery = true)
    String[] getAllEmails();

    @Query(value="select manager_id from managers",nativeQuery = true)
    Long[] getAllIds();

    @Query(value="select project_id from manager_projects where manager_id=?1",nativeQuery = true)
    Long[] getProjectsOfManager(Long manager_id);

@Query(value="select manager_email from managers where manager_id=?1",nativeQuery = true)
    String getEmail(Long manager);
}
