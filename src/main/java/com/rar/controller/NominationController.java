package com.rar.controller;

import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import com.rar.service.NominationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/trail")
public class NominationController {

    @Autowired
    private NominationsService nominationsService;


    @PostMapping("/saveNomination")
    public ResponseEntity<?> nominationSave(@RequestHeader(value = "Authorization") String token ,@RequestBody NominationPojo nominationPojo) {
         nominationsService.nominationSave(nominationPojo);
         return ResponseEntity.ok(nominationPojo);
    }

    @GetMapping("/showNomination")
    public List<Nominations> show(@RequestHeader(value = "Authorization") String token ,@RequestBody Map<String,Long> rewardID){
        return nominationsService.GetData(rewardID);
    }
}
