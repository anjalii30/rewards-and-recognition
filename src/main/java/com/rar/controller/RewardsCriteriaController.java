package com.rar.controller;


import com.rar.model.RewardsCriteria;
import com.rar.service.RewardsCriteriaService;
import com.rar.utils.CheckValidity;
import com.rar.utils.EmailNewReward;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@Api(value="Criterion Assigned to Rewards")
public class RewardsCriteriaController {

    @Autowired
    private RewardsCriteriaService rewardsCriteriaService;
@Autowired
    EmailNewReward emailNewReward;
    @Autowired
    private CheckValidity validity;

    /**
     * @param token jwt token
     * @param rewardsCriteria RewardsCriteria object
     * @return object that contains the saved criteria.
     */
    @ApiOperation(value = "Assign criteria to reward")
    @PostMapping("/saveRewardsCriteria")
    public RewardsCriteria save(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Assigned to criteria", required = true) @Valid @RequestBody RewardsCriteria rewardsCriteria){
        String email=validity.check(token);
        return rewardsCriteriaService.save(rewardsCriteria);
    }

    /**
     * @param token jwt token
     * @return list of all the saved criteria
     */
    @ApiOperation(value = "Get the mapping of criterion with rewards")
    @GetMapping("/listRewardsCriteria")
    public List<RewardsCriteria> list(@RequestHeader(value = "Authorization") String token) throws IOException, MessagingException {
        String email=validity.check(token);
        return rewardsCriteriaService.findAll();
    }

}
