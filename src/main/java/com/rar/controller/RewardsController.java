package com.rar.controller;

import com.rar.DTO.RewardPojo;
import com.rar.exception.IncorrectFieldException;
import com.rar.exception.RecordNotFoundException;
import com.rar.model.Rewards;
import com.rar.repository.RewardsRepository;
import com.rar.repository.UserRepository;
import com.rar.service.RewardsService;
import com.rar.service.impl.CheckValidity;
import com.rar.service.impl.SendEmail;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@Api(value="Rewards Management System")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckValidity validity;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private RewardsRepository rewardsRepository;

    /**
     * @param token jwt token
     * @param rewards Rewards object
     * @return object of saved reward.
     */
    @ApiOperation(value = "Save the rewards")
    @PostMapping("/save")
    public ResponseEntity<Rewards> save(@RequestHeader(value = "Authorization") String token ,@ApiParam(value = "Reward object store in database table", required = true) @Valid @RequestBody Rewards rewards) throws IncorrectFieldException{
        validity.check(token);
        return new ResponseEntity(rewardsService.rewardsSave(rewards), HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @param id reward id
     * @param rewardPojo rewardPojo object
     * @return the object of reward for which the award status has been changed.
     */
    @ApiOperation(value = "Update award status by id")
    @PutMapping("/updateAwardStatus/{id}")
    public ResponseEntity<Rewards> updateAwardStatus(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Award status Id to update award status", required = true)@PathVariable Long id, @ApiParam(value = "Reward object ", required = true) @Valid @RequestBody RewardPojo rewardPojo) throws IOException, MessagingException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException, javax.mail.MessagingException {
        validity.check(token);
        if (rewardsRepository.existsById(id)) {
            ResponseEntity<Rewards> rewards = rewardsService.updateAwardStatus(id, rewardPojo);
            return new ResponseEntity(rewards, HttpStatus.OK);
        } else
            throw new RecordNotFoundException("reward id not found");
    }

    /**
     * @param token jwt token
     * @return list of rewards.
     */
    @ApiOperation(value = "Get the list of rewards")
    @GetMapping("/listRewards")
    public ResponseEntity<List<Rewards>> list(@RequestHeader(value = "Authorization") String token) {
        validity.check(token);
        return new ResponseEntity(rewardsService.findAll(),HttpStatus.OK);
    }

    /**
     * @param token jwt token* @param id reward id
     * @return list of rewards based on id.
     */
    @ApiOperation(value = "Get the reward details    by id")
    @GetMapping("/listRewards/{id}")
    public ResponseEntity<Rewards> getById(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to get reward object", required = true) @PathVariable Long id){
        validity.check(token);
        if(rewardsRepository.existsById(id))
            return new ResponseEntity(rewardsService.findById(id),HttpStatus.OK);
        else
            throw new RecordNotFoundException("reward id not found");
    }

    /**
     * @param token jwt token
     * @return list of latest rewards.
     */
    @ApiOperation(value = "Get the latest list of rewards")
    @GetMapping("/listLatestRewards")
    public ResponseEntity<List<Rewards>> latest(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return new ResponseEntity(rewardsService.latest(email),HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @return list of rewards which are rolled-out.
     */
    @ApiOperation(value = "Get the list of rolled out rewards")
    @GetMapping("/listRolledOut")
    public ResponseEntity<List<Rewards>> listRolledOut(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return new ResponseEntity(rewardsService.findByRolled(email),HttpStatus.OK);
    }

    //used for self-nomination
    /**
     * @param token jwt token
     * @return list of rewards for which the manager needs to approve.
     */
    @ApiOperation(value = "Get the list of self-nominated rewards for manager approval")
    @GetMapping("/managerApprovalRewards")
    public ResponseEntity<List<Rewards>> listSelfNominate(@RequestHeader(value = "Authorization") String token) {
        String email=validity.check(token);
        return new ResponseEntity(rewardsService.managerApprovalRewards(email),HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @param id reward id
     * @param rewards Reward object
     * @return object of reward after updating.
     */
    @ApiOperation(value = "Update the reward by id")
    @PutMapping("/updateReward/{id}")
    public ResponseEntity<Rewards> Update(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to update reward object", required = true)@PathVariable Long id, @ApiParam(value = "Reward object ", required = true) @Valid @RequestBody Rewards rewards){
        validity.check(token);
        if (rewardsRepository.existsById(id))
            return new ResponseEntity(rewardsService.Update(id, rewards), HttpStatus.OK);
        else
            throw new RecordNotFoundException("reward id not found");
    }

    /**
     * @param token jwt token
     * @param id reward id
     * @return object of reward based on id.
     */
    @ApiOperation(value = "Get the reward details for editing by reward id")
    @GetMapping("/listRolledOutRewardEdit/{id}")
    public ResponseEntity<Rewards> rollOutListReward(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Reward Id to get reward object", required = true)@PathVariable Long id){
        validity.check(token);
        if(rewardsRepository.existsById(id))
            return new ResponseEntity(rewardsService.rollOutListReward(id),HttpStatus.OK);
        else
            throw new RecordNotFoundException("reward id not found");
    }

    /**
     * @param token jwt token
     * @param id reward id
     * @param rewards Reward object
     * @return object of reward after updating.
     */
    @ApiOperation(value = "Update the reward by id")
    @PutMapping("/updateRollOutReward/{id}")
    public ResponseEntity<Rewards> RollOutUpdate(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to update reward object", required = true)@PathVariable Long id, @ApiParam(value = "Reward object ", required = true) @Valid @RequestBody Rewards rewards) {
        validity.check(token);
        if (rewardsRepository.existsById(id))
            return new ResponseEntity(rewardsService.rollOutUpdate(id, rewards), HttpStatus.OK);
        else
            throw new RecordNotFoundException("reward id not found");
    }
}