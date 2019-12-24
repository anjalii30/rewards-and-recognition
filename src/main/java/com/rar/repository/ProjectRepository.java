package com.rar.repository;

import com.rar.model.Projects;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ProjectRepository  extends CrudRepository<Projects,Long> {

    @Modifying
    @Transactional
    @Query(value="insert into user_projects (user_id, project_id) values (:userId, :projectId)",nativeQuery = true)
    void assign( Long userId, Long projectId);

    @Query(value="select project_id from projects where project_name=?1", nativeQuery = true)
    Long getIdByName(String projectName);

    @Query(value="select project_name from projects where project_id=?1",nativeQuery = true)
    String getProjectName(Long projectId);

    @Modifying
    @Transactional
    @Query(value="delete from user_projects where user_id=?1 and project_id=?2",nativeQuery = true)
    void deleteUser(Long userId, Long projectId);

    @Modifying
    @Transactional
    @Query(value="delete from manager_projects where manager_id=?1 and project_id=?2",nativeQuery = true)
    void deleteManager(Long managerId, Long projectId);

    @Query(value="select email,name,image_url from users where user_id in (select user_id from user_projects where project_id=?1) and user_id in(select user_id from user_roles where role_id=1) ",nativeQuery = true)
    Object[] getUsersById(Long projectId);

    @Query(value="select email from users where user_id in(select user_id from user_projects where project_id=?1)",nativeQuery = true)
    String[] getEmployeesById(Long projectId);

    @Query(value="select email,name from users where user_id in(select user_id from user_projects where project_id=?1)",nativeQuery = true)
    Object[] getEmployeeById(Long projectId);

    @Query(value ="select manager_id from manager_projects where project_id=?1",nativeQuery = true)
    Long getManagerId(Long projectId);

    @Query(value="select manager_email from managers where manager_id=?1",nativeQuery = true)
    String getManagerEmail(Long managerId);

    @Query(value = "select email,name,image_url from users where email in (select manager_email from managers where manager_id=?1)",nativeQuery = true)
    Object[] getManagerDetails(Long managerId);

    @Query(value="select user_id,email,name from users where user_id not in (select user_id from user_projects where project_id=?1) and user_id in(select user_id from user_roles where role_id=1) and email not in(select manager_email from managers where manager_id=(select manager_id from manager_projects where project_id=?1))",nativeQuery = true)
    Object[] findNotInId(Long projectId);

    @Query(value="select * from projects order by project_id desc",nativeQuery = true)
    List<Projects> findAllData();

    @Query(value="select email,name from users where user_id not in (select user_id from user_projects) and user_id in(select user_id from user_roles where role_id=1)",nativeQuery =true )
    Object[] unAssignedUsers();

    @Query(value = "select count(project_id) from manager_projects where manager_id= ?1 and project_id= ?2",nativeQuery =true )
    long managerProjectPresent(long managerId, long projectId);

    @Query(value = "select count(user_id) from user_projects where user_id=?1 and project_id= ?2",nativeQuery =true)
    int userProjectPresent(long userId, long projectId);

    @Modifying
    @Transactional
    @Query(value="insert into projects (project_name) values(:projectName)",nativeQuery = true)
    void saveProject(String projectName);

    @Query(value="select * from projects where project_id in(select project_id from manager_projects where manager_id=?1) and project_id not in (select project_id from nominations where reward_id=?2)",nativeQuery = true)
    List<Projects> findProject(Long managerId,Long rewardId);

    @Query(value="select count(user_id) from user_projects where project_id=?1",nativeQuery = true)
    Long getCount(Long projectId);


    @Query(value = "select * from projects where project_id=?1",nativeQuery = true)
    Projects[] getProject(Long projectId);

    @Modifying
    @Transactional
    @Query(value = "update projects set completed=true where project_id=?1",nativeQuery = true)
    void setProjectStatus(Long projectId);

    @Query(value="select count(manager_id) from manager_projects where project_id=?1",nativeQuery = true)
    Long getManagerCount(Long projectId);

    @Query(value="select user_projects.user_id, manager_projects.manager_id from user_projects,manager_projects where user_projects.project_id=?1 and manager_projects.project_id=?1",nativeQuery = true)
    Long[] getEveryoneInProject(Long projectId);

}
