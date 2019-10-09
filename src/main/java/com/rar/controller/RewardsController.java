package com.rar.controller;

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
    public Rewards updateAwardStatus(@RequestHeader(value = "Authorization") String token,@PathVariable Long id, @RequestBody Rewards createReward){
        String email=validity.check(token);
        return rewardsService.updateAwardStatus(id, createReward);
    }

    @PutMapping("/discontinuing/{id}")
    public Rewards discontinuing(@RequestHeader(value = "Authorization") String token,@PathVariable Long id, @RequestBody Rewards createReward){
        String email=validity.check(token);
        return rewardsService.discontinuing(id, createReward);
    }

    @GetMapping("/listRewards")
    public List<Rewards> list(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return rewardsService.findAll();
    }

    @GetMapping("/listRewards/{id}")
    public Optional<Rewards> getById(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        String email=validity.check(token);
        return rewardsService.findById(id);
    }
/*
    @GetMapping("/listCriteria/{id}")
    public List<Criterias> getCriteria(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        String email=validity.check(token);
        return rewardsService.getCriteria(id);
    }*/


    @DeleteMapping("/deleteRewards/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
        String email=validity.check(token);
        rewardsService.deleteById(id);
        return "Deleted Successfully";
    }
}
