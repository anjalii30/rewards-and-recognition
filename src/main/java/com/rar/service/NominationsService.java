package com.rar.service;

import com.rar.DTO.History;
import com.rar.DTO.NominationPojo;
import com.rar.model.Nominations;
import com.rar.model.Rewards;
import freemarker.template.TemplateException;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface NominationsService {

    ResponseEntity<?> nominationSave(List<NominationPojo> nominationPojo, Long managerId);
/*
    ResponseEntity<?> GetData(Long rewardID) throws Exception;
*/
ResponseEntity<List<Nominations>> GetData(Long rewardID) throws Exception;


   // List<List<Nominations>> showToManager(String email, Long reward_id) throws Exception;

    void awardeeSelect(Map<String, Long[]> nomination_id) throws IOException, MessagingException, TemplateException;

    ResponseEntity<List<Map<String,String>>> getAwardedPeople();

  //  List<List<Nominations>> showAllToManager(String email) throws Exception;

//     void managerNominate(List<NominationPojo> nominationsPojo);

    ResponseEntity<List<Nominations>> getAllNominations();

    void managerSelect(Nominations[] nominations,Long managerId, String managerName);

    ResponseEntity<List<Rewards>> nominatedRewards() ;

    ResponseEntity<List> getTopAwardee();

    public ResponseEntity<List<History>> history(long managerId) throws Exception;

     void rewardCoins(Long[] nominationId);
}
