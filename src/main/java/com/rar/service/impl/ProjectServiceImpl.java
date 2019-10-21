package com.rar.service.impl;

import com.rar.exception.InvalidProjectException;
import com.rar.model.Nominations;
import com.rar.model.Projects;
import com.rar.model.UserProjects;
import com.rar.repository.ProjectRepository;
import com.rar.service.LoginService;
import com.rar.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Projects projectSave(Projects projects) {
        return projectRepository.save(projects);
    }


    @Override
    public void assign(UserProjects userProjects) throws Exception {

//        try {

            String[] employees = userProjects.getUser_email();
            System.out.println(employees);

            for(int i=0; i<employees.length;i++) {

                Long project_id = projectService.getIdByProject(userProjects.getProject_name());
                System.out.println(project_id);

                String user_name=employees[i];

                Long user_id = loginService.getIdByName(user_name);

                System.out.println(user_id);


                projectRepository.assign(user_id, project_id);
//            }
//
//        } catch (Exception e) {
//
//            throw new InvalidProjectException("Either employee or project is invalid...!!");
//
       }


    }

    @Override
    public Long getIdByProject(String project_name) throws Exception {

        return projectRepository.getIdByName(project_name);
    }

    @Override
    public void deleteUserFromProject(UserProjects userProjects) {

        try {

            String[] employees = userProjects.getUser_email();

            for (int i = 0; i < employees.length; i++) {


                String user_name = employees[i];

                Long user_id = loginService.getIdByName(user_name);
                System.out.println(user_id);

                Long project_id = projectService.getIdByProject(userProjects.getProject_name());
                System.out.println(project_id);


                projectRepository.deleteUser(user_id, project_id);
            }
        } catch (Exception e) {

            throw new InvalidProjectException("Either employee or project is invalid...!!");

        }

    }

    @Override
    public Object[] findById(Long project_id) {

        if(project_id==4)
            return projectRepository.unAssignedUsers();
        else
            return projectRepository.getUsersById(project_id);

    }

    @Override
    public Object[] findNotInId(Long project_id) {

        /*if(project_id==4)
            return  null;
        else*/
        return (Object[]) projectRepository.findNotInId(project_id);
    }

    @Override
    public List<Projects> findAllData() {
        return projectRepository.findAllData();
    }

    @Override
    public Object[] unAssigned() {
        return projectRepository.unAssignedUsers();
    }


}