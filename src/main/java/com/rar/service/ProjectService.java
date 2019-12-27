package com.rar.service;

import com.rar.dto.CreateProjectPojo;
import com.rar.dto.ManagerProjectsPojo;
import com.rar.dto.UserProjectsPojo;
import com.rar.model.Projects;
import com.rar.model.UserInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {


    ResponseEntity assign(UserProjectsPojo userProjectsPojo) ;

    ResponseEntity<CreateProjectPojo> createProject(CreateProjectPojo createProjectPojo);


    void deleteUserFromProject(UserProjectsPojo userProjectsPojo);

    void deleteManagerFromProject(ManagerProjectsPojo managerProjectsPojo);

    void editManagerForProject(ManagerProjectsPojo managerProjectsPojo);

    void addManager(String employeeEmail,Long projectId);

    ResponseEntity<UserInfo[]> findById(Long projectId);

    ResponseEntity<UserInfo[]>findManagerById(Long projectId);

    ResponseEntity<UserInfo[]> findNotInId(Long projectId);

    ResponseEntity<Projects>findAllData();

    ResponseEntity<Object[]> unAssigned();

    List<Projects> findProjects(Long managerId,Long rewardId);


    void setProjectStatus(Long id);

    ResponseEntity<Projects[]> projectDetails(Long id);
}