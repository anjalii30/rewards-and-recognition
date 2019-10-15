package com.rar.controller;


import com.rar.model.Designation;
import com.rar.service.DesignationService;
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
@Api(value="Designation Management System")
public class DesignationController {


    @Autowired
    private DesignationService designationService;

    @Autowired
    private CheckValidity validity;

    @ApiOperation(value = "Save the designation")
    @PostMapping("/saveDesignation")
    public Designation save(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Designation object store in database table", required = true) @Valid @RequestBody Designation designation){
        String email=validity.check(token);
        return designationService.save(designation);
    }

    @ApiOperation(value = "Get the list of designations")
    @GetMapping("/listDesignation")
    public List<Designation> list(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return designationService.findAll();
    }


    @ApiOperation(value = "Delete the designation by id")
    @DeleteMapping("/deleteDesignation/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Designation Id to delete designation object", required = true) @PathVariable long id){
        String email=validity.check(token);
        designationService.deleteById(id);
        return "Deleted Successfully";
    }

    @ApiOperation(value = "Get the designation by id")
    @GetMapping("/listDesignation/{id}")
    public Optional<Designation> getById(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Designation Id to get designation object", required = true) @PathVariable Long id){
        String email=validity.check(token);

        return designationService.findById(id);
    }
}
