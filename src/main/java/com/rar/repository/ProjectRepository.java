package com.rar.repository;

import com.rar.model.Projects;
import com.rar.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface ProjectRepository  extends JpaRepository<Projects,Long> {

    @Modifying
    @Transactional
    @Query(value="insert into user_projects (user_id, project_id) values (:user_id, :project_id)",nativeQuery = true)
    void assign( Long user_id, Long project_id);

    @Query(value="select project_id from projects where project_name=?1", nativeQuery = true)
    Long getIdByName(String project_name);

    @Modifying
    @Transactional
    @Query(value="delete from user_projects where user_id=?1 and project_id=?2",nativeQuery = true)
    void deleteUser(Long user_id, Long project_id);

    @Query(value="select email,name from users where user_id in (select user_id from user_projects where project_id=?1)",nativeQuery = true)
    Object[] getUsersById(Long project_id);

    @Query(value="select email, name from users where user_id not in (select user_id from user_projects where project_id=?1)",nativeQuery = true)
    List findNotInId(Long project_id);

    @Query(value="select *  from projects",nativeQuery = true)
    List<Map<String,Object>> findAllData();
}
