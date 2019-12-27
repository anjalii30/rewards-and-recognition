package com.rar.service;

import com.rar.dto.ListRollOutEdit;
import com.rar.dto.RewardPojo;
import com.rar.dto.RewardsDto;
import com.rar.model.Rewards;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface RewardsService {

    ResponseEntity<Rewards> update(Long id, RewardsDto createReward);

    ResponseEntity<Rewards> findById(Long id);

    ResponseEntity<Rewards> updateAwardStatus(Long id, RewardPojo rewardPojo) throws IOException, MessagingException, javax.mail.MessagingException, com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

    Rewards save(Rewards rewards);

    ResponseEntity<List<Rewards>> findAll();

    ResponseEntity<List<Rewards>> findByRolled(String email);

    ResponseEntity<Rewards> rewardsSave(RewardsDto rewards);

    ResponseEntity<List<Rewards>> latest(String email);


    ResponseEntity<ListRollOutEdit> rollOutListReward(long id);

    ResponseEntity<Rewards> rollOutUpdate(Long id, RewardsDto reward);
}
