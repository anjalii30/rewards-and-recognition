package com.rar.service;

import com.rar.model.Projects;
import com.rar.model.UserInfo;
import com.rar.model.UserProjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProjectService {

    Projects projectSave(Projects projects);


    void assign(UserProjects userProjects) throws Exception;

    Long getIdByProject(String project_name) throws Exception;

    void deleteUserFromproject(UserProjects userProjects);

    List findById(Long project_id);

    List findNotInId(Long project_id);

    List<Map<String,Object>> findAllData();
}
