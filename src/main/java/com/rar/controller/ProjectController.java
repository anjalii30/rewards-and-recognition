package com.rar.controller;

import com.rar.model.Projects;
import com.rar.model.Rewards;
import com.rar.model.UserProjects;
import com.rar.service.LoginService;
import com.rar.service.ProjectService;
import com.rar.utils.CheckValidity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Projects> list(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return projectService.findAll();
    }

    @PostMapping("/assignProjects")
    public void assignProjects(@RequestHeader(value = "Authorization") String token, @RequestBody UserProjects userProjects) throws Exception{
        String email=validity.check(token);

        projectService.assign(userProjects);

    }
}
