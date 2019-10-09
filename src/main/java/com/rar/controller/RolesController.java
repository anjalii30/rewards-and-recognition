package com.rar.controller;


import com.rar.model.Roles;
import com.rar.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class RolesController {


    @Autowired
    private RolesService rolesService;

    @PostMapping("/saveRoles")
    public Roles save(@RequestHeader(value = "Authorization") String token, @RequestBody Roles roles){
        return rolesService.save(roles);
    }

    @GetMapping("/listRoles")
    public List<Roles> list(@RequestHeader(value = "Authorization") String token){
        return rolesService.findAll();
    }

    @DeleteMapping("/deleteRoles/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
        rolesService.deleteById(id);
        return "Deleted Successfully";
    }

    @GetMapping("/listRoles/{id}")
    public Optional<Roles> getById(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){

        return rolesService.findById(id);
    }
}
