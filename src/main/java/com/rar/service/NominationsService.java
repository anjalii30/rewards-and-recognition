package com.rar.service;


import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NominationsService {

    ResponseEntity<?> nominationsave(NominationPojo nominationPojo);

    List<Nominations> GetData(long rewardID);

}
