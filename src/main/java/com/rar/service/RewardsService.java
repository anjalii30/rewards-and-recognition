package com.rar.service;

import com.rar.model.Rewards;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface RewardsService {

     Rewards Update(Long id, Rewards createreward);

    ResponseEntity<Rewards> updateAwardStatus(Long id, Rewards createReward) throws IOException, MessagingException, javax.mail.MessagingException, com.sun.xml.messaging.saaj.packaging.mime.MessagingException;


    Optional<Rewards> findById(Long id);

    Rewards save(Rewards rewards);

    ResponseEntity<List<Rewards>> findAll();

    void deleteById(long id);

    ResponseEntity<List<Rewards>> findByRolled(String email);

    ResponseEntity<Rewards> rewardsSave(Rewards rewards);

    ResponseEntity<List<Rewards>> latest(String email);

    List<Rewards> managerApprovalRewards(String email);

    Optional<Rewards> rollOutListReward(long id);

    Rewards rollOutUpdate(Long id, Rewards reward);
}
