package com.rar.service.impl;

import com.rar.DTO.CreateProjectPojo;
import com.rar.DTO.ManagerProjectsPojo;
import com.rar.DTO.UserProjectsPojo;
import com.rar.model.Projects;
import com.rar.model.UserInfo;
import com.rar.repository.ManagerRepository;
import com.rar.repository.ProjectRepository;
import com.rar.repository.UserRepository;
import com.rar.service.LoginService;
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

    @Override
    public Projects projectSave(Projects projects) {
        return projectRepository.save(projects);
    }

    @Override
    public ResponseEntity assign(UserProjectsPojo userProjectsPojo) throws Exception {

            String[] employees = userProjectsPojo.getUserEmail();
             Long projectId = userProjectsPojo.getProjectId();

            for(int i=0; i<employees.length;i++) {

                String userName=employees[i];
                Long userId = loginService.getIdByName(userName);
                projectRepository.assign(userId, projectId);
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

      //  try {
            String[] employees = userProjectsPojo.getUserEmail();
            System.out.println(employees+"emails");

            for (int i = 0; i < employees.length; i++) {

                Long userId = userRepository.getIdByEmail(employees[i]);
                System.out.println(userId+"userid");

                Long projectId = userProjectsPojo.getProjectId();
                System.out.println(projectId+"projectid");

                projectRepository.deleteUser(userId, projectId);
                System.out.println("deleted");
            }
      /*  } catch (Exception e) {

            throw new InvalidProjectException("Either employee or project is invalid...!!");

        }*/

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
            int temp=0;
            String managerEmail = managerProjectsPojo.getManagerEmail();
            System.out.println("line 159"+projectId+"line 159"+managerEmail);
            String currentWorkingEmployees[] =projectRepository.getEmployeesById(projectId);
                for(int i=0; i<currentWorkingEmployees.length;i++) {
                    if (managerEmail.equals(currentWorkingEmployees[i])) {
                        Long userId = userRepository.getUserId(currentWorkingEmployees[i]);
                        projectRepository.deleteUser(userId, projectId);
                        projectService.addManager(currentWorkingEmployees[i], projectId);
                        temp = 1;
                        break;
                    }
                }
                    if(temp==0) {
                             projectService.addManager(managerEmail,projectId);
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
    public ResponseEntity findAllData() {
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
    public Long getCount() {

        return null;
    }

}