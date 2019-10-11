package com.rar.service;

import com.rar.model.Projects;
import com.rar.model.UserProjects;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProjectService {

    Projects projectSave(Projects projects);


    void assign(long[] uId,long pId) throws Exception;

    Long getIdByProject(String project_name) throws Exception;

    void deleteUserFromProject(UserProjects userProjects);

   Object[] findById(Long project_id);

    Object[] findNotInId(Long project_id);

    List<Map<String,Object>> findAllData();

    Object[] unAssigned();
}
