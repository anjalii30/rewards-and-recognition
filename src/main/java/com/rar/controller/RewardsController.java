package com.rar.controller;

import com.rar.model.Criterias;
import com.rar.model.Rewards;
import com.rar.service.RewardsService;
import com.rar.utils.CheckValidity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @Autowired
    private CheckValidity validity;


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestHeader(value = "Authorization") String token , @RequestBody Rewards rewards) throws Exception{
        String email=validity.check(token);
       return rewardsService.rewardsSave(rewards);
    }

    @PutMapping("/updateAwardStatus/{id}")
    public Rewards updateAwardStatus(@PathVariable Long id, @RequestBody Rewards createreward){
        return rewardsService.updateAwardStatus(id, createreward);
    }

    @PutMapping("/discontinuing/{id}")
    public Rewards discontinuing(@PathVariable Long id, @RequestBody Rewards createreward){
        return rewardsService.discontinuing(id, createreward);
    }

    @GetMapping("/listRewards")
    public List<Rewards> list(@RequestHeader(value = "Authorization") String token){
        return rewardsService.findAll();
    }

    @GetMapping("/listRewards/{id}")
    public Optional<Rewards> getById(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        return rewardsService.findById(id);
    }

    @GetMapping("/listCriteria/{id}")
    public List<Criterias> getCriteria(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        return rewardsService.getCriteria(id);
    }


    @DeleteMapping("/deleterewards/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
        rewardsService.deleteById(id);
        return "Deleted Successfully";
    }
}
