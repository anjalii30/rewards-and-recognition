package com.rar.controller;

import com.rar.model.Awarded;
import com.rar.repository.AwardedRepository;
import com.rar.service.AwardedService;
import com.rar.utils.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@EnableAutoConfiguration
@RequestMapping
@Api(value=" Awarded data management system")
public class AwardedController {
    @Autowired
    private AwardedService awardedService;

    @Autowired
    private AwardedRepository awardedRepository;

    @Autowired
    private CheckValidity validity;



    @ApiOperation(value = "save the data for awarded persons")
    @PostMapping("/awardedSave")
    public Awarded save(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Employee object store in database table", required = true)@Valid @RequestBody Awarded awarded){
        String email=validity.check(token);
        return awardedService.save(awarded);

    }

    @ApiOperation(value = "update the awarded data")
    @PutMapping("/awardedUpdate/{id}")
    public Awarded Update(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Awarded Id to update awarded object", required = true)@PathVariable Long id,
                          @ApiParam(value = "Update awarded object", required = true)@Valid @RequestBody Awarded awarded){
        String email=validity.check(token);
        return awardedService.Update(id, awarded);
    }

    @ApiOperation(value = "show the awarded list")
    @GetMapping("/awardedList")
    public List awardedList(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return  awardedService.findAllAwarded();
    }

    /*@ApiOperation(value = "show the awarded data by id")
    @GetMapping("/awardedList/{id}")
    public Optional<Awarded> getByAwardedId(@PathVariable Long id){
        return awardedRepository.findById(id);
    }*/
    /*@ApiOperation(value = "delete the awarded data by id")
    @DeleteMapping("/awardedDelete/{id}")
    public String  deleteAwarded(@PathVariable Long id){
        awardedRepository.deleteById(id);
        return "Deleted Successfully id="+id;
    }*/

    /*@ApiOperation(value = "showing employee image,reward name, description for employee home page ")
    @GetMapping("/employeehomepage")
    public Object ehomepage(){
        Object awarded= awardedService.ehomepage();
        return awarded;
    }*/
}