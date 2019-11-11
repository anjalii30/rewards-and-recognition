package com.rar.controller;

import com.rar.model.Projects;
import com.rar.model.UserProjects;
import com.rar.service.ProjectService;
import com.rar.utils.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@Api(value="Project Assigning System")
public class ProjectController {

    @Autowired
    private CheckValidity validity;

    @Autowired
    private ProjectService projectService;

    @ApiOperation(value = "Save the new project")
    @PostMapping("/ProjectSave")
    public Projects save(@RequestHeader(value = "Authorization") String token ,@ApiParam(value = "Project object store in database table", required = true) @Valid @RequestBody Projects projects) throws Exception{
        String email=validity.check(token);
        return projectService.projectSave(projects);
    }

    @ApiOperation(value = "Get the list of projects")
    @GetMapping(value = "/listProjects")
    public List<Projects> projects(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return  projectService.findAllData();
    }

    @ApiOperation(value = "Get users assigned to some project")
    @PostMapping("/listAssignedUsers")
    public Object[] UsersForProject(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Project object ", required = true) @Valid @RequestBody Projects project_name) throws Exception {
        String email=validity.check(token);
        Long project_id = projectService.getIdByProject(project_name.getProject_name());
        return projectService.findById(project_id);
    }

    @ApiOperation(value = "Get users not assigned to the project")
    @PostMapping("/listNotAssigned")
    public Object[] UsersNotInProject(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Project object ", required = true) @Valid @RequestBody Projects project_name) throws Exception {
        String email = validity.check(token);
        Long project_id = projectService.getIdByProject(project_name.getProject_name());
        return  projectService.findNotInId(project_id);
    }

    @ApiOperation(value = "Assign project to users")
    @PostMapping("/assignProjects")
    public void assignProjects(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Project name and employee emails ", required = true) @Valid @RequestBody UserProjects userProjects) throws Exception {
        String email=validity.check(token);
        projectService.assign(userProjects);
    }

    @ApiOperation(value = "Delete user from  the project")
    @DeleteMapping("/deleteFromProject")
    public Object[] deleteUserFromProject(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Project name and employee emails ", required = true) @Valid @RequestBody UserProjects userProjects) throws Exception {

        String email=validity.check(token);
        projectService.deleteUserFromProject(userProjects);
        Long project_id = projectService.getIdByProject(userProjects.getProject_name());
        return projectService.findById(project_id);
    }

    @ApiOperation(value = "Get the list of users not assigned to any project")
    @GetMapping("/unAssigned")
    public Object[] unAssigned(@RequestHeader(value = "Authorization") String token){
        return projectService.unAssigned();
    }
}