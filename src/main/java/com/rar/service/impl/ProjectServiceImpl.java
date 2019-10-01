package com.rar.service.impl;

import com.rar.exception.InvalidProjectException;
import com.rar.model.Projects;
import com.rar.model.UserProjects;
import com.rar.repository.ProjectRepository;
import com.rar.service.LoginService;
import com.rar.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Projects> findAll() {
        return (List<Projects>) projectRepository.findAll();
    }

    @Override
    public void assign(UserProjects userProjects)  {

        try {
            Long user_id = loginService.getIdByName(userProjects.getUser_email());

            Long project_id = projectService.getIdByProject(userProjects.getProject_name());


            projectRepository.assign(user_id, project_id);

        } catch (Exception e) {

            throw new InvalidProjectException("Either employee or project is invalid...!!");

        }

    }

    @Override
    public Long getIdByProject(String project_name)  {
        return projectRepository.getIdByName(project_name);
    }
}
