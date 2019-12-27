package com.rar.service;

import com.rar.dto.History;
import com.rar.dto.NominationPojo;
import com.rar.model.Nominations;
import com.rar.model.Rewards;
import freemarker.template.TemplateException;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface NominationsService {

    ResponseEntity<List<HashMap<String,Object>>> nominationSave(List<NominationPojo> nominationPojo, Long managerId);

    ResponseEntity<List<Nominations>> getData(Long rewardID);

    void awardeeSelect(Map<String, Long[]> nominationId) throws IOException, MessagingException, TemplateException;

    ResponseEntity<List<Map<String,String>>> getAwardedPeople();


    void managerSelect(Nominations[] nominations,Long managerId, String managerName);

    ResponseEntity<List<Rewards>> nominatedRewards() ;

    ResponseEntity<List> getTopAwardee();

     ResponseEntity<List<History>> history(String email) ;

     void rewardCoins(Long[] nominationID);
}
