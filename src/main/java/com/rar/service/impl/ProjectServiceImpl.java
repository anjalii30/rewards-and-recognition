package com.rar.service.impl;

import com.rar.dto.CreateProjectPojo;
import com.rar.dto.ManagerProjectsPojo;
import com.rar.dto.UserProjectsPojo;
import com.rar.exception.InvalidProjectException;
import com.rar.model.Projects;
import com.rar.model.UserInfo;
import com.rar.repository.ManagerRepository;
import com.rar.repository.ProjectRepository;
import com.rar.repository.UserRepository;
import com.rar.service.LoginService;
import com.rar.service.NotificationsService;
import com.rar.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationsService notificationsService;

    @Override
    public Projects projectSave(Projects projects) {
        return projectRepository.save(projects);
    }

    @Override
    public ResponseEntity assign(UserProjectsPojo userProjectsPojo)  {

            String[] employees = userProjectsPojo.getUserEmail();
             Long projectId = userProjectsPojo.getProjectId();

            for(int i=0; i<employees.length;i++) {
                String userName=employees[i];
                Long userId = loginService.getIdByName(userName);
                projectRepository.assign(userId, projectId);
                notificationsService.newMemberAdded(userId,projectId);
       }
            return new ResponseEntity(projectService.findById(projectId), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CreateProjectPojo> createProject(CreateProjectPojo createProjectPojo) {

        String manager = createProjectPojo.getManagerEmail();
        Projects data = new Projects();
        data.setProjectName(createProjectPojo.getProjectName());
        projectRepository.save(data);

        long projectId = projectRepository.getIdByName(createProjectPojo.getProjectName());

        String[] employees = createProjectPojo.getUserEmail();

        for (int i = 0; i < employees.length; i++) {

            String userName = employees[i];
            if(!userName.equals(manager)) {
                Long userId = loginService.getIdByName(userName);
                projectRepository.assign(userId, projectId);
            }
        }

           Long managerId = managerRepository.findByEmail(manager);

           if(managerId == null){
               managerRepository.managerInsert(manager);
               managerId =managerRepository.findByEmail(manager);
               managerRepository.assignValues(managerId,projectId);
           }
            else {
               managerRepository.assignValues(managerId, projectId);
           }

            return new ResponseEntity(data,HttpStatus.OK);
    }


    @Override
    public void addManager(String employeeEmail, Long projectId) {

        Long managerId = managerRepository.findByEmail(employeeEmail);
        if(managerId == null){
            managerRepository.managerInsert(employeeEmail);
            managerId= managerRepository.findByEmail(employeeEmail);
            managerRepository.assignValues(managerId,projectId);
        }
        else {
            managerRepository.assignValues(managerId,projectId);
        }
    }

    @Override
    public Long getIdByProject(String projectName) throws Exception {

        return projectRepository.getIdByName(projectName);
    }

    @Override
    public void deleteUserFromProject(UserProjectsPojo userProjectsPojo) {

            String[] employees = userProjectsPojo.getUserEmail();


        for (String employee : employees) {

            Long userId = userRepository.getIdByEmail(employee);

            Long projectId = userProjectsPojo.getProjectId();

            projectRepository.deleteUser(userId, projectId);

            notificationsService.MemberDeletedFromProject(userId, projectId);
        }
    }

    @Override
    public void deleteManagerFromProject(ManagerProjectsPojo managerProjectsPojo) {

            Long projectId = managerProjectsPojo.getProjectId();
            String managerEmail = managerProjectsPojo.getManagerEmail();
            Long managerId = managerRepository.findByEmail(managerEmail);
            projectRepository.deleteManager(managerId,projectId);
    }

    @Override
    public void editManagerForProject(ManagerProjectsPojo managerProjectsPojo) {
            Long projectId = managerProjectsPojo.getProjectId();
            if(projectRepository.getManagerCount(projectId)>0)
                throw new InvalidProjectException("Manager already exist for this project..");
            else {
                int temp = 0;
                String managerEmail = managerProjectsPojo.getManagerEmail();
                String[] currentWorkingEmployees = projectRepository.getEmployeesById(projectId);
                for (int i = 0; i < currentWorkingEmployees.length; i++) {
                    if (managerEmail.equals(currentWorkingEmployees[i])) {
                        Long userId = userRepository.getUserId(currentWorkingEmployees[i]);
                        projectRepository.deleteUser(userId, projectId);
                        projectService.addManager(currentWorkingEmployees[i], projectId);
                        temp = 1;
                        break;
                    }
                }
                if (temp == 0) {
                    projectService.addManager(managerEmail, projectId);
                }
            }
                }



    @Override
    public ResponseEntity<UserInfo[]> findById(Long projectId) {

            return new ResponseEntity(projectRepository.getUsersById(projectId),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserInfo[]> findManagerById(Long projectId) {
        Long managerId = projectRepository.getManagerId(projectId);
        return new ResponseEntity(projectRepository.getManagerDetails(managerId),HttpStatus.OK);
       }


    @Override
    public ResponseEntity<UserInfo[]>findNotInId(Long projectId) {

        return new ResponseEntity(projectRepository.findNotInId(projectId),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Projects> findAllData() {
         List<Projects> projects=projectRepository.findAllData();
         List parent=new ArrayList();

        for (Projects project : projects) {

            Map map = new HashMap();

            map.put("projectId", project.getProjectId());
            map.put("projectName", project.getProjectName());
            map.put("count", projectRepository.getCount(project.getProjectId()));

            parent.add(map);
        }


        return new ResponseEntity(parent,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object[]> unAssigned() {
        return new ResponseEntity<>(projectRepository.unAssignedUsers(),HttpStatus.OK);
    }

    @Override
    public List<Projects> findProjects(Long managerId,Long rewardId) {
        return projectRepository.findProject(managerId,rewardId);
    }

    @Override
    public ResponseEntity<Projects[]> projectDetails(Long id) {
        return new ResponseEntity(projectRepository.getProject(id),HttpStatus.OK);
    }

    @Override
    public void setProjectStatus(Long id) {
        projectRepository.setProjectStatus(id);
    }
}