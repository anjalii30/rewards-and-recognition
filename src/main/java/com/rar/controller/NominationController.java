package com.rar.controller;

import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import com.rar.model.Rewards;
import com.rar.repository.UserRepository;
import com.rar.service.NominationsService;
import com.rar.utils.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@EnableAutoConfiguration
@Api(value="Nomination Management System")
public class NominationController {

    @Autowired
    private NominationsService nominationsService;

    @Autowired
    private CheckValidity validity;

    @Autowired
    private UserRepository userRepository;



    @ApiOperation(value = "Save the nomination")
    @PostMapping("/saveNomination")
    public ResponseEntity<?> nominationSave(@RequestHeader(value = "Authorization") String token ,@ApiParam(value = "Nomination object store in database table", required = true) @Valid @RequestBody List<NominationPojo> nominationPojo) {
        String email=validity.check(token);
        nominationsService.nominationSave(nominationPojo);
        return ResponseEntity.ok(nominationPojo);
    }

   /* @ApiOperation(value = "Manager nominating their team members")
    @PostMapping("/managerNominate")
    public <nominationPojo> void managerNominate(@RequestHeader(value = "Authorization") String token, ArrayList<NominationPojo> nominations ){
        String email=validity.check(token);
        nominationsService.managerNominate(nominations);

    }*/

    @ApiOperation(value = "Get the list of nominations for admin by reward id")
    @GetMapping("/showNomination/{id}")
    public List<Nominations> showById(@RequestHeader(value = "Authorization") String token,  @ApiParam(value = "Get nomination object by reward_id", required = true) @PathVariable Long id) throws Exception{
       String email=validity.check(token);
        return nominationsService.GetData(id);
    }

    @ApiOperation(value = "Get the list of all nominations for admin")
    @GetMapping("/showAllNomination")
    public List<Nominations> show(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return nominationsService.getAllNominations();
    }

    @ApiOperation(value = "Get the list of rewards for all the nominations")
    @GetMapping("/showNominatedRewards")
    public  List<Rewards> showNominatedRewards(@RequestHeader(value = "Authorization") String token) throws Exception{
        String email=validity.check(token);
        return nominationsService.nominated_rewards();
    }


    @ApiOperation(value = "Get the list of nominations for manager by reward id")
    @GetMapping("/showToManager/{id}")
    public List<List<Nominations>> showToManager(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) throws Exception {
        String email=validity.check(token);
        return nominationsService.showToManager(email,id);

    }

    @ApiOperation(value = "Get the list of all the nominations for manager")
    @GetMapping("/showToManager")
    public List<List<Nominations>> showAllToManager(@RequestHeader(value = "Authorization") String token) throws Exception {
        String email = validity.check(token);
        return nominationsService.showAllToManager(email);
    }

   /* @ApiOperation(value = "mark employee selected by HR")
    @PutMapping("/awardee/{nomination_id}")
    public void awardeeSelect(@RequestHeader(value = "Authorization") String token , @ApiParam(value = "update Nomination object by id", required = true) @PathVariable Long nomination_id) {
        String email=validity.check(token);
       // Long nomination_id=nominations.getNominationID();
     //   return  nominationsService.awardeeSelect(nomination_id.get("nomination_id"));
        nominationsService.awardeeSelect(nomination_id);
    }*/
   @ApiOperation(value = "awardee selected by admin")
   @PostMapping("/awardee")
   public void awardeeSelect(@RequestHeader(value = "Authorization") String token , @ApiParam(value = "update Nomination object by id", required = true) @RequestBody Map<String, Long[]> n) {
       String email=validity.check(token);
       // Long nomination_id=nominations.getNominationID();
       //   return  nominationsService.awardeeSelect(nomination_id.get("nomination_id"));
       nominationsService.awardeeSelect(n);
   }

    @ApiOperation(value = "show the list of awardee ")
    @GetMapping("/awardedList")
    public List<Map<String,String>> getByAwardedId(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);

        return nominationsService.getAwardedPeople();
    }

    @ApiOperation(value = "show top 6 awardee ")
    @GetMapping("/topAwardee")
    public List getTopAwardee(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);

        return nominationsService.getTopAwardee();
    }

    @ApiOperation(value="mark selected by manager from self nominations of team members by nomination id")
    @PutMapping("/managerSelect")
    public void managerSelect(@RequestHeader(value = "Authorization") String token ,@RequestBody Nominations[] nominations) {
        String email=validity.check(token);
        nominationsService.managerSelect(nominations);
    }

}
