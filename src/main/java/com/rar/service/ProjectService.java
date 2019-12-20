package com.rar.service;

import com.rar.DTO.CreateProjectPojo;
import com.rar.model.Projects;
import com.rar.DTO.UserProjectsPojo;
import com.rar.model.UserInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {

    Projects projectSave(Projects projects);

    ResponseEntity assign(UserProjectsPojo userProjectsPojo) throws Exception;

    ResponseEntity<CreateProjectPojo> createProject(CreateProjectPojo createProjectPojo);

    Long getIdByProject(String project_name) throws Exception;

    void deleteUserFromProject(UserProjectsPojo userProjectsPojo);

    ResponseEntity<UserInfo[]> findById(Long projectId);

    ResponseEntity<Object[]>findManagerById(Long projectId);

    ResponseEntity<UserInfo[]> findNotInId(Long project_id);

    ResponseEntity findAllData();

    ResponseEntity<Object[]> unAssigned();

    List<Projects> findProjects(Long manager_id,Long reward_id);

    Long getCount();
}