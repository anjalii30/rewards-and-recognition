package com.rar.controller;

import com.rar.model.Projects;
import com.rar.model.UserInfo;
import com.rar.model.UserProjects;
import com.rar.service.ProjectService;
import com.rar.utils.CheckValidity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ProjectController {


    @Autowired
    private CheckValidity validity;

    @Autowired
    private ProjectService projectService;



    @PostMapping("/ProjectSave")
    public Projects save(@RequestHeader(value = "Authorization") String token , @RequestBody Projects projects) throws Exception{
        String email=validity.check(token);
        return projectService.projectSave(projects);
    }

    @GetMapping("/listProjects")
    public List<Map<String,Object>> list(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return  projectService.findAllData();
    }

    @PostMapping("/listAssignedUsers")
    public Object[] UsersForProject(@RequestHeader(value = "Authorization") String token,@RequestBody Projects project_name) throws Exception {
        String email=validity.check(token);
        Long project_id = projectService.getIdByProject(project_name.getProject_name());
        return projectService.findById(project_id);

    }

    @GetMapping("/listNotAssigned")
    public List UsersNotInProject(@RequestHeader(value = "Authorization") String token,@RequestBody Projects project_name) throws Exception {
        String email = validity.check(token);
        Long project_id = projectService.getIdByProject(project_name.getProject_name());
        return projectService.findNotInId(project_id);


    }

    @PostMapping("/assignProjects")
    public void assignProjects(@RequestHeader(value = "Authorization") String token, @RequestBody UserProjects userProjects) throws Exception {
        String email=validity.check(token);

        projectService.assign(userProjects);

    }
    @DeleteMapping("/deleteFromProject")
    public ResponseEntity deleteUserFromProject(@RequestHeader(value = "Authorization") String token, @RequestBody UserProjects userProjects) throws Exception{

        String email=validity.check(token);

        projectService.deleteUserFromproject(userProjects);

        return ResponseEntity.ok( "User deleted from " + userProjects.getProject_name());

    }
}
