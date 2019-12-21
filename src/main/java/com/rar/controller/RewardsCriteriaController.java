package com.rar.controller;

import com.rar.model.RewardsCriteria;
import com.rar.service.RewardsCriteriaService;
import com.rar.service.impl.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value="Criterion Assigned to Rewards")
public class RewardsCriteriaController {

    @Autowired
    private RewardsCriteriaService rewardsCriteriaService;

    @Autowired
    private CheckValidity validity;

    /**
     * @param token jwt token
     * @param rewardsCriteria RewardsCriteria object
     * @return object that contains the saved criteria.
     */
    @ApiOperation(value = "Assign criteria to reward")
    @PostMapping("/saveRewardsCriteria")
    public ResponseEntity<RewardsCriteria> save(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Reward Assigned to criteria", required = true) @Valid @RequestBody RewardsCriteria rewardsCriteria){

            validity.check(token);
            return new ResponseEntity(rewardsCriteriaService.save(rewardsCriteria), HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @return list of all the saved criteria
     */
    @ApiOperation(value = "Get the mapping of criterion with rewards")
    @GetMapping("/listRewardsCriteria")
    public ResponseEntity<List<RewardsCriteria>> list(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return new ResponseEntity(rewardsCriteriaService.findAll(),HttpStatus.OK);
    }
}
