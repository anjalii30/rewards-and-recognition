package com.rar.controller;


import com.rar.model.RewardsCriterias;
import com.rar.service.RewardsCriteriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RewardsCriteriasController {

    @Autowired
    private RewardsCriteriasService rewardsCriteriasService;


    @PostMapping("/saveRewardsCriterias")
    public RewardsCriterias save(@RequestHeader(value = "Authorization") String token, @RequestBody RewardsCriterias rewardsCriterias){
        return rewardsCriteriasService.save(rewardsCriterias);
    }

    @GetMapping("/listRewardsCriterias")
    public List<RewardsCriterias> list(@RequestHeader(value = "Authorization") String token){
        return rewardsCriteriasService.findAll();
    }
/*
    @DeleteMapping("/deleteRewardsCriterias/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
        rewardsCriteriasService.deleteById(id);
        return "Deleted Successfully";
    }

    @GetMapping("/listRewardsCriterias/{rewardId}")
    public Optional<RewardsCriterias> getById(@RequestHeader(value = "Authorization") String token, @PathVariable Long rewardId){

        return rewardsCriteriasService.findById(rewardId);
    }*/
}
