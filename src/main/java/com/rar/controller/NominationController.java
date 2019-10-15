package com.rar.controller;

import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import com.rar.service.NominationsService;
import com.rar.utils.CheckDisable;
import com.rar.utils.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.jetbrains.annotations.NotNull;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin
@RestController
@EnableAutoConfiguration
@Api(value="Nomination Management System")
public class NominationController {

    @Autowired
    private NominationsService nominationsService;

    @Autowired
    private CheckValidity validity;




    @ApiOperation(value = "Save the nomination")
    @PostMapping("/saveNomination")
    public ResponseEntity<?> nominationSave(@RequestHeader(value = "Authorization") String token ,@ApiParam(value = "Nomination object store in database table", required = true) @Valid @RequestBody NominationPojo nominationPojo) {
        String email=validity.check(token);
        nominationsService.nominationSave(nominationPojo);
        return ResponseEntity.ok(nominationPojo);
    }



    @ApiOperation(value = "Get the list of nominations")
    @GetMapping("/showNomination/{id}")
    public List<Nominations> show(@RequestHeader(value = "Authorization") String token,  @ApiParam(value = "Get nomination object by id", required = true) @PathVariable Long id){
        String email=validity.check(token);
        return nominationsService.GetData(id);
    }

}
