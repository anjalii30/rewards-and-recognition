package com.rar.controller;

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
import java.util.Optional;

@RestController
@CrossOrigin
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
    public ResponseEntity save(@RequestHeader(value = "Authorization") String token ,@ApiParam(value = "Reward object store in database table", required = true) @Valid @RequestBody Rewards rewards){
           String email=validity.check(token);
           return new ResponseEntity<>(rewardsService.rewardsSave(rewards),HttpStatus.OK) ;

    }

    /**
     * @param token jwt token
     * @param id reward id
     * @param createReward reward object
     * @return the object of reward for which the award status has been changed.
     */

    @ApiOperation(value = "Update award status by id")
    @PutMapping("/updateAwardStatus/{id}")
    public ResponseEntity<Rewards> updateAwardStatus(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Award status Id to update award status", required = true)@PathVariable Long id,
                                     @ApiParam(value = "Reward object ", required = true) @Valid @RequestBody Rewards createReward) throws IOException, MessagingException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException, javax.mail.MessagingException {

        String email=validity.check(token);
        ResponseEntity<Rewards> rewards=rewardsService.updateAwardStatus(id, createReward);

        return new ResponseEntity(rewards,HttpStatus.OK);
    }



    /**
     * @param token jwt token
     * @return list of rewards.
     */
    @ApiOperation(value = "Get the list of rewards")
    @GetMapping("/listRewards")
    public List<Rewards> list(@RequestHeader(value = "Authorization") String token) throws IOException, MessagingException {
        String email=validity.check(token);
       // sendEmail.sendEmailWithAttachment("anjali.garg@nineleaps.com","Testing from Spring Boot");
        return  rewardsService.findAll();
    }

    /**
     * @param token jwt token
     * @return list of latest rewards.
     */
    @ApiOperation(value = "Get the latest list of rewards")
    @GetMapping("/listLatestRewards")
    public List<Rewards> latest(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return rewardsService.latest(email);
    }

    /**
     * @param token jwt token
     * @return list of rewards which are rolled-out.
     */
    @ApiOperation(value = "Get the list of rolled out rewards")
    @GetMapping("/listRolledOut")
    public List<Rewards> listRolledOut(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return rewardsService.findByRolled(email);
    }

    /**
     * @param token jwt token
     * @return list of rewards for which the manager needs to approve.
     */
    @ApiOperation(value = "Get the list of self-nominated rewards for manager approval")
    @GetMapping("/managerApprovalRewards")
    public List<Rewards> listSelfNominate(@RequestHeader(value = "Authorization") String token) {
        String email=validity.check(token);
        return rewardsService.managerApprovalRewards(email);
    }

    /**
     * @param token jwt token
     * @param id reward id
     * @return list of rewards based on id.
     */
    @ApiOperation(value = "Get the list of rewards by id")
    @GetMapping("/listRewards/{id}")
    public Optional<Rewards> getById(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to get reward object", required = true) @PathVariable Long id){
        String email=validity.check(token);
        return rewardsService.findById(id);
    }

    /**
     * @param token jwt token
     * @param id reward id
     * @param rewards Reward object
     * @return object of reward after updating.
     */
    @ApiOperation(value = "Update the reward by id")
    @PutMapping("/updateReward/{id}")
    public Rewards Update(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to update reward object", required = true)@PathVariable Long id, @ApiParam(value = "Reward object ", required = true) @Valid @RequestBody Rewards rewards){
        return rewardsService.Update(id, rewards);
    }

    /**
     * @param token jwt token
     * @param id reward id
     * @return a string that displays the reward has been successfully deleted.
     */
    @ApiOperation(value = "Delete the reward by id")
    @DeleteMapping("/deleteRewards/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to delete reward object", required = true) @PathVariable long id){
        String email=validity.check(token);
        rewardsService.deleteById(id);
        return "Deleted Successfully";
    }

    /**
     * @param token
     * @param id
     * @return object of reward based on id.
     */
    @ApiOperation(value = "Get the user details for editing by user id")
    @GetMapping("/listRolledOutRewardEdit/{id}")
    public Optional<Rewards> rollOutListReward(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Reward Id to get reward object", required = true)@PathVariable Long id){

        String email=validity.check(token);
        return rewardsService.rollOutListReward(id);
    }

    /**
     * @param token jwt token
     * @param id reward id
     * @param rewards Reward object
     * @return object of reward after updating.
     */
    @ApiOperation(value = "Update the reward by id")
    @PutMapping("/updateRollOutReward/{id}")
    public Rewards RollOutUpdate(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to update reward object", required = true)@PathVariable Long id, @ApiParam(value = "Reward object ", required = true) @Valid @RequestBody Rewards rewards){

        String email=validity.check(token);


        return rewardsService.rollOutUpdate(id, rewards);
    }
}
