package com.rar.service;

import com.rar.DTO.RewardPojo;
import com.rar.model.Rewards;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface RewardsService {

    ResponseEntity<Rewards> Update(Long id, Rewards createReward);

    ResponseEntity<Rewards> findById(Long id);

    ResponseEntity<Rewards> updateAwardStatus(Long id, RewardPojo rewardPojo) throws IOException, MessagingException, javax.mail.MessagingException, com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

    Rewards save(Rewards rewards);

    ResponseEntity<List<Rewards>> findAll();

    ResponseEntity<List<Rewards>> findByRolled(String email);

    ResponseEntity<Rewards> rewardsSave(Rewards rewards, Boolean editReward);

    ResponseEntity<List<Rewards>> latest(String email);

    ResponseEntity<List<Rewards>>  managerApprovalRewards(String email);

    ResponseEntity<Rewards> rollOutListReward(long id);

    ResponseEntity<Rewards> rollOutUpdate(Long id, Rewards reward);
}
