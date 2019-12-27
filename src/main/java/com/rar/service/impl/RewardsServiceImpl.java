package com.rar.service.impl;

import com.rar.dto.ListRollOutEdit;
import com.rar.dto.RewardPojo;
import com.rar.dto.RewardsDto;
import com.rar.enums.FrequencyEnum;
import com.rar.exception.RecordNotFoundException;
import com.rar.model.Rewards;
import com.rar.model.RewardsCriteria;
import com.rar.repository.*;
import com.rar.service.NotificationsService;
import com.rar.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static com.rar.utils.Constants.DISCONTINUED;
import static com.rar.utils.Constants.ROLLED_OUT;

@Service
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private RewardsCriteriaRepository rewardsCriteriaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private NominationsRepository nominationsRepository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private NotificationsService notificationsService;


    static String[] monthName = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};

    private Calendar cal = Calendar.getInstance();
    private String month = monthName[cal.get(Calendar.MONTH)];

    private String year = String.valueOf(cal.get(Calendar.YEAR));

    @Override
    public Rewards save(Rewards rewards) {
        return rewardsRepository.save(rewards);
    }

    @Override
    public ResponseEntity<List<Rewards>> findAll() {

        return new ResponseEntity<>(rewardsRepository.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Rewards> update(Long id, RewardsDto createReward) {
        Rewards createRewards = null;
        if (rewardsRepository.findById(id).isPresent()) {
            createRewards = rewardsRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("record not found"));
            System.out.println(createRewards.getRewardName());
        }
        if (null != createRewards) {
            createRewards.setRewardName(createReward.getRewardName());
            createRewards.setFrequency(createReward.getFrequency());
            createRewards.setDescription(createReward.getDescription());
            createRewards.setStartDate(createReward.getStartDate());
            createRewards.setEndDate(createReward.getEndDate());
            createRewards.setAwardStatus(createReward.getAwardStatus());
            createRewards.setDiscontinuingDate(createReward.getDiscontinuingDate());
            createRewards.setDiscontinuingReason(createReward.getDiscontinuingReason());
            createRewards.setSelfNominate(createReward.isSelfNominate());
            createRewards.setNominationsAllowed(createReward.getNominationsAllowed());
            createRewards.setCategory(createReward.getCategory());
            createRewards.setRegenerated(createRewards.isRegenerated());
            createRewards.setCoins(createReward.getCoins());

            Rewards rewardData1 = rewardsRepository.save(createRewards);


            for (RewardsCriteria ff : createRewards.getCriteria()) {
                rewardsCriteriaRepository.deleteById(ff.getRewardId(), ff.getCriteriaId());
            }

            RewardsCriteria rewardsCriteria;

            for (int i = 0; i < createReward.getCriteria().size(); i++) {
                rewardsCriteria = new RewardsCriteria();

                rewardsCriteria.setRewardId(id);
                rewardsCriteria.setCriteriaId(createReward.getCriteria().get(i).getCriteriaId());
                rewardsCriteria.setCompulsory(createReward.getCriteria().get(i).getCompulsory());
                rewardsCriteriaRepository.insertById(createReward.getCriteria().get(i).getCriteriaId(), id, createReward.getCriteria().get(i).getCompulsory());

            }
            return new ResponseEntity<>(rewardData1, HttpStatus.OK);
        }
        return null;
    }

    @Override
    public ResponseEntity<Rewards> updateAwardStatus(Long id, RewardPojo rewardPojo) throws IOException, MessagingException {

        LocalDate today = LocalDate.now();

        Rewards createReward = rewardsRepository.findById(id).get();
        if (rewardPojo.getAwardStatus() == ROLLED_OUT) {
            if (createReward.getFrequency() == FrequencyEnum.ANNUALLY)

                createReward.setRewardName(createReward.getRewardName() + " for " + year);

            else
                createReward.setRewardName(createReward.getRewardName() + " for " + month + " " + year);
        } else
            createReward.setRewardName(createReward.getRewardName());

        createReward.setFrequency(createReward.getFrequency());
        createReward.setDescription(createReward.getDescription());
        createReward.setDiscontinuingDate(rewardPojo.getDiscontinuingDate());
        createReward.setDiscontinuingReason(rewardPojo.getDiscontinuingReason());
        createReward.setSelfNominate(createReward.isSelfNominate());
        createReward.setNominationsAllowed(createReward.getNominationsAllowed());
        createReward.setAwardStatus(rewardPojo.getAwardStatus());
        createReward.setStartDate(today);

        FrequencyEnum frequency = createReward.getFrequency();

        if (frequency == FrequencyEnum.MONTHLY)
            createReward.setEndDate(today.plusMonths(1));
        else if (frequency == FrequencyEnum.QUARTERLY)
            createReward.setEndDate(today.plusMonths(4));
        else if (frequency == FrequencyEnum.ANNUALLY)
            createReward.setEndDate(today.plusYears(1));

        Rewards update = rewardsRepository.save(createReward);
        notificationsService.awardStatusChanged(id, rewardPojo.getAwardStatus(), rewardPojo.getDiscontinuingReason());

        if (rewardPojo.getAwardStatus() == ROLLED_OUT) {

            String rewardName = rewardsRepository.getRewardName(id);
            String[] emails = managerRepository.getAllEmails();

            for (int i = 0; i < emails.length; i++) {
                String name = userRepository.getName(emails[i]);
                sendEmail.sendEmailWithoutAttachment(emails[i], "New Reward rolled out",
                        "Hello, " + name + ". A new reward " + rewardName.toUpperCase() + " has been rolled out. Go, check it out and nominate..!!");
            }
        } else if (rewardPojo.getAwardStatus() == DISCONTINUED) {

            String[] emails = managerRepository.getAllEmails();

            for (int i = 0; i < emails.length; i++) {
                String name = userRepository.getName(emails[i]);
                sendEmail.sendEmailWithoutAttachment(emails[i], "Reward discontinued",
                        "Hello, " + name + ". " + rewardsRepository.getRewardName(id).toUpperCase() + " has been discontinued because " + rewardPojo.getDiscontinuingReason());
            }
        }
        return ResponseEntity.ok(update);

    }

    @Override
    public ResponseEntity<Rewards> findById(Long id) {
        return new ResponseEntity(rewardsRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<List<Rewards>> latest(String email) {
        Long managerId = managerRepository.findByEmail(email);
        return new ResponseEntity(rewardsRepository.latest(managerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Rewards>> managerApprovalRewards(String email) {

        List<Rewards> rewards = null;
        Long managerId = managerRepository.findByEmail(email);
        if (managerId != null) {
            rewards = rewardsRepository.managerApprovalRewards();
        } else {
            rewards = new ArrayList<>();
        }
        return new ResponseEntity<>(rewards, HttpStatus.OK);


    }


    @Override
    public ResponseEntity<List<Rewards>> findByRolled(String email) {

        List<Rewards> rewards = null;
        Long managerId = managerRepository.findByEmail(email);
        Long userId = userRepository.getIdByEmail(email);
        if (managerId != null) {
            Long[] projects = managerRepository.getProjectsOfManager(managerId);

            for (int i = 0; i < projects.length; i++) {
                rewards = rewardsRepository.findByRolled(projects[i], managerId);
            }
        } else {
            rewards = rewardsRepository.findByRolledForEmp(userId);
        }
        return new ResponseEntity<>(rewards, HttpStatus.OK);

    }

    public ResponseEntity<Rewards> rewardsSave(RewardsDto rewardsDto) {

        Rewards rewards = new Rewards();
        rewards.setRewardName(rewardsDto.getRewardName());
        rewards.setCoins(rewardsDto.getCoins());
        rewards.setStartDate(rewardsDto.getStartDate());
        rewards.setRegenerated(rewardsDto.isRegenerated());
        rewards.setNominationsAllowed(rewardsDto.getNominationsAllowed());
        rewards.setDescription(rewardsDto.getDescription());
        rewards.setEndDate(rewardsDto.getEndDate());
        rewards.setCategory(rewardsDto.getCategory());
        rewards.setAwardStatus(rewardsDto.getAwardStatus());
        rewards.setFrequency(rewardsDto.getFrequency());
        rewards.setSelfNominate(rewardsDto.isSelfNominate());
        rewards.setDiscontinuingDate(rewardsDto.getDiscontinuingDate());
        rewards.setDiscontinuingReason(rewardsDto.getDiscontinuingReason());
        rewards.setRollOutId(rewardsDto.getRollOutId());
        save(rewards);

        long id = rewards.getRewardId();

        RewardsCriteria rewardsCriteria;

        for (int i = 0; i < rewards.getCriteria().size(); i++) {
            rewardsCriteria = new RewardsCriteria();

            rewardsCriteria.setRewardId(id);
            rewardsCriteria.setCriteriaId(rewardsDto.getCriteria().get(i).getCriteriaId());
            rewardsCriteria.setCompulsory(rewardsDto.getCriteria().get(i).getCompulsory());

            rewardsCriteriaRepository.save(rewardsCriteria);
        }

        HashMap<String, Object> s = new HashMap<>();
        s.put("rewards", rewards);
        return new ResponseEntity(s, HttpStatus.OK);
    }

    public ResponseEntity<ListRollOutEdit> rollOutListReward(long id) {
        Optional<Rewards> rewards = rewardsRepository.findById(id);
        long rewardId = rewards.get().getRewardId();
        Long coins = rewards.get().getCoins();
        FrequencyEnum frequency = rewards.get().getFrequency();
        String description = rewards.get().getDescription();
        LocalDate endDate = rewards.get().getEndDate();
        int nominationsAllowed = rewards.get().getNominationsAllowed();
        List<RewardsCriteria> criteria = rewards.get().getCriteria();
        if (rewardsRepository.findEditRollOutId(id) == 0) {
            if (rewards.get().getRewardName().contains("for ")) {
                String rewardName = rewards.get().getRewardName().substring(0, rewards.get().getRewardName().indexOf("for ") - 1);

                return new ResponseEntity(new ListRollOutEdit(rewardId, rewardName, coins, frequency, description, endDate, nominationsAllowed, criteria), HttpStatus.OK);
            } else {
                String rewardName = rewards.get().getRewardName();
                return new ResponseEntity(new ListRollOutEdit(rewardId, rewardName, coins, frequency, description, endDate, nominationsAllowed, criteria), HttpStatus.OK);
            }
        } else {
            String rewardName = rewards.get().getRewardName();
            return new ResponseEntity(new ListRollOutEdit(rewardId, rewardName, coins, frequency, description, endDate, nominationsAllowed, criteria), HttpStatus.OK);
        }
    }

    public ResponseEntity<Rewards> rollOutUpdate(Long id, RewardsDto reward) {
        if (rewardsRepository.findEditRollOutId(id) == 0 && rewardsRepository.checkingRewardInRolledOut(id) == 0) {
            ResponseEntity<Rewards> rewards = rewardsSave(reward);
            rewardsRepository.regenerationCancel(id);
            rewardsRepository.updateRolledOutColumn(id, reward.getRewardId());
            rewardsRepository.updateRolledOutEditAwardStatus(reward.getRewardId());
            rewardsRepository.initialisingRollOutEditEndDate(reward.getRewardId());
            return rewards;
        } else if (rewardsRepository.findEditRollOutId(id) == 0 && rewardsRepository.checkingRewardInRolledOut(id) > 0) {
            return update(id, reward);
        }
        return null;
    }


}



