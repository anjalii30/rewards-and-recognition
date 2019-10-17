package com.rar.service;


import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NominationsService {

    ResponseEntity<?> nominationSave(NominationPojo nominationPojo);

    List<Nominations> GetData(Long rewardID);

    List<Nominations> showToManager(String email) throws Exception;

    void awardeeSelect(Long[] nomination_id);
}
