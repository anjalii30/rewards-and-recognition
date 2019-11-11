package com.rar.service;

import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import com.rar.model.Rewards;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface NominationsService {

    ResponseEntity<?> nominationSave(List<NominationPojo> nominationPojo);

    List<Nominations> GetData(Long rewardID) throws Exception;

    List<List<Nominations>> showToManager(String email, Long reward_id) throws Exception;

    void awardeeSelect(Map<String, Long[]> nomination_id);

    List<Map<String,String>> getAwardedPeople();

    List<List<Nominations>> showAllToManager(String email) throws Exception;

    List<Nominations> getAllNominations();

    void managerSelect(Nominations[] nominations);

    List<Rewards> nominated_rewards() throws Exception;

    List getTopAwardee();
}
