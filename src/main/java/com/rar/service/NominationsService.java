package com.rar.service;


import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import com.rar.model.Rewards;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface NominationsService {

    ResponseEntity<?> nominationSave(List<NominationPojo> nominationPojo);

/*
    ResponseEntity<?> GetData(Long rewardID) throws Exception;
*/
List<Nominations> GetData(Long rewardID) throws Exception;


    List<List<Nominations>> showToManager(String email, Long reward_id) throws Exception;

    void awardeeSelect(Map<String, Long[]> nomination_id);

    List<Map<String,String>> getAwardedPeople();

    List<List<Nominations>> showAllToManager(String email) throws Exception;

//     void managerNominate(List<NominationPojo> nominationsPojo);

    List<Nominations> getAllNominations();

    void managerSelect(Nominations[] nominations,Long manager_id, String manager_name);

    List<Rewards> nominated_rewards() throws Exception;

    List getTopAwardee();
}
