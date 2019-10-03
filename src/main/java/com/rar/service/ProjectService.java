package com.rar.service;

import com.rar.model.Projects;
import com.rar.model.UserProjects;
import java.util.List;

public interface ProjectService {

    Projects projectSave(Projects projects);

    List findAll();

    void assign(UserProjects userProjects) throws Exception;

    Long getIdByProject(String project_name) throws Exception;

    void updateAssign(UserProjects userProjects);
}
