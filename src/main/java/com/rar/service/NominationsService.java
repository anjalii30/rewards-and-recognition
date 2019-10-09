package com.rar.service;


import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface NominationsService {

    ResponseEntity<?> nominationSave(NominationPojo nominationPojo);

    List<Nominations> GetData(Map<String, Long> rewardID);

}
