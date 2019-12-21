package com.rar.service;

import com.rar.DTO.CreateProjectPojo;
import com.rar.DTO.ManagerProjectsPojo;
import com.rar.model.Projects;
import com.rar.DTO.UserProjectsPojo;
import com.rar.model.UserInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {

    Projects projectSave(Projects projects);

    ResponseEntity assign(UserProjectsPojo userProjectsPojo) throws Exception;

    ResponseEntity<CreateProjectPojo> createProject(CreateProjectPojo createProjectPojo);

    Long getIdByProject(String projectName) throws Exception;

    void deleteUserFromProject(UserProjectsPojo userProjectsPojo);

    void deleteManagerFromProject(ManagerProjectsPojo managerProjectsPojo);

    void editManagerForProject(ManagerProjectsPojo managerProjectsPojo);

    void addManager(String employeeEmail,Long projectId);

    ResponseEntity<UserInfo[]> findById(Long projectId);

    ResponseEntity<UserInfo[]>findManagerById(Long projectId);

    ResponseEntity<UserInfo[]> findNotInId(Long projectId);

    ResponseEntity findAllData();

    ResponseEntity<Object[]> unAssigned();

    List<Projects> findProjects(Long managerId,Long rewardId);

    Long getCount();
}