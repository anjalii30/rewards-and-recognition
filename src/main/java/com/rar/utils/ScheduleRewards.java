package com.rar.utils;

import com.rar.enums.FrequencyEnum;
import com.rar.model.Rewards;
import com.rar.model.RewardsCriteria;
import com.rar.repository.RewardsCriteriaRepository;
import com.rar.repository.RewardsRepository;
import com.rar.service.RewardsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;


@Component
@Service
public class ScheduleRewards {

    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private RewardsCriteriaRepository rewardsCriteriaRepository;

    @Autowired
    private RewardsService rewardsService;


    private Rewards rewards;

    String[] monthName = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};


    //Checking for regeneration of  monthly reward whose end date has passed daily at 12 a.m.
    @Scheduled(cron = "0 0 0 1/1 * ? ")
    //@Scheduled(cron="0 0/1 * 1/1 * ?")
    public void scheduleMonthly(){


        ArrayList<Rewards> list = (ArrayList<Rewards>) rewardsRepository.findAll();

        int i=0;

        while(i<list.size()){
            Rewards old_reward = list.get(i);

            // Get the two dates to be compared
            LocalDate d1 =old_reward.getEnd_date();
            LocalDate d2 = LocalDate.now();

            Calendar cal = Calendar.getInstance();
            String month = monthName[cal.get(Calendar.MONTH)];
            String month1=monthName[cal.get(Calendar.MONTH)+1];
            String currentYear = String.valueOf(cal.get(Calendar.YEAR));
            String year =String.valueOf(cal.get(Calendar.YEAR)-1);





            if (old_reward.getFrequency() == FrequencyEnum.Monthly && d2.isAfter(d1) && old_reward.isRegenerated()==true) {

                rewardsRepository.updateToNull(old_reward.getRewardId());

                Rewards new_reward = new Rewards();

                String rName1 = old_reward.getReward_name();

              /*  String replaceString=str;
                        replaceString=rName1.replaceFirst(month,month1);
                 replaceString=rName1.replaceFirst(year,currentYear);*/

                String replaceString = rName1.replaceFirst(month,month1).replaceFirst(year,currentYear);



                new_reward.setReward_name(replaceString);
                new_reward.setNominations_allowed(old_reward.getNominations_allowed());
                new_reward.setSelf_nominate(old_reward.isSelf_nominate());
                new_reward.setFrequency(old_reward.getFrequency());
                new_reward.setDescription(old_reward.getDescription());
                new_reward.setCategory(old_reward.getCategory());
                new_reward.setRegenerated(true);
                new_reward.setStart_date(old_reward.getEnd_date());
                LocalDate d3 = new_reward.getStart_date();
                LocalDate ed=d3.plusMonths(1);
                new_reward.setEnd_date(ed);
                new_reward.setAward_status(0);

                rewardsService.save(new_reward);

                Set<RewardsCriteria> criterias= rewardsCriteriaRepository.findByRewardId(old_reward.getId());

                for (Iterator<RewardsCriteria> it = criterias.iterator(); it.hasNext(); ) {
                    RewardsCriteria f = it.next();

                    RewardsCriteria rewardsCriteria = new RewardsCriteria();
                    rewardsCriteria.setRewardId(new_reward.getId());
                    rewardsCriteria.setCriteriaId(f.getCriteriaId());
                    rewardsCriteria.setCompulsory(f.getCompulsory());

                    rewardsCriteriaRepository.save(rewardsCriteria);

                }

            }

            i++;

        }
    }


    //Regenerating quarterly rewards starting from 1st of the month{jan, april, july, october} at 12 a.m.
    //Checking for regeneration of  monthly reward whose end date has passed every sunday at 12 a.m.
    @Scheduled(cron = "0 0 0 ? * SUN ")
    //@Scheduled(cron="0 0/2 * 1/1 * ?")
    public void scheduleQuarterly() {

        ArrayList<Rewards> list = (ArrayList<Rewards>) rewardsRepository.findAll();

        int i=0;

        while(i<list.size()){
            Rewards old_reward = list.get(i);

            // Get the two dates to be compared
            LocalDate d1 =old_reward.getEnd_date();
            LocalDate d2 = LocalDate.now();

            Calendar cal = Calendar.getInstance();
            String month = monthName[cal.get(Calendar.MONTH)];
            String month1=monthName[cal.get(Calendar.MONTH)+4];
            String currentYear = String.valueOf(cal.get(Calendar.YEAR));
            String year =String.valueOf(cal.get(Calendar.YEAR)-1);




            if (old_reward.getFrequency() == FrequencyEnum.Quarterly && d2.isAfter(d1) && old_reward.isRegenerated()) {

                rewardsRepository.updateToNull(old_reward.getRewardId());

                Rewards new_reward = new Rewards();

                String rName1 = old_reward.getReward_name();

                String replaceString = rName1.replaceFirst(month,month1).replaceFirst(year,currentYear);


              //  String replaceString=rName1.replaceFirst(month,month1);


                new_reward.setReward_name(replaceString);
                new_reward.setNominations_allowed(old_reward.getNominations_allowed());
                new_reward.setSelf_nominate(old_reward.isSelf_nominate());
                new_reward.setFrequency(old_reward.getFrequency());
                new_reward.setDescription(old_reward.getDescription());
                new_reward.setCategory(old_reward.getCategory());
                new_reward.setRegenerated(true);
                new_reward.setStart_date(old_reward.getEnd_date());
                LocalDate d3 = new_reward.getStart_date();
                LocalDate ed=d3.plusMonths(4);
                new_reward.setEnd_date(ed);
                new_reward.setAward_status(0);


                rewardsService.save(new_reward);

                Set<RewardsCriteria> criterias= rewardsCriteriaRepository.findByRewardId(old_reward.getId());


                for (Iterator<RewardsCriteria> it = criterias.iterator(); it.hasNext(); ) {
                    RewardsCriteria f = it.next();

                    RewardsCriteria rewardsCriteria = new RewardsCriteria();
                    rewardsCriteria.setRewardId(new_reward.getId());
                    rewardsCriteria.setCriteriaId(f.getCriteriaId());
                    rewardsCriteria.setCompulsory(f.getCompulsory());

                    rewardsCriteriaRepository.save(rewardsCriteria);

                }
            }

            i++;
        }
    }


    //Regenerating yearly rewards starting from the 1st of every year 12 a.m.
    //Checking for regenration of  monthly reward whose end date has passed every 1st of month at 12 a.m.
    @Scheduled(cron = "0 0 12 1 1/1 ? ")
    //  @Scheduled(cron="0 0/3 * 1/1 * ?")

    public void scheduleYearly(){


        ArrayList<Rewards> list = (ArrayList<Rewards>) rewardsRepository.findAll();

        int i=0;

        while(i<list.size()){
            Rewards old_reward = list.get(i);

            // Get the two dates to be compared
            LocalDate d1 =old_reward.getEnd_date();
            LocalDate d2 = LocalDate.now();

            Calendar cal = Calendar.getInstance();
            String year = String.valueOf(cal.get(Calendar.YEAR));
            String year1=String.valueOf(cal.get(Calendar.YEAR)+1);



            if (old_reward.getFrequency() == FrequencyEnum.Annually && d2.isAfter(d1) && old_reward.isRegenerated()==true) {

                rewardsRepository.updateToNull(old_reward.getRewardId());

                Rewards new_reward = new Rewards();

                String rName1 = old_reward.getReward_name();

                String replaceString=rName1.replace(year,year1);


                new_reward.setReward_name(replaceString);
                new_reward.setNominations_allowed(old_reward.getNominations_allowed());
                new_reward.setSelf_nominate(old_reward.isSelf_nominate());
                new_reward.setFrequency(old_reward.getFrequency());
                new_reward.setDescription(old_reward.getDescription());
                new_reward.setCategory(old_reward.getCategory());
                new_reward.setRegenerated(true);
                new_reward.setStart_date(old_reward.getEnd_date());
                LocalDate d3 = new_reward.getStart_date();
                LocalDate ed=d3.plusYears(1);
                new_reward.setEnd_date(ed);
                new_reward.setAward_status(0);


                rewardsService.save(new_reward);


                Set<RewardsCriteria> criterias= rewardsCriteriaRepository.findByRewardId(old_reward.getId());

                for (Iterator<RewardsCriteria> it = criterias.iterator(); it.hasNext(); ) {
                    RewardsCriteria f = it.next();

                    RewardsCriteria rewardsCriteria = new RewardsCriteria();
                    rewardsCriteria.setRewardId(new_reward.getId());
                    rewardsCriteria.setCriteriaId(f.getCriteriaId());
                    rewardsCriteria.setCompulsory(f.getCompulsory());

                    rewardsCriteriaRepository.save(rewardsCriteria);

                }
            }

            i++;
        }

    }
}



