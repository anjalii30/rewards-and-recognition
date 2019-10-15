package com.rar.controller;


import com.rar.model.Criteria;
import com.rar.service.CriteriaService;
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
@Api(value="Criteria Management System")
public class CriteriaController {

    @Autowired
    private CriteriaService criteriaService;

    @Autowired
    private CheckValidity validity;


    @ApiOperation(value = "save the criterion")
    @PostMapping("/saveCriteria")
    public Criteria save(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Criteria object store in database table", required = true) @Valid @RequestBody Criteria criteria){
        String email=validity.check(token);
        return criteriaService.saveCriteria(criteria);
    }

    @ApiOperation(value = "Get list of criterion")
    @GetMapping("/listCriteria")
    public List<Criteria> list(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return criteriaService.findAll();
    }

    @ApiOperation(value = "Delete criteria by id")
    @DeleteMapping("/deleteCriteria/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Criteria Id to delete criteria object", required = true) @PathVariable long id){
        String email=validity.check(token);
        criteriaService.deleteById(id);
        return "Deleted Successfully";
    }

    @ApiOperation(value = "Get criteria list by id")
    @GetMapping("/listCriterion/{id}")
    public Optional<Criteria> getById(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Criteria Id to get criteria object", required = true) @PathVariable Long id){

        String email=validity.check(token);
        return criteriaService.findById(id);
    }

}

