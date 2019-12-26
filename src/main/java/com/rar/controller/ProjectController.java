package com.rar.controller;

import com.rar.dto.CreateProjectPojo;
import com.rar.dto.ManagerProjectsPojo;
import com.rar.dto.UserProjectsPojo;
import com.rar.exception.RecordNotFoundException;
import com.rar.model.Projects;
import com.rar.model.UserInfo;
import com.rar.repository.ManagerRepository;
import com.rar.repository.ProjectRepository;
import com.rar.repository.RewardsRepository;
import com.rar.service.ProjectService;
import com.rar.config.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.rar.utils.Constants.PROJECT_ID_NOT_FOUND;


@RestController
@Api(value="Project Assigning System")
public class ProjectController {

    @Autowired
    private CheckValidity validity;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RewardsRepository rewardsRepository;



    /**
     * @param token jwt token
     * @param projects object
     * @return saved object of projects
     */
    @ApiOperation(value = "Save the new project")
    @PostMapping("/projectSave")
    public ResponseEntity<Projects> save(@RequestHeader(value = "Authorization") String token ,@ApiParam(value = "Project object store in database table", required = true) @Valid @RequestBody Projects projects){
            validity.check(token);
           return new ResponseEntity(projectService.projectSave(projects),HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @return list of projects
     */
    @ApiOperation(value = "Get the list of all projects")
    @GetMapping(value = "/listProjects")
    public ResponseEntity<Projects> projects(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return new ResponseEntity(projectService.findAllData(),HttpStatus.OK);
    }

    /**
     *
     * @param token jwt token
     * @param id reward id
     * @return list of projects assigned to this manager
     */
    @ApiOperation(value = "Get the list of projects that are assigned to this manager for which manager has not nominated anyone")
    @GetMapping(value = "/myProjects/{id}")
    public ResponseEntity<List<Projects>> projectsOfManager(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to show projects not been nominated for that reward", required = true)@PathVariable Long id){
        String email=validity.check(token);
        Long managerId=managerRepository.findByEmail(email);
        if(rewardsRepository.existsById(id)) {
            return new ResponseEntity(projectService.findProjects(managerId, id), HttpStatus.OK);
        }
        else
            throw new RecordNotFoundException("reward id not found");
    }

    /**
     * @param token jwt token
     * @param id project id
     * @return list of assigned users based on project_name.
     * @throws Exception no project found
     */
    @ApiOperation(value = "Get users assigned to some project")
    @GetMapping("/listAssignedUsers/{id}")
    public ResponseEntity<UserInfo[]> usersForProject(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Project Id to get employees under project", required = true) @PathVariable Long id){
            validity.check(token);
            if(projectRepository.existsById(id)) {
                return new ResponseEntity(projectService.findById(id), HttpStatus.OK);
            }
            else
                throw new RecordNotFoundException(PROJECT_ID_NOT_FOUND);

    }

    /**
     *
     * @param token jwt token
     * @param id project id
     * @return assigned manager for a particular project
     */
    @ApiOperation(value = "Get managers assigned to the project")
    @GetMapping("/assignedManager/{id}")
    public ResponseEntity<UserInfo[]> managerForProject(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Project Id to get managers of the project", required = true) @PathVariable Long id) {
               validity.check(token);
               if(projectRepository.existsById(id)) {
                   return new ResponseEntity(projectService.findManagerById(id), HttpStatus.OK);
               }
               else
                   throw new RecordNotFoundException(PROJECT_ID_NOT_FOUND);

    }

    /**
     * @param token jwt token
     * @param id project id
     * @return list of users not assigned to the project based on project_name.
     * @throws Exception no project found
     */
    @ApiOperation(value = "Get users not assigned to the project")
    @GetMapping("/listNotAssigned/{id}")
    public  ResponseEntity<UserInfo[]>  usersNotInProject(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Project Id to get managers of the project", required = true) @PathVariable Long id) {
        validity.check(token);
        if(projectRepository.existsById(id)) {
            return new ResponseEntity(projectService.findNotInId(id), HttpStatus.OK);
        }
        else
            throw new RecordNotFoundException(PROJECT_ID_NOT_FOUND);

    }

    /**
     *
     * @param token jwt token
     * @param managerProjectsPojo manager project pojo object
     * @return details of added manager into project
     * @throws Exception
     */
    @ApiOperation(value = "to add manager for project")
    @PostMapping("/addManagerForProject")
    public ResponseEntity<UserInfo []> editManagerForProject(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Project name and manager email",required = true)@Valid @RequestBody ManagerProjectsPojo managerProjectsPojo){
            validity.check(token);
            projectService.editManagerForProject(managerProjectsPojo);
            Long projectId = managerProjectsPojo.getProjectId();
            return new ResponseEntity(projectService.findManagerById(projectId), HttpStatus.OK);
    }

    /**
     *
     * @param token jwt token
     * @param managerProjectsPojo manager project pojo object
     * @return The details of manager for particular project
     * @throws Exception
     */
    @ApiOperation(value = "to delete a manager from project")
    @DeleteMapping("/deleteManagerFromProject")
    public ResponseEntity<UserInfo []> deleteManagerFromProject(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Project name and manager email",required = true)@Valid @RequestBody ManagerProjectsPojo managerProjectsPojo)  {
            validity.check(token);
            Long projectId = managerProjectsPojo.getProjectId();
            projectService.deleteManagerFromProject(managerProjectsPojo);
            return new ResponseEntity(projectService.findManagerById(projectId), HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @param userProjectsPojo object
     * @throws Exception no project found
     */
    @ApiOperation(value = "Assign project to users")
    @PostMapping("/assignProjects")
    public ResponseEntity<UserInfo []> assignProjects(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Project name and employee emails ", required = true) @Valid @RequestBody UserProjectsPojo userProjectsPojo) throws Exception {
            validity.check(token);
            projectService.assign(userProjectsPojo);
            Long projectId = userProjectsPojo.getProjectId();
            return new ResponseEntity(projectService.findById(projectId), HttpStatus.OK);
    }

    /**
     *
     * @param token jwt token
     * @param createProjectPojo createProjectPojo object
     */
    @ApiOperation(value = "Create a project")
    @PostMapping("/createProject")
    public ResponseEntity<CreateProjectPojo>createProject(@RequestHeader(value = "Authorization") String token,@RequestBody CreateProjectPojo createProjectPojo){
           validity.check(token);
           return new ResponseEntity(projectService.createProject(createProjectPojo),HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @param userProjectsPojo UserProjects object
     * @return list of users based on project_id.
     * @throws Exception no project found
     */
    @ApiOperation(value = "Delete user from  the project")
    @DeleteMapping("/deleteFromProject")
    public ResponseEntity<UserInfo[]> deleteUserFromProject(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Project name and employee emails ", required = true) @Valid @RequestBody UserProjectsPojo userProjectsPojo){
        validity.check(token);
        projectService.deleteUserFromProject(userProjectsPojo);
        Long projectId = userProjectsPojo.getProjectId();
        return new ResponseEntity(projectService.findById(projectId),HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @return list of unassigned users.
     */
    @ApiOperation(value = "Get the list of users not assigned to any project")
    @GetMapping("/unAssigned")
    public ResponseEntity<Object[]> unAssigned(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return new ResponseEntity(projectService.unAssigned(),HttpStatus.OK);
    }

    /**
     *
     * @param token jwt token
     * @param id project id
     * @return updated details of that particular project
     */
    @ApiOperation(value = "To set the project status as completed")
    @PutMapping("/setProjectStatus/{id}")
    public ResponseEntity<Projects[]> projectStatus(@RequestHeader(value = "Authorization")String token, @ApiParam(value = "Reward Id to update reward object", required = true)@PathVariable Long id){
        validity.check(token);
        projectService.setProjectStatus(id);
        return new ResponseEntity(projectService.projectDetails(id),HttpStatus.OK);
    }
}