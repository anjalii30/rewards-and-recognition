package com.rar.controller;

import com.rar.config.CheckValidity;
import com.rar.model.Criteria;
import com.rar.repository.CriteriaRepository;
import com.rar.service.CriteriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value="Criteria Management System")
public class CriteriaController {

    @Autowired
    private CriteriaService criteriaService;

    @Autowired
    private CheckValidity validity;

    @Autowired
    private CriteriaRepository criteriaRepository;

    /**
     * @param token    jwt token
     * @param criteria Criteria object
     * @return saved criteria.
     */
    @ApiOperation(value = "save the criterion")
    @PostMapping("/saveCriteria")
    public ResponseEntity<Criteria> save(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Criteria object store in database table", required = true) @Valid @RequestBody Criteria criteria) {
            validity.check(token);
            return new ResponseEntity(criteriaService.saveCriteria(criteria), HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @return list of criteria.
     */
    @ApiOperation(value = "Get list of criterion")
    @GetMapping("/listCriteria")
    public ResponseEntity<List<Criteria>> list(@RequestHeader(value = "Authorization") String token) {
        validity.check(token);
        return new ResponseEntity(criteriaService.findAll(), HttpStatus.OK);
    }

}