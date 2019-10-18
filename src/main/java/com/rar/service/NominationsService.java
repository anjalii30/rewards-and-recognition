package com.rar.service;


import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NominationsService {

    ResponseEntity<?> nominationSave(NominationPojo nominationPojo);

    List<Nominations> GetData(Long rewardID);

    List<Nominations> showToManager(String email,Long reward_id) throws Exception;

    void awardeeSelect(Long[] nomination_id);

    List<Map<String,String>> getAwardedPeople();

    //List<Nominations> getAllNominations();
}
