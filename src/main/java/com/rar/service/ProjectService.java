package com.rar.service;

import com.rar.model.Projects;
import com.rar.model.UserProjects;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {

    Projects projectSave(Projects projects);

    List<Projects> findAll();

    void assign(UserProjects userProjects);
}
