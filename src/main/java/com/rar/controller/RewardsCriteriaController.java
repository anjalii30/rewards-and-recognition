package com.rar.controller;


import com.rar.model.RewardsCriteria;
import com.rar.service.RewardsCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RewardsCriteriaController {

    @Autowired
    private RewardsCriteriaService rewardsCriteriaService;


    @PostMapping("/saveRewardsCriteria")
    public RewardsCriteria save(@RequestHeader(value = "Authorization") String token, @RequestBody RewardsCriteria rewardsCriteria){
        return rewardsCriteriaService.save(rewardsCriteria);
    }

    @GetMapping("/listRewardsCriteria")
    public List<RewardsCriteria> list(@RequestHeader(value = "Authorization") String token){
        return rewardsCriteriaService.findAll();
    }
/*
    @DeleteMapping("/deleteRewardsCriteria/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
        rewardsCriteriaService.deleteById(id);
        return "Deleted Successfully";
    }

    @GetMapping("/listRewardsCriteria/{rewardId}")
    public Optional<RewardsCriteria> getById(@RequestHeader(value = "Authorization") String token, @PathVariable Long rewardId){

        return rewardsCriteriaService.findById(rewardId);
    }*/
}
