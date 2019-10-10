package com.rar.controller;


import com.rar.model.Criterias;
import com.rar.service.CriteriaService;
import com.rar.utils.CheckValidity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class CriteriaController {

    @Autowired
    private CriteriaService criteriaService;

    @Autowired
    private CheckValidity validity;


    @PostMapping("/saveCriteria")
    public Criterias save(@RequestHeader(value = "Authorization") String token, @RequestBody Criterias criterias){
        String email=validity.check(token);
        return criteriaService.saveCriteria(criterias);
    }

    @GetMapping("/listCriteria")
    public List<Criterias> list(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return criteriaService.findAll();
    }

    @DeleteMapping("/deleteCriteria/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
        String email=validity.check(token);
        criteriaService.deleteById(id);
        return "Deleted Successfully";
    }

    @GetMapping("/listCriterion/{id}")
    public Optional<Criterias> getById(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){

        String email=validity.check(token);
        return criteriaService.findById(id);
    }

}

