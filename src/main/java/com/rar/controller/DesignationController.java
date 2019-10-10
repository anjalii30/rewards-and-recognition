package com.rar.controller;


import com.rar.model.Designation;
import com.rar.service.DesignationService;
import com.rar.utils.CheckValidity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class DesignationController {


    @Autowired
    private DesignationService designationService;

    @Autowired
    private CheckValidity validity;

    @PostMapping("/saveDesignation")
    public Designation save(@RequestHeader(value = "Authorization") String token, @RequestBody Designation designation){
        String email=validity.check(token);
        return designationService.save(designation);
    }

    @GetMapping("/listDesignation")
    public List<Designation> list(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return designationService.findAll();
    }


    @DeleteMapping("/deleteDesignation/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
        String email=validity.check(token);
        designationService.deleteById(id);
        return "Deleted Successfully";
    }

    @GetMapping("/listDesignation/{id}")
    public Optional<Designation> getById(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        String email=validity.check(token);

        return designationService.findById(id);
    }
}
