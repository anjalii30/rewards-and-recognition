package com.rar.controller;

import com.rar.config.CheckValidity;
import com.rar.exception.RecordNotFoundException;
import com.rar.repository.ManagerRepository;
import com.rar.repository.ProjectRepository;
import com.rar.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value="Managers Assigned to Employees")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private CheckValidity validity;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    /**
     *
     * @param token jwt token
     * @param id project_id
     * @return list of employee id and names under this manager
     */
    @ApiOperation(value = "Get the members working under this project")
    @GetMapping("/getMembers/{id}")
    public Object getMembers(@RequestHeader(value = "Authorization") String token, @ApiParam(value = " Id to get project object", required = true) @PathVariable Long id, HttpServletResponse response){
                  validity.check(token);
        if(projectRepository.existsById(id)) {
            return new ResponseEntity(managerService.getAllMembers(id), HttpStatus.OK);
        }else
            throw new RecordNotFoundException("project id not found");
    }


}