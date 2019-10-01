package com.rar.service.impl;

import com.rar.model.Projects;
import com.rar.model.UserProjects;
import com.rar.repository.ProjectRepository;
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

    @Override
    public Projects projectSave(Projects projects) {
        return projectRepository.save(projects);
    }

    @Override
    public List<Projects> findAll() {
        return (List<Projects>) projectRepository.findAll();
    }

    @Override
    public void assign(UserProjects userProjects) {

        Long user_id= userProjects.getUser_id();
        Long project_id=userProjects.getProject_id();
         projectRepository.assign(user_id,project_id);

    }
}
