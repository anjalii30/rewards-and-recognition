package com.rar.service.impl;

import com.rar.exception.InvalidProjectException;
import com.rar.model.CreateProjectPojo;
import com.rar.model.Projects;
import com.rar.model.UserProjectsPojo;
import com.rar.repository.ManagerRepository;
import com.rar.repository.ProjectRepository;
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
//@Autowired Projects projects;
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Projects projectSave(Projects projects) {
        return projectRepository.save(projects);
    }

    @Override
    public ResponseEntity assign(UserProjectsPojo userProjectsPojo) throws Exception {

            String[] employees = userProjectsPojo.getUser_email();
             Long project_id = projectService.getIdByProject(userProjectsPojo.getProject_name());

            for(int i=0; i<employees.length;i++) {



                String user_name=employees[i];

                Long user_id = loginService.getIdByName(user_name);

                projectRepository.assign(user_id, project_id);

       }
            return new ResponseEntity(projectService.findById(project_id), HttpStatus.OK);

    }

    @Override
    public void createProject(CreateProjectPojo createProjectPojo) {

        String manager = createProjectPojo.getManager_email();
        Projects data = new Projects();
        data.setProject_name(createProjectPojo.getProject_name());
        projectRepository.save(data);

        long project_id = projectRepository.getIdByName(createProjectPojo.getProject_name());


        String[] employees = createProjectPojo.getUser_email();

        for (int i = 0; i < employees.length; i++) {

            String user_name = employees[i];
            if(!user_name.equals(manager)) {
                Long user_id = loginService.getIdByName(user_name);
                projectRepository.assign(user_id, project_id);
            }
        }

           Long manager_id = managerRepository.findByEmail(manager);

           if(manager_id == null){
               managerRepository.managerInsert(manager);
               manager_id =managerRepository.findByEmail(manager);
               managerRepository.assignValues(manager_id,project_id);
           }
            else {
               managerRepository.assignValues(manager_id, project_id);
           }

    }

    @Override
    public Long getIdByProject(String project_name) throws Exception {

        return projectRepository.getIdByName(project_name);
    }

    @Override
    public void deleteUserFromProject(UserProjectsPojo userProjectsPojo) {

        try {

            String[] employees = userProjectsPojo.getUser_email();

            for (int i = 0; i < employees.length; i++) {

                String user_name = employees[i];

                Long user_id = loginService.getIdByName(user_name);

                Long project_id = projectService.getIdByProject(userProjectsPojo.getProject_name());

                projectRepository.deleteUser(user_id, project_id);
            }
        } catch (Exception e) {

            throw new InvalidProjectException("Either employee or project is invalid...!!");

        }

    }

    @Override
    public Object[] findById(Long project_id) {

            return projectRepository.getUsersById(project_id);
    }

    @Override
    public Object[] findManagerById(Long project_id) {
       Long manager_id = projectRepository.getManagerId(project_id);
       String manager_email = projectRepository.getManagerEmail(manager_id);
       return  projectRepository.getManagerDetails(manager_email);
    }

    @Override
    public Object[] findNotInId(Long project_id) {

        return  projectRepository.findNotInId(project_id);
    }

    @Override
    public ResponseEntity findAllData() {
         List<Projects> projects=projectRepository.findAllData();
         List parent=new ArrayList();

        for (Projects project : projects) {

            Map map = new HashMap();

            map.put("project_id", project.getProject_id());
            map.put("project_name", project.getProject_name());
            map.put("count", projectRepository.getCount(project.getProject_id()));

            parent.add(map);
        }


        return new ResponseEntity(parent,HttpStatus.OK);
    }

    @Override
    public Object[] unAssigned() {
        return projectRepository.unAssignedUsers();
    }

    @Override
    public List<Projects> findProjects(Long manager_id,Long reward_id) {
        return projectRepository.findProject(manager_id,reward_id);
    }

    @Override
    public Long getCount() {

        return null;
    }

}