package com.rar.controller;

import com.rar.config.CheckValidity;
import com.rar.model.Designation;
import com.rar.repository.DesignationRepository;
import com.rar.service.DesignationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value="Designation Management System")
public class DesignationController {

    @Autowired
    private DesignationService designationService;

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private CheckValidity validity;


    /**
     * @param token jwt token
     * @return list of designations.
     */
    @ApiOperation(value = "Get the list of designations")
    @GetMapping("/listDesignation")
    public ResponseEntity<List<Designation>> list(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return new ResponseEntity(designationService.findAll(),HttpStatus.OK);
    }


}
