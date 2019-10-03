package com.rar.repository;

import com.rar.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Query(value="update  user_projects  set project_id=?2 where user_id=?1)",nativeQuery = true)
    void updateAssign(Long user_id, Long project_id);
}
