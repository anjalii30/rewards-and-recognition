package com.rar.controller;

import com.rar.DTO.CreateProjectPojo;
import com.rar.entity.Projects;
import com.rar.DTO.UserProjectsPojo;
import com.rar.repository.ManagerRepository;
import com.rar.service.ProjectService;
import com.rar.service.impl.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ManagerRepository managerRepository;

    /**
     * @param token jwt token
     * @param projects object
     * @return saved object of projects
     */
    @ApiOperation(value = "Save the new project")
    @PostMapping("/ProjectSave")
    public Projects save(@RequestHeader(value = "Authorization") String token ,@ApiParam(value = "Project object store in database table", required = true) @Valid @RequestBody Projects projects){
        validity.check(token);
        return projectService.projectSave(projects);
    }

    /**
     * @param token jwt token
     * @return list of projects
     */
    @ApiOperation(value = "Get the list of projects")
    @GetMapping(value = "/listProjects")
    public ResponseEntity<?> projects(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return (projectService.findAllData());
    }

    /**
     *
     * @param token jwt token
     * @param id reward id
     * @return list of projects assigned to this manager
     */
    @ApiOperation(value = "Get the list of projects that are assigned to this manager")
    @GetMapping(value = "/myProjects/{id}")
    public List<Projects> projectsOfManager(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to show projects not been nominated for that reward", required = true)@PathVariable Long id){
        String email=validity.check(token);
        Long manager_id=managerRepository.findByEmail(email);
        return  projectService.findProjects(manager_id,id);
    }

    /**
     * @param token jwt token
     * @param project_name project name
     * @return list of assigned users based on project_name.
     * @throws Exception no project found
     */
    @ApiOperation(value = "Get users assigned to some project")
    @PostMapping("/listAssignedUsers")
    public Object[] UsersForProject(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Project object ", required = true) @Valid @RequestBody Projects project_name) throws Exception {
        validity.check(token);
        Long project_id = projectService.getIdByProject(project_name.getProject_name());
        return projectService.findById(project_id);
    }

    /**
     *
     * @param token jwt token
     * @param project project
     * @return managers of this project id
     * @throws Exception
     */
    @ApiOperation(value = "Get users assigned to some project")
    @PostMapping("/assignedManager")
    public Object [] ManagerForProject(@RequestHeader(value = "Authorization") String token,@RequestBody Projects project) throws Exception {
        validity.check(token);
        Long project_id = projectService.getIdByProject(project.getProject_name());
        return projectService.findManagerById(project_id);
    }
    /**
     * @param token jwt token
     * @param project_name project name
     * @return list of users not assigned to the project based on project_name.
     * @throws Exception no project found
     */
    @ApiOperation(value = "Get users not assigned to the project")
    @PostMapping("/listNotAssigned")
    public Object[] UsersNotInProject(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Project object ", required = true) @Valid @RequestBody Projects project_name) throws Exception {
        validity.check(token);
        Long project_id = projectService.getIdByProject(project_name.getProject_name());
        return  projectService.findNotInId(project_id);
    }

    /**
     * @param token jwt token
     * @param userProjectsPojo object
     * @throws Exception no project found
     */
    @ApiOperation(value = "Assign project to users")
    @PostMapping("/assignProjects")
    public ResponseEntity<?> assignProjects(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Project name and employee emails ", required = true) @Valid @RequestBody UserProjectsPojo userProjectsPojo) throws Exception {
        validity.check(token);
        projectService.assign(userProjectsPojo);
        Long project_id = projectService.getIdByProject(userProjectsPojo.getProject_name());
        return new ResponseEntity(projectService.findById(project_id), HttpStatus.OK);
    }

    /**
     *
     * @param token jwt token
     * @param createProjectPojo createProjectPojo object
     */
    @ApiOperation(value = "Create a project")
    @PostMapping("/createProject")
    public void createProject(@RequestHeader(value = "Authorization") String token,@RequestBody CreateProjectPojo createProjectPojo){
        validity.check(token);
        projectService.createProject(createProjectPojo);
    }

    /**
     * @param token jwt token
     * @param userProjectsPojo UserProjects object
     * @return list of users based on project_id.
     * @throws Exception no project found
     */
    @ApiOperation(value = "Delete user from  the project")
    @DeleteMapping("/deleteFromProject")
    public Object[] deleteUserFromProject(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Project name and employee emails ", required = true) @Valid @RequestBody UserProjectsPojo userProjectsPojo) throws Exception {
        validity.check(token);
        projectService.deleteUserFromProject(userProjectsPojo);
        Long project_id = projectService.getIdByProject(userProjectsPojo.getProject_name());
        return projectService.findById(project_id);
    }

    /**
     * @param token jwt token
     * @return list of unassigned users.
     */
    @ApiOperation(value = "Get the list of users not assigned to any project")
    @GetMapping("/unAssigned")
    public Object[] unAssigned(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return projectService.unAssigned();
    }
}