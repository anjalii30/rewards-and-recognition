package com.rar.service.impl;

import com.rar.exception.InvalidProjectException;
import com.rar.model.Projects;
import com.rar.model.UserInfo;
import com.rar.model.UserProjects;
import com.rar.repository.ProjectRepository;
import com.rar.service.LoginService;
import com.rar.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void assign(UserProjects userProjects)  {

        try {

            String[] employees = userProjects.getUser_email();

            for(int i=0; i<employees.length;i++) {

                Long project_id = projectService.getIdByProject(userProjects.getProject_name());

                String user_name=employees[i];

                Long user_id = loginService.getIdByName(user_name);


                projectRepository.assign(user_id, project_id);
            }

        } catch (Exception e) {

            throw new InvalidProjectException("Either employee or project is invalid...!!");

        }

    }

    @Override
    public Long getIdByProject(String project_name)  {

        return projectRepository.getIdByName(project_name);
    }

    @Override
    public void deleteUserFromproject(UserProjects userProjects) {

        try {

            String[] employees = userProjects.getUser_email();

            for(int i=0; i<employees.length;i++) {


                String user_name=employees[i];

                Long user_id = loginService.getIdByName(user_name);

                Long project_id = projectService.getIdByProject(userProjects.getProject_name());



                projectRepository.delete(user_id, project_id);
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
    public List findNotInId(Long project_id) {
        return projectRepository.findNotInId(project_id);
    }

    @Override
    public List<Map<String,Object>> findAllData() {
        return projectRepository.findAllData();
        //return null;
    }
}
