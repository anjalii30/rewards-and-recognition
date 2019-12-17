package com.rar.controller;

import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import com.rar.model.Rewards;
import com.rar.repository.ManagerRepository;
import com.rar.repository.UserRepository;
import com.rar.service.NominationsService;
import com.rar.service.impl.CheckValidity;
//import com.rar.utils.UtcDate;
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
    //UtcDate utcDate;
    @Autowired
    private NominationsService nominationsService;

    @Autowired
    private CheckValidity validity;

    @Autowired
    private UserRepository userRepository;

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
        String email=validity.check(token);
        Long manager_id=managerRepository.findByEmail(email);
        nominationsService.nominationSave(nominationPojo, manager_id);
        return new ResponseEntity<>(nominationPojo, HttpStatus.OK);
    }

    /**
     * @param token jwt token
     * @param id reward id
     * @return list of nominations based on id.
     */
    @ApiOperation(value = "Get the list of nominations for admin by reward id")
    @GetMapping("/showNomination/{id}")
    public List<Nominations> showById(@RequestHeader(value = "Authorization") String token,  @ApiParam(value = "Get nomination object by reward_id", required = true) @PathVariable Long id) throws Exception {
       String email=validity.check(token);
        return nominationsService.GetData(id);
    }

    /**
     * @param token jwt token
     * @return list of all nominations.
     */
    @ApiOperation(value = "Get the list of all nominations for admin")
    @GetMapping("/showAllNomination")
    public List<Nominations> show(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
         return  nominationsService.getAllNominations();

    }

    /**
     * @param token jwt token
     * @return list of rewards that are nominated.
     */
    @ApiOperation(value = "Get the list of rewards for all the nominations")
    @GetMapping("/showNominatedRewards")
    public  List<Rewards> showNominatedRewards(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return nominationsService.nominated_rewards();
    }

    //used for self-nomination
   /* *//**
     *
     * @param token jwt token
     * @param id reward id
     * @return list of all the nominations for that manager for the particular reward
     * @throws Exception no nominations
     *//*
    @ApiOperation(value = "Get the list of nominations for manager by reward id")
    @GetMapping("/showToManager/{id}")
    public List<List<Nominations>> showToManager(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) throws Exception {
        String email=validity.check(token);
        return nominationsService.showToManager(email,id);

    }

    *//**
     * @param token jwt token
     * @return list of list of nominations.
     * @throws Exception that displays "You are not a manager" when a normal user logs in.
     *//*
    @ApiOperation(value = "Get the list of all the nominations for manager")
    @GetMapping("/showToManager")
    public List<List<Nominations>> showAllToManager(@RequestHeader(value = "Authorization") String token) throws Exception {
        String email = validity.check(token);
        return nominationsService.showAllToManager(email);
    }*/

    /**
     * @param token jwt token
     * @param n id of the nomination that needs to be updated as awardee.
     */
   @ApiOperation(value = "awardee selected by admin")
   @PostMapping("/awardee")
   public void awardeeSelect(@RequestHeader(value = "Authorization") String token , @ApiParam(value = "update Nomination object by id", required = true) @RequestBody Map<String, Long[]> n) throws IOException, MessagingException, TemplateException {
       String email=validity.check(token);
       nominationsService.awardeeSelect(n);
   }

    /**
     * @param token jwt token
     * @return list of all awardee
     */
    @ApiOperation(value = "show the list of awardee ")
    @GetMapping("/awardedList")
    public List<Map<String,String>> getByAwardedId(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);

        return nominationsService.getAwardedPeople();
    }

    /**
     * @param token jwt token
     * @return list of top three awardee
     */
    @ApiOperation(value = "show top three awardee ")
    @GetMapping("/topAwardee")
    public List getTopAwardee(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);

        return nominationsService.getTopAwardee();
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

/* @GetMapping("/")
 public void abc() throws ParseException {
     LocalDate d2 = LocalDate.now();
     System.out.println(d2+" localdate");

     Date d1 =utcDate.dateToday(LocalDate.now());
     Instant today = Instant.now();
     System.out.println(today);
     SimpleDateFormat df=new SimpleDateFormat("yyy-mm-dd ");
     df.format(today);
     System.out.println(today);
 }*/
}
