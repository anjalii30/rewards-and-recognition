package com.rar.controller;

import com.rar.model.Manager;
import com.rar.repository.ManagerRepository;
import com.rar.service.ManagerService;
import com.rar.utils.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@Api(value="Managers Assigned to Employees")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private CheckValidity validity;

    @Autowired
    private ManagerRepository managerRepository;

    /**
     * @param token
     * @param manager
     * @return saved manager object.
     */
    @ApiOperation(value = "Save the  manager")
    @PostMapping("/saveEmp")
    public Manager save(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Manager object stored in database table", required = true) @Valid @RequestBody Manager manager){
        String email=validity.check(token);
        return managerService.save(manager);
    }

    /**
     * @param token
     * @return list of Managers.
     */
    @ApiOperation(value = "Get the list of managers")
    @GetMapping("/listManagers")
    public List<Manager> list(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return managerService.findAll();
    }

    /**
     * @param token
     * @param id
     * @return String that displays that manager has been successfully deleted.
     */
    @ApiOperation(value = "Delete the manager detail by id")
    @DeleteMapping("/deleteEmp/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token, @ApiParam(value = " Id to delete manager", required = true) @PathVariable long id){
        String email=validity.check(token);
        managerService.deleteById(id);
        return "Deleted Successfully";
    }

    /**
     * @param token
     * @param id
     * @return object of manager based on id.
     */
    @ApiOperation(value = "Get the manager by id")
    @GetMapping("/listEmp/{id}")
    public Optional<Manager> getById(@RequestHeader(value = "Authorization") String token, @ApiParam(value = " Id to get manager object", required = true) @PathVariable Long id){
        String email=validity.check(token);

        return managerService.findById(id);
    }

    @ApiOperation(value = "Get the list of employees under this manager")
    @GetMapping("/listEmp")
    public List listEmployees(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        Long manager_id=managerRepository.findByEmail(email);
        return managerService.getEmployees(manager_id);
    }

    @GetMapping("/getMembers")
    public List<Map<String,String>> getMembers(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        Long manager_id=managerRepository.findByEmail(email);
        return managerService.getAllMembers(manager_id);

    }

    @ApiOperation(value = "Get projects assigned to manager")
    @PostMapping("/listAssignedProjects")
    public String assignValues(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "manager id ", required = true) @Valid @RequestBody long manager_id, @ApiParam(value = "project id ", required = true) @Valid @RequestBody long project_id) throws Exception {
        String email=validity.check(token);
        managerService.assignValues(manager_id,project_id);
        return "Assigned";
    }
}
