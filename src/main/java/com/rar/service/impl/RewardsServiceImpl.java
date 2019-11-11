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




    private String[] monthName = {"January", "February",
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

        return (List<Rewards>) rewardsRepository.findAll();
    }

    @Override
    public void deleteById(long id) {

        rewardsRepository.deleteById(id);
    }

    @Override
    public Optional<Rewards> findById(Long id) {
        return rewardsRepository.findById(id);
    }
/*
    @Override
    public List<Criteria> getCriteria(Long id){

        return rewardsRepository.getCriteria(id);
    }*/

    /*@Override
    public List<Rewards> findByDiscontinued() {
        return rewardsRepository.findByDiscontinued();
    }

    @Override
    public List<Rewards> findByNominationClosed() {
        return rewardsRepository.findByNominationClosed();
    }*/




    @Override
    public Rewards Update(Long id, Rewards createReward) {
        Rewards CreateReward1 = rewardsRepository.findById(id).get();
        CreateReward1.setReward_name(createReward.getReward_name());
        CreateReward1.setFrequency(createReward.getFrequency());
        CreateReward1.setDescription(createReward.getDescription());
        CreateReward1.setStart_date(createReward.getStart_date());
        CreateReward1.setEnd_date(createReward.getEnd_date());
        CreateReward1.setAward_status(createReward.getAward_status());
        CreateReward1.setDiscontinuingDate(createReward.getDiscontinuingDate());
        CreateReward1.setDiscontinuingReason(createReward.getDiscontinuingReason());
        CreateReward1.setSelf_nominate(createReward.isSelf_nominate());
        CreateReward1.setNominations_allowed(createReward.getNominations_allowed());
        /*CreateReward1.setCriteria(createReward.getCriteria());*/
        CreateReward1.setCategory(createReward.getCategory());
        CreateReward1.setRegenerated(CreateReward1.isRegenerated());

       /* Iterator<Designation> itt= userInfo1.getDesignation().iterator();*/
        Iterator<RewardsCriteria> it=CreateReward1.getCriteria().iterator();
/*

        for(Iterator<RewardsCriteria> itttt = CreateReward1.getCriteria().iterator(); itttt.hasNext();){
            RewardsCriteria ff = itttt.next();
            for (Iterator<RewardsCriteria> ittt = createReward.getCriteria().iterator(); ittt.hasNext(); ) {
                RewardsCriteria f = ittt.next();
                if(ff.getRewardId() == f.getRewardId() && ff.getCriteriaId() == f.getCriteriaId())
                    break;
                else
                {
                    System.out.println(ff.getRewardId());
                    System.out.println(ff.getCriteriaId());
                    rewardsCriteriaRepository.deleteById(ff.getRewardId(),ff.getCriteriaId());
                }}}
*/



       /* for (Iterator<RewardsCriteria> ittt = createReward.getCriteria().iterator(); ittt.hasNext(); ) {
            RewardsCriteria f = ittt.next();*/
            for(Iterator<RewardsCriteria> itttt = CreateReward1.getCriteria().iterator(); itttt.hasNext();) {
                RewardsCriteria ff = itttt.next();
                System.out.println(ff.getRewardId());
                System.out.println(ff.getCriteriaId());
             rewardsCriteriaRepository.deleteById(ff.getRewardId(),ff.getCriteriaId());
            }
        for (Iterator<RewardsCriteria> ittt = createReward.getCriteria().iterator(); ittt.hasNext(); ) {
            RewardsCriteria f = ittt.next();
            RewardsCriteria rewardsCriteria = new RewardsCriteria();
            rewardsCriteria.setRewardId(f.getRewardId());
            rewardsCriteria.setCriteriaId(f.getCriteriaId());
            rewardsCriteria.setCompulsory(f.getCompulsory());
            rewardsCriteria.setCriterias(f.getCriterias());
            rewardsCriteriaRepository.save(rewardsCriteria);


                /*if(f.getRewardId() == ff.getRewardId() && f.getCriteriaId() == ff.getCriteriaId()){

                    Iterator<RewardsCriteria> criteriaIterator = rewardsCriteriaRepository.findById(f.getRewardId(),f.getCriteriaId()).iterator();
                    RewardsCriteria criteria=criteriaIterator.next();

                    criteria.setRewardId(ff.getRewardId());
                    criteria.setCriteriaId(f.getCriteriaId());
                    criteria.setCompulsory(f.getCompulsory());
                    criteria.setCriterias(f.getCriterias());

                *//*    ff.setRewardId(f.getRewardId());
                    ff.setCriteriaId(f.getCriteriaId());
                    ff.setCompulsory(f.getCompulsory());
*//*
                    rewardsCriteriaRepository.save(criteria);


                }
                else if(f.getRewardId() == ff.getRewardId() && f.getCriteriaId() != ff.getCriteriaId())
                {
                    RewardsCriteria rewardsCriteria = new RewardsCriteria();

                    rewardsCriteria.setRewardId(f.getRewardId());
                    rewardsCriteria.setCriteriaId(f.getCriteriaId());
                    rewardsCriteria.setCompulsory(f.getCompulsory());
                    rewardsCriteria.setCriterias(f.getCriterias());

                  *//*  ff.setRewardId(f.getRewardId());
                    ff.setCriteriaId(f.getCriteriaId());
                    ff.setCompulsory(f.getCompulsory());
*//*
                    rewardsCriteriaRepository.save(rewardsCriteria);



                }*/
                /*else
                {
                    System.out.println(ff.getRewardId());
                    System.out.println(ff.getCriteriaId());
                    rewardsCriteriaRepository.deleteById(ff.getRewardId(),ff.getCriteriaId());
                }*/



        }
        /*CreateReward1.setCriteria(i);*/
        return rewardsRepository.save(CreateReward1);
    }

    @Override
    public Rewards updateAwardStatus(Long id, Rewards createReward) {
        Rewards CreateReward1 = rewardsRepository.findById(id).get();
        CreateReward1.setReward_name(CreateReward1.getReward_name());
        CreateReward1.setFrequency(CreateReward1.getFrequency());
        CreateReward1.setDescription(CreateReward1.getDescription());
        CreateReward1.setStart_date(CreateReward1.getStart_date());
        CreateReward1.setEnd_date(CreateReward1.getEnd_date());
        CreateReward1.setDiscontinuingDate(CreateReward1.getDiscontinuingDate());
        CreateReward1.setDiscontinuingReason(CreateReward1.getDiscontinuingReason());
        CreateReward1.setSelf_nominate(CreateReward1.isSelf_nominate());
        CreateReward1.setNominations_allowed(CreateReward1.getNominations_allowed());
        CreateReward1.setAward_status(createReward.getAward_status());

        Rewards update = rewardsRepository.save(CreateReward1);
        return update;
    }


    @Override
    public Rewards discontinuing(Long id, Rewards createReward) {
        Rewards CreateReward1 = rewardsRepository.findById(id).get();
        CreateReward1.setReward_name(CreateReward1.getReward_name());
        CreateReward1.setFrequency(CreateReward1.getFrequency());
        CreateReward1.setDescription(CreateReward1.getDescription());
        CreateReward1.setStart_date(CreateReward1.getStart_date());
        CreateReward1.setEnd_date(CreateReward1.getEnd_date());
        CreateReward1.setDiscontinuingDate(createReward.getDiscontinuingDate());
        CreateReward1.setDiscontinuingReason(createReward.getDiscontinuingReason());
        CreateReward1.setSelf_nominate(CreateReward1.isSelf_nominate());
        CreateReward1.setNominations_allowed(CreateReward1.getNominations_allowed());
        CreateReward1.setAward_status(createReward.getAward_status());

        Rewards update = rewardsRepository.save(CreateReward1);
        return update;
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
    public List<Rewards> findByRolled(String email) throws Exception {

        List<Rewards> rewards = null;
        Long manager_id = managerRepository.findByEmail(email);
        Long user_id = userRepository.getIdByEmail(email);
        if(manager_id!=null) {
            Long[] members = managerRepository.getMembers(manager_id);

            for (int i = 0; i < members.length; i++) {
                rewards = rewardsRepository.findByRolled(members[i]);

            }
        }
        else{
            rewards= rewardsRepository.findByRolledForEmp(user_id);
        }
        return rewards;


    }

    public ResponseEntity rewardsSave(Rewards rewards) {



            if (rewards.getFrequency() == FrequencyEnum.Annually)
                rewards.setReward_name(rewards.getReward_name() + " for " + year);

            else
                rewards.setReward_name(rewards.getReward_name() + " for " + month + " " + year);

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


        }



