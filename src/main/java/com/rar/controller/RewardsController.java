package com.rar.controller;

import com.rar.model.Rewards;
import com.rar.repository.UserRepository;
import com.rar.service.RewardsService;
import com.rar.utils.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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

    @ApiOperation(value = "Save the rewards")
    @PostMapping("/save")
    public ResponseEntity save(@RequestHeader(value = "Authorization") String token ,@ApiParam(value = "Reward object store in database table", required = true) @Valid @RequestBody Rewards rewards) throws Exception{
       try{
           String email=validity.check(token);
           return new ResponseEntity<>(rewardsService.rewardsSave(rewards),HttpStatus.OK) ;

       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
       }
    }

    @ApiOperation(value = "Update award status by id")
    @PutMapping("/updateAwardStatus/{id}")
    public Rewards updateAwardStatus(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Award status Id to update award status", required = true)@PathVariable Long id,
                                     @ApiParam(value = "Reward object ", required = true) @Valid @RequestBody Rewards createReward){
        String email=validity.check(token);
        return rewardsService.updateAwardStatus(id, createReward);
    }

    @ApiOperation(value = "Update award status to discontinue by reward id")
    @PutMapping("/discontinuing/{id}")
    public Rewards discontinuing(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to update discontinuing reward object", required = true)@PathVariable Long id,
                                 @ApiParam(value = "Reward object ", required = true) @Valid @RequestBody Rewards createReward){
        String email=validity.check(token);
        return rewardsService.discontinuing(id, createReward);
    }

    @ApiOperation(value = "Get the list of rewards")
    @GetMapping("/listRewards")
    public List<Rewards> list(@RequestHeader(value = "Authorization") String token){
        System.out.println("token" +token);
        String email=validity.check(token);
        return  rewardsService.findAll();
    }

    @ApiOperation(value = "Get the latest list of rewards")
    @GetMapping("/listLatestRewards")
    public List<Rewards> latest(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return rewardsService.latest(email);
    }

    @ApiOperation(value = "Get the list of rolled out  rewards")
    @GetMapping("/listRolledOut")
    public List<Rewards> listRolledOut(@RequestHeader(value = "Authorization") String token) throws Exception{
        String email=validity.check(token);
        System.out.println(email);
        return rewardsService.findByRolled(email);
    }

    @ApiOperation(value = "Get the list of self-nominated rewards for manager approval")
    @GetMapping("/managerApprovalRewards")
    public List<Rewards> listSelfNominate(@RequestHeader(value = "Authorization") String token) {
        String email=validity.check(token);
        return rewardsService.managerApprovalRewards(email);
    }

    @ApiOperation(value = "Get the list of rewards by id")
    @GetMapping("/listRewards/{id}")
    public Optional<Rewards> getById(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to get reward object", required = true) @PathVariable Long id){
        String email=validity.check(token);
        return rewardsService.findById(id);
    }

    @ApiOperation(value = "Update the reward by id")
    @PutMapping("/updateReward/{id}")
    public Rewards Update(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to update reward object", required = true)@PathVariable Long id,
                          @ApiParam(value = "Reward object ", required = true) @Valid @RequestBody Rewards rewards){
        return rewardsService.Update(id, rewards);
    }

    @ApiOperation(value = "Delete the reward by id")
    @DeleteMapping("/deleteRewards/{id}")
    public String delete(@RequestHeader(value = "Authorization") String token,@ApiParam(value = "Reward Id to delete reward object", required = true) @PathVariable long id){
        String email=validity.check(token);
        rewardsService.deleteById(id);
        return "Deleted Successfully";
    }
}
