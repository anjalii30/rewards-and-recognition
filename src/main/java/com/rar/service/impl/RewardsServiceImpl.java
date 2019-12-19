package com.rar.service.impl;

import com.rar.enums.FrequencyEnum;
import com.rar.model.Rewards;
import com.rar.model.RewardsCriteria;
import com.rar.repository.*;
import com.rar.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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
    public List<Rewards> findAll() {

        return (List<Rewards>) rewardsRepository.getAll();
    }

    @Override
    public void deleteById(long id) {

        rewardsRepository.deleteById(id);
    }

    @Override
    public Optional<Rewards> findById(Long id) {
        return rewardsRepository.findById(id);
    }

    @Override
    public Rewards Update(Long id, Rewards createReward) {
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

        Rewards rewardData1 =  rewardsRepository.save(CreateReward1);



            for(Iterator<RewardsCriteria> itttt = CreateReward1.getCriteria().iterator(); itttt.hasNext();) {
                RewardsCriteria ff = itttt.next();

             rewardsCriteriaRepository.deleteById(ff.getRewardId(),ff.getCriteriaId());
            }

        RewardsCriteria rewardsCriteria;

        for (int i = 0; i < createReward.getCriteria().size(); i++) {
            rewardsCriteria = new RewardsCriteria();

            rewardsCriteria.setRewardId(id);
            rewardsCriteria.setCriteriaId(createReward.getCriteria().get(i).getCriteriaId());
            rewardsCriteria.setCompulsory(createReward.getCriteria().get(i).getCompulsory());
            rewardsCriteriaRepository.insertById(createReward.getCriteria().get(i).getCriteriaId(),id,createReward.getCriteria().get(i).getCompulsory());

        }

        return rewardData1;
    }

    @Override
    public ResponseEntity<Rewards> updateAwardStatus(Long id, Rewards createReward) throws IOException, MessagingException, com.sun.xml.messaging.saaj.packaging.mime.MessagingException {

        LocalDate today = LocalDate.now();
      // ResponseEntity t= utcDate.dateToday(today);
       // Instant today = Instant.now();


        Rewards CreateReward1 = rewardsRepository.findById(id).get();
        CreateReward1.setRewardName(CreateReward1.getRewardName());
        CreateReward1.setFrequency(CreateReward1.getFrequency());
        CreateReward1.setDescription(CreateReward1.getDescription());
        CreateReward1.setDiscontinuingDate(createReward.getDiscontinuingDate());
        CreateReward1.setDiscontinuingReason(createReward.getDiscontinuingReason());
        CreateReward1.setSelfNominate(CreateReward1.isSelfNominate());
        CreateReward1.setNominationsAllowed(CreateReward1.getNominationsAllowed());
        CreateReward1.setAwardStatus(createReward.getAwardStatus());
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

        if(createReward.getAwardStatus()==1) {

            String reward_name = rewardsRepository.getRewardName(id);
            String[] emails=managerRepository.getAllEmails();

            for (int i = 0; i < emails.length; i++) {
                String name=userRepository.getName(emails[i]);
                System.out.println(emails[i]);
                sendEmail.sendEmailWithoutAttachment(emails[i], "New Reward rolled out",
                        "Hello, " + name + ". A new reward " +reward_name.toUpperCase() + " has been rolled out. Go, check it out and nominate..!!");
            }
        }
        else
            if(createReward.getAwardStatus()==3){

                String reward_name = rewardsRepository.getRewardName(id);
                String reason=createReward.getDiscontinuingReason();
                String[] emails=managerRepository.getAllEmails();
                System.out.println(id);
                System.out.println(reward_name);
                for (int i = 0; i < emails.length; i++) {
                    String name=userRepository.getName(emails[i]);
                    System.out.println(emails[i]);
                    sendEmail.sendEmailWithoutAttachment(emails[i], "Reward discontinued",
                            "Hello, " + name + ". " +reward_name.toUpperCase() + " has been discontinued because "+reason);
                }
            }
        return ResponseEntity.ok(update);

    }


    public List<Rewards> latest(String email){

        Long user_id = userRepository.getIdByEmail(email);
        return rewardsRepository.latest(user_id);
    }

    @Override
    public List<Rewards> managerApprovalRewards(String email) {

        List<Rewards> rewards= null;
        Long manager_id = managerRepository.findByEmail(email);
        if(manager_id!=null) {
            rewards=rewardsRepository.managerApprovalRewards();
        }
        else
        {
            rewards= new ArrayList<Rewards>();
        }
        return rewards;


        }



    @Override
    public List<Rewards> findByRolled(String email) {

        List<Rewards> rewards = null;
        Long manager_id = managerRepository.findByEmail(email);
        Long user_id = userRepository.getIdByEmail(email);
        if(manager_id!=null) {
          //  Long[] members = managerRepository.getMembers(manager_id);
            Long[] projects=managerRepository.getProjectsOfManager(manager_id);

            for (int i = 0; i < projects.length; i++) {
                rewards=rewardsRepository.findByRolled(projects[i]);
              //  rewards = rewardsRepository.findByRolled(members[i]);

            }
        }
        else{
            rewards= rewardsRepository.findByRolledForEmp(user_id);
        }
        return rewards;

    }

    public ResponseEntity rewardsSave(Rewards rewards) {


      /*  String month = monthName[cal.get(rewards.getStart_date().getMonthValue())];
        System.out.println(month);
         String year = String.valueOf(cal.get(rewards.getStart_date().getYear()));
         System.out.println(year+month);*/

            if (rewards.getFrequency() == FrequencyEnum.Annually)

                rewards.setRewardName(rewards.getRewardName() + " for " + year);

            else
                rewards.setRewardName(rewards.getRewardName() + " for " + month + " " + year);

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
            s.put("criteria", rewardsCriteria);
            s.put("rewards", rewards);
            return new ResponseEntity<>(s,HttpStatus.OK);
    }

    public Optional<Rewards> rollOutListReward(long id){
        if(rewardsRepository.findEditRollOutId(id)!= 0)
           return rewardsRepository.findById(rewardsRepository.findEditRollOutId(id));
        else
           return rewardsRepository.findById(id);
    }

    public Rewards rollOutUpdate(Long id, Rewards reward){
        if(rewardsRepository.findEditRollOutId(id)==0 && rewardsRepository.checkingRewardInRolledOut(id)==0)
        {
            rewardsSave(reward);
            System.out.print(reward.getId());
            rewardsRepository.regenerationCancel(id);
            rewardsRepository.updateRolledOutColumn(id,reward.getId());
            rewardsRepository.updateRolledOutEditAwardStatus(reward.getId());
            return reward;
        }
        else if(rewardsRepository.findEditRollOutId(id)==0 && rewardsRepository.checkingRewardInRolledOut(id)>0){
            return Update(id,reward);
        }
        return null;
    }


}



