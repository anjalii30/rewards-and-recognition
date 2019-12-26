package com.rar.controller;

import com.rar.exception.RecordNotFoundException;
import com.rar.model.Manager;
import com.rar.repository.ManagerRepository;
import com.rar.repository.ProjectRepository;
import com.rar.service.ManagerService;
import com.rar.service.impl.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

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
     * @param token jwt token
     * @param manager Manager object
     * @return saved manager object.
     */
    @ApiOperation(value = "Save the  manager")
    @PostMapping("/saveManager")
    public ResponseEntity<Manager> save(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Manager object stored in database table", required = true) @Valid @RequestBody Manager manager){
            validity.check(token);
            return new ResponseEntity(managerService.save(manager),HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @return list of Managers.
     */
    @ApiOperation(value = "Get the list of managers")
    @GetMapping("/listManagers")
    public ResponseEntity<List<Manager>> list(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return new ResponseEntity(managerService.findAll(),HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @param id manager id
     * @return String that displays that manager has been successfully deleted.
     */
    @ApiOperation(value = "Delete the manager detail by id")
    @DeleteMapping("/deleteEmp/{id}")
    public ResponseEntity<String> delete(@RequestHeader(value = "Authorization") String token, @ApiParam(value = " Id to delete manager", required = true) @PathVariable long id){
         validity.check(token);
         if(managerRepository.existsById(id)) {
             managerService.deleteById(id);
             return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
         }
         else
             throw new RecordNotFoundException("manager id not found");
    }

    /**
     * @param token jwt token
     * @param id manager id
     * @return object of manager based on id.
     */
    @ApiOperation(value = "Get the manager by id")
    @GetMapping("/listEmp/{id}")
    public ResponseEntity<Manager> getById(@RequestHeader(value = "Authorization") String token, @ApiParam(value = " Id to get manager object", required = true) @PathVariable Long id) {
            validity.check(token);
        if(managerRepository.existsById(id))
            return new ResponseEntity(managerService.findById(id),HttpStatus.OK);
        else
            throw new RecordNotFoundException("manager id not found");
    }

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

    /**
     * @param token jwt token
     * @param manager_id manager id
     * @param project_id project id
     * @return a string "assigned"
     * @throws Exception project not found
     */
    @ApiOperation(value = "assign project to manager")
    @PostMapping("/assignManagerProject")
    public ResponseEntity<String> assignValues(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "manager id ", required = true) @Valid @RequestBody long manager_id, @Valid @RequestBody long project_id) throws Exception {
        validity.check(token);
           managerService.assignValues(manager_id, project_id);
           return new ResponseEntity<>("Assigned", HttpStatus.OK);
    }
}