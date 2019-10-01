package com.rar.service.impl;


import com.rar.enums.FrequencyEnum;
import com.rar.model.Criterias;
import com.rar.model.Rewards;
import com.rar.model.RewardsCriteria;
import com.rar.repository.RewardsCriteriasRepository;
import com.rar.repository.RewardsRepository;
import com.rar.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private RewardsCriteriasRepository rewardsCriteriasRepository;



    String[] monthName = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};

    Calendar cal = Calendar.getInstance();
    String month = monthName[cal.get(Calendar.MONTH)];

    String year = String.valueOf(cal.get(Calendar.YEAR));


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

    @Override
    public List<Criterias> giveCriterias(Long id){
        return rewardsRepository.giveCriterias(id);
    }

    @Override
    public List<Rewards> findByDiscontinued() {
        return rewardsRepository.findByDiscontinued();
    }

    @Override
    public List<Rewards> findByNominationClosed() {
        return rewardsRepository.findByNominationClosed();
    }

    @Override
    public List<Rewards> findByRolled() {
        return rewardsRepository.findByRolled();
    }


    @Override
    public Rewards Update(Long id, Rewards createreward) {
        Rewards Createaward1 = rewardsRepository.findById(id).get();
        Createaward1.setReward_name(createreward.getReward_name());
        Createaward1.setFrequency(createreward.getFrequency());
        Createaward1.setDescription(createreward.getDescription());
        Createaward1.setStart_date(createreward.getStart_date());
        Createaward1.setEnd_date(createreward.getEnd_date());
        Createaward1.setAward_status(createreward.getAward_status());
        Createaward1.setDiscontinuingDate(createreward.getDiscontinuingDate());
        Createaward1.setDiscontinuingReason(createreward.getDiscontinuingReason());
        Createaward1.setSelf_nominate(createreward.isSelf_nominate());
        Createaward1.setNominations_allowed(createreward.getNominations_allowed());

        Rewards update = rewardsRepository.save(Createaward1);
        return update;
    }

    @Override
    public Rewards updateAwardStatus(Long id, Rewards createreward) {
        Rewards Createaward1 = rewardsRepository.findById(id).get();
        Createaward1.setReward_name(Createaward1.getReward_name());
        Createaward1.setFrequency(Createaward1.getFrequency());
        Createaward1.setDescription(Createaward1.getDescription());
        Createaward1.setStart_date(Createaward1.getStart_date());
        Createaward1.setEnd_date(Createaward1.getEnd_date());
        Createaward1.setDiscontinuingDate(Createaward1.getDiscontinuingDate());
        Createaward1.setDiscontinuingReason(Createaward1.getDiscontinuingReason());
        Createaward1.setSelf_nominate(Createaward1.isSelf_nominate());
        Createaward1.setNominations_allowed(Createaward1.getNominations_allowed());
        Createaward1.setAward_status(createreward.getAward_status());

        Rewards update = rewardsRepository.save(Createaward1);
        return update;
    }


    @Override
    public Rewards discontinuing(Long id, Rewards createreward) {
        Rewards Createaward1 = rewardsRepository.findById(id).get();
        Createaward1.setReward_name(Createaward1.getReward_name());
        Createaward1.setFrequency(Createaward1.getFrequency());
        Createaward1.setDescription(Createaward1.getDescription());
        Createaward1.setStart_date(Createaward1.getStart_date());
        Createaward1.setEnd_date(Createaward1.getEnd_date());
        Createaward1.setDiscontinuingDate(createreward.getDiscontinuingDate());
        Createaward1.setDiscontinuingReason(createreward.getDiscontinuingReason());
        Createaward1.setSelf_nominate(Createaward1.isSelf_nominate());
        Createaward1.setNominations_allowed(Createaward1.getNominations_allowed());
        Createaward1.setAward_status(createreward.getAward_status());

        Rewards update = rewardsRepository.save(Createaward1);
        return update;
    }

    public ResponseEntity<?> rewardsSave(Rewards rewards) {



        if(rewards.getFrequency()== FrequencyEnum.Annually)
            rewards.setReward_name(rewards.getReward_name()+" for year " + year);

        else
            rewards.setReward_name(rewards.getReward_name()+" for month " + month);

        Rewards rewardData= save(rewards);

        long id = rewards.getId();

        RewardsCriteria rewardsCriteria =new RewardsCriteria();
        System.out.println(rewards.getCriteria().size());

        for(int i = 0; i<rewards.getCriteria().size(); i++){
            rewardsCriteria = new RewardsCriteria();

            rewardsCriteria.setRewardId(id);
            rewardsCriteria.setCriteriaId(rewards.getCriteria().get(i).getCriteriaId());
            rewardsCriteria.setCompulsory(rewards.getCriteria().get(i).getCompulsory());

            rewardsCriteriasRepository.save(rewardsCriteria);
        }

        HashMap<String,Object> s=new HashMap<>();
        s.put("criterias", rewardsCriteria);
        s.put("rewards",rewards);
        Object returnValue=s;
        return ResponseEntity.ok(s);
    }
/*

    @Override
    public Rewards function(Rewards reward) {
        Rewards rewardData = save(reward);
        System.out.println("Reward:" + reward.getId());
*/





//        RewardsCriteriaId rewardsCriteriasId = new RewardsCriteriaId();
//        System.out.println(reward.getCriterias().size());
//
///*        for(int i=0;i<reward.getCriterias().size();i++){
//            rewardsCriterias = new RewardsCriteria();
//
//            rewardsCriterias.setCriterias(reward.getCriterias());
//
//        }*/
//        RewardsCriteria rewardsCriterias;
//
//        long id = reward.getId();
//
//        for (Iterator<RewardsCriteria> it = reward.getCriterias().iterator(); it.hasNext(); ) {
//            RewardsCriteria f = it.next();
//            RewardsCriteria d = new RewardsCriteria();
////            rewardsCriteriasId = new RewardsCriteriaId();
//            System.out.println("Reward:" + f.getCriterias().getCriteriaId());
//
//            Criterias c = f.getCriterias();
//            Rewards r = f.getRewards();
//            Boolean b = f.getCompulsory();
//
//             new RewardsCriteria(r, c, b);

//            d.setCriterias(f.getCriterias());


//            rewardsCriteriasRepository.save(d);


//        }


//
//        RewardsCriteria rewardsCriterias = new RewardsCriteria();

        /*for (long i = 0; i < reward.getCriterias().size(); i++) {
            rewardsCriterias = new RewardsCriteria();


            rewardsCriterias.setRewardId(id);
            rewardsCriterias.setCriteriaId(reward.getCriterias().getClass().get);
        }*/
//
////        Set<Criterias> rewardsCriterias1=reward.getCriterias();
////        rewardsCriterias.setRewardId(reward.getId());
//////        rewardsCriterias.setCriteriaId(rewardsCriterias1.);
////        System.out.println(" "+rewardsCriterias1.getClass());
        }

//            return save(reward);


