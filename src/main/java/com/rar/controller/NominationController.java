package com.rar.controller;

import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import com.rar.service.NominationsService;
import com.rar.utils.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@EnableAutoConfiguration
@Api(value = "Controller for nominating for a reward and displaying nominations for HR")
@RequestMapping(value = "/trail")
public class NominationController {

    @Autowired
    private NominationsService nominationsService;

    @Autowired
    private CheckValidity validity;


    @ApiOperation(value = "save the nomination data ")
    @PostMapping("/saveNomination")
    public ResponseEntity<?> nominationSave(@RequestHeader(value = "Authorization") String token ,@RequestBody NominationPojo nominationPojo) {
        String email=validity.check(token);
        nominationsService.nominationSave(nominationPojo);
        return ResponseEntity.ok(nominationPojo);
    }
    @ApiOperation(value = "display the nominations data to HR")
    @GetMapping("/showNomination")
    public List<Nominations> show(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String,Long> rewardID){
        String email=validity.check(token);
        return nominationsService.GetData(rewardID.get("rewardID"));
    }
}
