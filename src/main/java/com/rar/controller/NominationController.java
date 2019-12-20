package com.rar.controller;

import com.rar.DTO.History;
import com.rar.DTO.NominationPojo;
import com.rar.exception.IncorrectFieldException;
import com.rar.exception.RecordNotFoundException;
import com.rar.model.Nominations;
import com.rar.model.Rewards;
import com.rar.repository.ManagerRepository;
import com.rar.repository.RewardsRepository;
import com.rar.repository.UserRepository;
import com.rar.service.NominationsService;
import com.rar.service.impl.CheckValidity;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
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

    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private ManagerRepository managerRepository;

    /**
     * @param token jwt token
     * @param nominationPojo object
     * @return saved nominationPojo
     */
    @ApiOperation(value = "Save the nomination")
    @PostMapping("/saveNomination")
    public ResponseEntity nominationSave(@RequestHeader(value = "Authorization") String token , @ApiParam(value = "Nomination object store in database table", required = true) @Valid @RequestBody List<NominationPojo> nominationPojo) {
        try {
            String email = validity.check(token);
            Long manager_id = managerRepository.findByEmail(email);
            nominationsService.nominationSave(nominationPojo, manager_id);
            return new ResponseEntity<>(nominationPojo, HttpStatus.OK);
        }catch (IncorrectFieldException e) {
            throw new IncorrectFieldException("Incorrect fields given");
        }
    }

    /**
     * @param token jwt token
     * @param id reward id
     * @return list of nominations based on id.
     */
    @ApiOperation(value = "Get the list of nominations for admin by reward id")
    @GetMapping("/showNomination/{id}")
    public ResponseEntity<List<Nominations>> showById(@RequestHeader(value = "Authorization") String token,  @ApiParam(value = "Get nomination object by reward_id", required = true) @PathVariable Long id) throws Exception {
           validity.check(token);
           if(rewardsRepository.existsById(id))
           return new ResponseEntity(nominationsService.GetData(id),HttpStatus.OK);
           else
               throw new RecordNotFoundException("reward id not found");
    }

    /**
     * @param token jwt token
     * @return list of all nominations.
     */
    @ApiOperation(value = "Get the list of all nominations for admin")
    @GetMapping("/showAllNomination")
    public ResponseEntity<List<Nominations>> show(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return new ResponseEntity(nominationsService.getAllNominations(),HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @return list of rewards that are nominated.
     */
    @ApiOperation(value = "Get the list of rewards for all the nominations")
    @GetMapping("/showNominatedRewards")
    public  ResponseEntity<List<Rewards>> showNominatedRewards(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return new ResponseEntity(nominationsService.nominatedRewards(),HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @param nominationId id of the nomination that needs to be updated as awardee.
     */
   @ApiOperation(value = "awardee selected by admin")
   @PostMapping("/awardee")
   public void awardeeSelect(@RequestHeader(value = "Authorization") String token , @ApiParam(value = "update Nomination object by id", required = true) @RequestBody Map<String, Long[]> nominationId) throws IOException, MessagingException, TemplateException {
       validity.check(token);
       nominationsService.awardeeSelect(nominationId);
   }

    /**
     * @param token jwt token
     * @return list of all awardee
     */
    @ApiOperation(value = "show the list of awardee ")
    @GetMapping("/awardedList")
    public ResponseEntity<List<Map<String,String>>> getByAwardedId(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return new ResponseEntity(nominationsService.getAwardedPeople(),HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @return list of top three awardee
     */
    @ApiOperation(value = "show top six awardee ")
    @GetMapping("/topAwardee")
    public ResponseEntity<List> getTopAwardee(@RequestHeader(value = "Authorization") String token){
        validity.check(token);
        return new ResponseEntity(nominationsService.getTopAwardee(),HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @param nominations for which the manager approves.
     */
    @ApiOperation(value="mark selected by manager from self nominations of team members by nomination id")
    @PutMapping("/managerSelect")
    public void managerSelect(@RequestHeader(value = "Authorization") String token ,@RequestBody Nominations[] nominations) {
        String email=validity.check(token);
        Long manager_id=managerRepository.findByEmail(email);
        String manager_name=userRepository.getName(email);
        nominationsService.managerSelect(nominations,manager_id,manager_name);
    }

    /**
     * @param token jwt token
     * @return list of rewards with employees which the manager has nominated.
     */
    @ApiOperation(value = "Get the list of rewards and their nominees")
    @GetMapping("/history/{id}")
    public ResponseEntity<List<History>> showHistory(@RequestHeader(value = "Authorization") String token, @ApiParam(value = "Get history of nominations object by user_id", required = true) @PathVariable Long id) throws Exception{
        validity.check(token);
        return new ResponseEntity(nominationsService.history(id),HttpStatus.OK);
    }

}
