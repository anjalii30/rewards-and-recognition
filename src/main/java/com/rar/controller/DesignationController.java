package com.rar.controller;


import com.rar.model.Designation;
import com.rar.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class DesignationController {


    @Autowired
    private DesignationService designationService;

    @PostMapping("/savedesignation")
    public Designation save(@RequestHeader(value = "Authorization") String token, @RequestBody Designation designation){
        return designationService.save(designation);
    }

    @GetMapping("/listdesignation")
    public List<Designation> list(@RequestHeader(value = "Authorization") String token){
        return designationService.findAll();
    }


    @DeleteMapping("/deletedesignation/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
        designationService.deleteById(id);
        return "Deleted Successfully";
    }

    @GetMapping("/listdesignation/{id}")
    public Optional<Designation> getById(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){

        return designationService.findById(id);
    }
}
