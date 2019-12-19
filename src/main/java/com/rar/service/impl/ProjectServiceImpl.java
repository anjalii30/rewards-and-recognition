package com.rar.service.impl;

import com.rar.exception.InvalidProjectException;
import com.rar.DTO.CreateProjectPojo;
import com.rar.model.Projects;
import com.rar.DTO.UserProjectsPojo;
import com.rar.model.UserInfo;
import com.rar.repository.ManagerRepository;
import com.rar.repository.ProjectRepository;
import com.rar.repository.UserRepository;
import com.rar.service.LoginService;
import com.rar.service.ProjectService;
import io.swagger.annotations.ApiModel;
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

    @Autowired
    private UserRepository userRepository;

    @Override
    public Projects projectSave(Projects projects) {
        return projectRepository.save(projects);
    }

    @Override
    public ResponseEntity assign(UserProjectsPojo userProjectsPojo) throws Exception {

            String[] employees = userProjectsPojo.getUserEmail();
             Long project_id = userProjectsPojo.getProjectId();

            for(int i=0; i<employees.length;i++) {



                String user_name=employees[i];

                Long user_id = loginService.getIdByName(user_name);

                projectRepository.assign(user_id, project_id);

       }
            return new ResponseEntity(projectService.findById(project_id), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CreateProjectPojo> createProject(CreateProjectPojo createProjectPojo) {

        String manager = createProjectPojo.getManagerEmail();
        Projects data = new Projects();
        data.setProjectName(createProjectPojo.getProjectName());
        projectRepository.save(data);

        long project_id = projectRepository.getIdByName(createProjectPojo.getProjectName());

        String[] employees = createProjectPojo.getUserEmail();

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

            return new ResponseEntity(data,HttpStatus.OK);
    }

    @Override
    public Long getIdByProject(String project_name) throws Exception {

        return projectRepository.getIdByName(project_name);
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
    public ResponseEntity<UserInfo[]> findById(Long project_id) {

            return new ResponseEntity(projectRepository.getUsersById(project_id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object[]> findManagerById(Long project_id) {
       Long[] manager_id = projectRepository.getManagerId(project_id);
       List list=new ArrayList();
       for(int i=0; i<manager_id.length;i++){
           list.add(projectRepository.getManagerDetails(manager_id[i]));
       }

       return new ResponseEntity(list,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserInfo[]>findNotInId(Long project_id) {

        return new ResponseEntity(projectRepository.findNotInId(project_id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity findAllData() {
         List<Projects> projects=projectRepository.findAllData();
         List parent=new ArrayList();

        for (Projects project : projects) {

            Map map = new HashMap();

            map.put("project_id", project.getProjectId());
            map.put("project_name", project.getProjectName());
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
    public List<Projects> findProjects(Long manager_id,Long reward_id) {
        return projectRepository.findProject(manager_id,reward_id);
    }

    @Override
    public Long getCount() {

        return null;
    }

}