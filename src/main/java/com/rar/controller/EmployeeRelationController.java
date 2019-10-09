package com.rar.controller;

import com.rar.model.EmployeeRelation;
import com.rar.service.EmployeeRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class EmployeeRelationController {

    @Autowired
    private EmployeeRelationService employeeRelationService;

    @PostMapping("/saveEmp")
    public EmployeeRelation save(@RequestHeader(value = "Authorization") String token,
                                 @RequestBody EmployeeRelation employeeRelation){
        return employeeRelationService.save(employeeRelation);
    }

    @GetMapping("/listEmp")
    public List<EmployeeRelation> list(@RequestHeader(value = "Authorization") String token){
        return employeeRelationService.findAll();
    }

    @DeleteMapping("/deleteEmp/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
        employeeRelationService.deleteById(id);
        return "Deleted Successfully";
    }

    @GetMapping("/listEmp/{id}")
    public Optional<EmployeeRelation> getById(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){

        return employeeRelationService.findById(id);
    }


}
