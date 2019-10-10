package com.rar.controller;


import com.rar.model.Roles;
import com.rar.service.RolesService;
import com.rar.utils.CheckValidity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class RolesController {


    @Autowired
    private RolesService rolesService;

    @Autowired
    private CheckValidity validity;


    @PostMapping("/saveRoles")
    public Roles save(@RequestHeader(value = "Authorization") String token, @RequestBody Roles roles){
        String email=validity.check(token);
        return rolesService.save(roles);
    }

    @GetMapping("/listRoles")
    public List<Roles> list(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return rolesService.findAll();
    }

    @DeleteMapping("/deleteRoles/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
        String email=validity.check(token);
        rolesService.deleteById(id);
        return "Deleted Successfully";
    }

    @GetMapping("/listRoles/{id}")
    public Optional<Roles> getById(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        String email=validity.check(token);
        return rolesService.findById(id);
    }
}
