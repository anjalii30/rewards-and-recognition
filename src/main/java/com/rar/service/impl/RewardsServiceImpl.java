package com.rar.service.impl;

import com.rar.DTO.ListRollOutEdit;
import com.rar.DTO.RewardPojo;
import com.rar.enums.FrequencyEnum;
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

        return new ResponseEntity<>(rewardsRepository.getAll(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Rewards> Update(Long id, Rewards createReward) {
        Rewards CreateReward1 = rewardsRepository.findById(id).get();
        CreateReward1.setRewardName(createReward.getRewardName());
        CreateReward1.setFrequency(createReward.getFrequency());
        CreateReward1.setDescription(createReward.getDescription());
        CreateReward1.setStartDate(createReward.getStartDate());
        CreateReward1.setEndDate(createReward.getEndDate());
        CreateReward1.setAwardStatus(createReward.getAwardStatus());
        CreateReward1.setDiscontinuingDate(createReward.getDiscontinuingDate());
        CreateReward1.setDiscontinuingReason(createReward.getDiscontinuingReason());
        CreateReward1.setSelfNominate(createReward.isSelfNominate());
        CreateReward1.setNominationsAllowed(createReward.getNominationsAllowed());
        CreateReward1.setCategory(createReward.getCategory());
        CreateReward1.setRegenerated(CreateReward1.isRegenerated());
        CreateReward1.setCoins(createReward.getCoins());

        Rewards rewardData1 =  rewardsRepository.save(CreateReward1);


        for (RewardsCriteria ff : CreateReward1.getCriteria()) {
            rewardsCriteriaRepository.deleteById(ff.getRewardId(), ff.getCriteriaId());
        }

        RewardsCriteria rewardsCriteria;

        for (int i = 0; i < createReward.getCriteria().size(); i++) {
            rewardsCriteria = new RewardsCriteria();

            rewardsCriteria.setRewardId(id);
            rewardsCriteria.setCriteriaId(createReward.getCriteria().get(i).getCriteriaId());
            rewardsCriteria.setCompulsory(createReward.getCriteria().get(i).getCompulsory());
            rewardsCriteriaRepository.insertById(createReward.getCriteria().get(i).getCriteriaId(),id,createReward.getCriteria().get(i).getCompulsory());

        }

        return new ResponseEntity<>(rewardData1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Rewards> updateAwardStatus(Long id, RewardPojo rewardPojo) throws IOException, MessagingException {

      LocalDate today = LocalDate.now();

        Rewards CreateReward1 = rewardsRepository.findById(id).get();
        if(rewardPojo.getAwardStatus()==ROLLED_OUT) {
            if (CreateReward1.getFrequency() == FrequencyEnum.Annually)

                CreateReward1.setRewardName(CreateReward1.getRewardName() + " for " + year);

            else
                CreateReward1.setRewardName(CreateReward1.getRewardName() + " for " + month + " " + year);
        }
        else
            CreateReward1.setRewardName(CreateReward1.getRewardName());

        CreateReward1.setFrequency(CreateReward1.getFrequency());
        CreateReward1.setDescription(CreateReward1.getDescription());
        CreateReward1.setDiscontinuingDate(rewardPojo.getDiscontinuingDate());
        CreateReward1.setDiscontinuingReason(rewardPojo.getDiscontinuingReason());
        CreateReward1.setSelfNominate(CreateReward1.isSelfNominate());
        CreateReward1.setNominationsAllowed(CreateReward1.getNominationsAllowed());
        CreateReward1.setAwardStatus(rewardPojo.getAwardStatus());
        CreateReward1.setStartDate(today);

        FrequencyEnum frequency =CreateReward1.getFrequency();

        if(frequency==FrequencyEnum.Monthly)
            CreateReward1.setEndDate(today.plusMonths(1));
        else
            if(frequency==FrequencyEnum.Quarterly)
                CreateReward1.setEndDate(today.plusMonths(4));
        else
            if(frequency==FrequencyEnum.Annually)
                CreateReward1.setEndDate(today.plusYears(1));

        Rewards update = rewardsRepository.save(CreateReward1);
        notificationsService.awardStatusChanged(id,rewardPojo.getAwardStatus(),rewardPojo.getDiscontinuingReason());

        if(rewardPojo.getAwardStatus()==ROLLED_OUT) {

            String reward_name = rewardsRepository.getRewardName(id);
            String[] emails=managerRepository.getAllEmails();

            for (int i = 0; i < emails.length; i++) {
                String name=userRepository.getName(emails[i]);
                sendEmail.sendEmailWithoutAttachment(emails[i], "New Reward rolled out",
                        "Hello, " + name + ". A new reward " +reward_name.toUpperCase() + " has been rolled out. Go, check it out and nominate..!!");
            }
        }
        else
            if(rewardPojo.getAwardStatus()==DISCONTINUED){

                String[] emails=managerRepository.getAllEmails();

                for (int i = 0; i < emails.length; i++) {
                    String name=userRepository.getName(emails[i]);
                    System.out.println(emails[i]);
                    sendEmail.sendEmailWithoutAttachment(emails[i], "Reward discontinued",
                            "Hello, " + name + ". " +rewardsRepository.getRewardName(id).toUpperCase() + " has been discontinued because "+rewardPojo.getDiscontinuingReason());
                }
            }
        return ResponseEntity.ok(update);

    }
    @Override
    public ResponseEntity<Rewards> findById(Long id) {
        return new ResponseEntity(rewardsRepository.findById(id),HttpStatus.OK);
    }

    public ResponseEntity<List<Rewards>> latest(String email){
        Long managerId=managerRepository.findByEmail(email);
        return new ResponseEntity(rewardsRepository.latest(managerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Rewards>> managerApprovalRewards(String email) {

        List<Rewards> rewards= null;
        Long managerId = managerRepository.findByEmail(email);
        if(managerId!=null) {
            rewards=rewardsRepository.managerApprovalRewards();
        }
        else
        {
            rewards= new ArrayList<Rewards>();
        }
        return new ResponseEntity<>(rewards,HttpStatus.OK);


        }



    @Override
    public ResponseEntity<List<Rewards>> findByRolled(String email) {

        List<Rewards> rewards = null;
        Long managerId = managerRepository.findByEmail(email);
        Long userId = userRepository.getIdByEmail(email);
        if(managerId!=null) {
            Long[] projects=managerRepository.getProjectsOfManager(managerId);

            for (int i = 0; i < projects.length; i++) {
                rewards=rewardsRepository.findByRolled(projects[i],managerId);
            }
        }
        else{
            rewards= rewardsRepository.findByRolledForEmp(userId);
        }
        return new ResponseEntity<>(rewards,HttpStatus.OK);

    }

    public ResponseEntity<Rewards> rewardsSave(Rewards rewards) {


      /*  String month = monthName[cal.get(rewards.getStart_date().getMonthValue())];
        System.out.println(month);
         String year = String.valueOf(cal.get(rewards.getStart_date().getYear()));
         System.out.println(year+month);*/

            Rewards rewardData = save(rewards);

            long id = rewards.getId();

            RewardsCriteria rewardsCriteria = new RewardsCriteria();

            for (int i = 0; i < rewards.getCriteria().size(); i++) {
                rewardsCriteria = new RewardsCriteria();

                rewardsCriteria.setRewardId(id);
                rewardsCriteria.setCriteriaId(rewards.getCriteria().get(i).getCriteriaId());
                rewardsCriteria.setCompulsory(rewards.getCriteria().get(i).getCompulsory());

                rewardsCriteriaRepository.save(rewardsCriteria);
            }

            HashMap<String, Object> s = new HashMap<>();
            s.put("rewards", rewards);
            return new ResponseEntity(s,HttpStatus.OK);
    }

    public ResponseEntity<ListRollOutEdit> rollOutListReward(long id){
        Optional<Rewards> rewards=rewardsRepository.findById(id);
        long rewardId = rewards.get().getRewardId();
        Long coins = rewards.get().getCoins();
        FrequencyEnum frequency = rewards.get().getFrequency();
        String description = rewards.get().getDescription();
        LocalDate endDate = rewards.get().getEndDate();
        int nominationsAllowed = rewards.get().getNominationsAllowed();
        List<RewardsCriteria> criteria = rewards.get().getCriteria();
        if(rewardsRepository.findEditRollOutId(id)== 0){
            if(rewards.get().getRewardName().contains("for ")) {
                String rewardName = rewards.get().getRewardName().substring(0, rewards.get().getRewardName().indexOf("for ")-1);

                return new ResponseEntity(new ListRollOutEdit(rewardId,rewardName,coins,frequency,description,endDate,nominationsAllowed, criteria),HttpStatus.OK);
            }
            else{
                String rewardName = rewards.get().getRewardName();
                return new ResponseEntity(new ListRollOutEdit(rewardId,rewardName,coins,frequency,description,endDate,nominationsAllowed, criteria),HttpStatus.OK);
            }
            }
        else{
            String rewardName= rewards.get().getRewardName();
            return new ResponseEntity(new ListRollOutEdit(rewardId,rewardName,coins,frequency,description,endDate,nominationsAllowed, criteria),HttpStatus.OK);
        }
    }

    public ResponseEntity<Rewards> rollOutUpdate(Long id, Rewards reward){
        if(rewardsRepository.findEditRollOutId(id)==0 && rewardsRepository.checkingRewardInRolledOut(id)==0)
        {
            rewardsSave(reward);
            rewardsRepository.regenerationCancel(id);
            rewardsRepository.updateRolledOutColumn(id,reward.getId());
            rewardsRepository.updateRolledOutEditAwardStatus(reward.getId());
            return new ResponseEntity<>(reward,HttpStatus.OK);
        }
        else if(rewardsRepository.findEditRollOutId(id)==0 && rewardsRepository.checkingRewardInRolledOut(id)>0){
            return Update(id,reward);
        }
        return null;
    }


}



