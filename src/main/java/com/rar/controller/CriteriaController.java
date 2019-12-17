package com.rar.controller;

import com.rar.model.Criteria;
import com.rar.service.CriteriaService;
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
import java.util.Optional;

@CrossOrigin
@RestController
@Api(value="Criteria Management System")
public class CriteriaController {

    @Autowired
    private CriteriaService criteriaService;

    @Autowired
    private CheckValidity validity;

    /**
     * @param token jwt token
     * @param criteria Criteria object
     * @return saved criteria.
     */

    @ApiOperation(value = "save the criterion")
    @PostMapping("/saveCriteria")
    public ResponseEntity<Criteria> save(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Criteria object store in database table", required = true) @Valid @RequestBody Criteria criteria){
       try {
           String email = validity.check(token);
           return new ResponseEntity(criteriaService.saveCriteria(criteria), HttpStatus.CREATED);
       }catch (Exception e){
           return  ResponseEntity.status(HttpStatus.CONFLICT).build();

       }
    }

    /**
     * @param token jwt token
     * @return list of criteria.
     */

    @ApiOperation(value = "Get list of criterion")
    @GetMapping("/listCriteria")
    public List<Criteria> list(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return criteriaService.findAll();
    }

    /**
     * @param token jwt token
     * @param id criteria id
     * @return String that displays that criteria is deleted successfully.
     */

    @ApiOperation(value = "Delete criteria by id")
    @DeleteMapping("/deleteCriteria/{id}")
    public ResponseEntity delete(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Criteria Id to delete criteria object", required = true) @PathVariable long id) {

        try {
            String email = validity.check(token);
            criteriaService.deleteById(id);
            return new ResponseEntity("Deleted",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
    /**
     * @param token jwt token
     * @param id criteria id
     * @return object of criteria based on id.
     */

    @ApiOperation(value = "Get criteria list by id")
    @GetMapping("/listCriterion/{id}")
    public Optional<Criteria> getById(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Criteria Id to get criteria object", required = true) @PathVariable Long id){

        String email=validity.check(token);
        return criteriaService.findById(id);
    }
}

