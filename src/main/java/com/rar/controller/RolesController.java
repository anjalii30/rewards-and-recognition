package com.rar.controller;


import com.rar.model.Roles;
import com.rar.service.RolesService;
import com.rar.utils.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@Api(value="Role Management System")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @Autowired
    private CheckValidity validity;

    /**
     * @param token
     * @param roles
     * @return object of the saved role.
     */
    @ApiOperation(value = "Save the roles")
    @PostMapping("/saveRoles")
    public Roles save(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Role object store in database table", required = true) @Valid @RequestBody Roles roles){
        String email=validity.check(token);
        return rolesService.save(roles);
    }

    /**
     * @param token
     * @return list of all the roles.
     */
    @ApiOperation(value = "Get the list of roles")
    @GetMapping("/listRoles")
    public List<Roles> list(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return rolesService.findAll();
    }

    /**
     * @param token
     * @param id
     * @return string that displays that role has been successfully displayed.
     */
    @ApiOperation(value = "Delete the role by id")
    @DeleteMapping("/deleteRoles/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Role Id to delete role object", required = true) @PathVariable long id){
        String email=validity.check(token);
        rolesService.deleteById(id);
        return "Deleted Successfully";
    }

    /**
     * @param token
     * @param id
     * @return object of role based on id.
     */
    @ApiOperation(value = "Get the role by id")
    @GetMapping("/listRole/{id}")
    public Optional<Roles> getById(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Role Id to get role object", required = true) @PathVariable Long id){
        String email=validity.check(token);
        return rolesService.findById(id);
    }
}
