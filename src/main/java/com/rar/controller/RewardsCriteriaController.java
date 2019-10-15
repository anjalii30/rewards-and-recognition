package com.rar.controller;


import com.rar.model.RewardsCriteria;
import com.rar.service.RewardsCriteriaService;
import com.rar.utils.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@Api(value="Criterion Assigned to Rewards")
public class RewardsCriteriaController {

    @Autowired
    private RewardsCriteriaService rewardsCriteriaService;

    @Autowired
    private CheckValidity validity;

    @ApiOperation(value = "Assign criteria to reward")
    @PostMapping("/saveRewardsCriteria")
    public RewardsCriteria save(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Assigned to criteria", required = true) @Valid @RequestBody RewardsCriteria rewardsCriteria){

        String email=validity.check(token);
        return rewardsCriteriaService.save(rewardsCriteria);
    }

    @ApiOperation(value = "Get the mapping of criterion with rewards")
    @GetMapping("/listRewardsCriteria")
    public List<RewardsCriteria> list(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
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
