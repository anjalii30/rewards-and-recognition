package com.rar.utils;

import com.rar.enums.FrequencyEnum;
import com.rar.model.Rewards;
import com.rar.model.RewardsCriteria;
import com.rar.repository.RewardsCriteriaRepository;
import com.rar.repository.RewardsRepository;
import com.rar.service.NotificationsService;
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

import static com.rar.utils.Constants.*;


@Component
@Service
public class ScheduleRewards {

    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private RewardsCriteriaRepository rewardsCriteriaRepository;

    @Autowired
    private RewardsService rewardsService;

    @Autowired
    private NotificationsService notificationsService;


    String[] monthName = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};


    LocalDate today = LocalDate.now();
    Calendar cal = Calendar.getInstance();
    String month=monthName[cal.get(Calendar.MONTH)];
    String year = String.valueOf(cal.get(Calendar.YEAR));


    public void commonCode(String updatedMonth,FrequencyEnum frequencyEnum) {
        String previousMonth;
        String previousYear;
        String replaceString = null;
        LocalDate newRewardEndDate = null;

        ArrayList<Rewards> rewards = (ArrayList<Rewards>) rewardsRepository.findAll();
        int i = 0;

        while (i < rewards.size()) {
            Rewards oldReward = rewards.get(i);
            String oldRewardName = oldReward.getRewardName();
            LocalDate oldRewardEndDate = oldReward.getEndDate();

            if (oldReward.getFrequency() == frequencyEnum && today.isAfter(oldRewardEndDate) && oldReward.isRegenerated()) {

                String[] arrOfStr = oldRewardName.split(" ");
                previousMonth=arrOfStr[arrOfStr.length-2];
                previousYear=arrOfStr[arrOfStr.length-1];

                rewardsRepository.updateToNull(oldReward.getRewardId());

                Rewards newReward = new Rewards();

                newReward.setStartDate(oldReward.getEndDate());
                LocalDate newRewardStartDate = newReward.getStartDate();
                String updatedYear= String.valueOf(newRewardStartDate.getYear());
                String updatedName = oldRewardName.replaceFirst(previousMonth, updatedMonth).replaceFirst(previousYear, updatedYear);

                if(frequencyEnum==FrequencyEnum.MONTHLY) {
                    replaceString = updatedName;
                    newRewardEndDate=newRewardStartDate.plusMonths(1);
                } else
                if(frequencyEnum==FrequencyEnum.QUARTERLY){
                    replaceString = updatedName;
                    newRewardEndDate=newRewardStartDate.plusMonths(4);
                } else
                if(frequencyEnum==FrequencyEnum.ANNUALLY) {
                    replaceString = oldRewardName.replace(year,updatedYear);
                    newRewardEndDate = newRewardStartDate.plusYears(1);
                }
                newReward.setRewardName(replaceString);
                newReward.setNominationsAllowed(oldReward.getNominationsAllowed());
                newReward.setSelfNominate(oldReward.isSelfNominate());
                newReward.setFrequency(oldReward.getFrequency());
                newReward.setDescription(oldReward.getDescription());
                newReward.setCategory(oldReward.getCategory());
                newReward.setRegenerated(true);
                newReward.setEndDate(newRewardEndDate);
                newReward.setAwardStatus(CREATED);

                rewardsService.save(newReward);

                Set<RewardsCriteria> criteria= rewardsCriteriaRepository.findByRewardId(oldReward.getRewardId());
                for (Iterator<RewardsCriteria> iterator = criteria.iterator(); iterator.hasNext(); ) {
                    RewardsCriteria next = iterator.next();
                    RewardsCriteria rewardsCriteria = new RewardsCriteria();
                    rewardsCriteria.setRewardId(newReward.getRewardId());
                    rewardsCriteria.setCriteriaId(next.getCriteriaId());
                    rewardsCriteria.setCompulsory(next.getCompulsory());
                    rewardsCriteriaRepository.save(rewardsCriteria);
                }
            }
            i++;
        }
    }

    //@Scheduled(cron="0 * * ? * *")
      @Scheduled(cron = "0 0 0 1/1 * ? ")
    public void scheduleMonthly() {

        String updatedMonth=monthName[(cal.get(Calendar.MONTH)+1)%12];
        commonCode(updatedMonth,FrequencyEnum.MONTHLY);

    }


    //Regenerating quarterly rewards starting from 1st of the month{jan, april, july, october} at 12 a.m.
    //Checking for regeneration of  monthly reward whose end date has passed every sunday at 12 a.m.
    @Scheduled(cron = "0 0 0 ? * SUN ")
    public void scheduleQuarterly() {


        String updateMonth=monthName[((cal.get(Calendar.MONTH)+4)%12)];
        commonCode(updateMonth,FrequencyEnum.QUARTERLY);

    }


    //Regenerating yearly rewards starting from the 1st of every year 12 a.m.
    //Checking for regeneration of  monthly reward whose end date has passed every 1st of month at 12 a.m.
    @Scheduled(cron = "0 0 12 1 1/1 ? ")

    public void scheduleYearly(){


        String updatedYear=String.valueOf(cal.get(Calendar.YEAR)+1);
        commonCode("month",FrequencyEnum.ANNUALLY);


    }
    //Checking everyday at 9 a.m. to Roll out after reward has been edited after roll out
    @Scheduled(cron = "0 0 9 * * ?  ")
//    @Scheduled(cron = "0 * * ? * * ")
    public void editAfterRollOut(){
        ArrayList<Rewards> rewards = (ArrayList<Rewards>) rewardsRepository.findAll();

        for(int i=0;i<rewards.size();i++){
            Long rewardId=rewards.get(i).getRewardId();
            int awardStatus=rewards.get(i).getAwardStatus();
            LocalDate startDate=rewards.get(i).getStartDate();
            if(rewardsRepository.checkingRewardInRolledOut(rewardId)>0 && awardStatus==EDITED_AFTER_ROLL_OUT
                    && startDate.equals(today)){
                rewardsRepository.updateAwardStatus(ROLLED_OUT,rewardId);

                if(rewards.get(i).getFrequency()==FrequencyEnum.MONTHLY){
                    rewardsRepository.updateEndDateRolledOutEdit(rewardId,today.plusMonths(1));
                    rewardsRepository.updateRewardName(rewards.get(i).getRewardId(),rewards.get(i).getRewardName() + FOR + month + " " + year);
                }
                else
                if(rewards.get(i).getFrequency()==FrequencyEnum.QUARTERLY){
                    rewardsRepository.updateEndDateRolledOutEdit(rewardId,today.plusMonths(4));
                    rewardsRepository.updateRewardName(rewards.get(i).getRewardId(),rewards.get(i).getRewardName() + FOR + month + " " + year);
                }
                else
                if(rewards.get(i).getFrequency()==FrequencyEnum.ANNUALLY){
                    rewardsRepository.updateEndDateRolledOutEdit(rewardId,today.plusYears(1));
                    rewardsRepository.updateRewardName(rewards.get(i).getRewardId(),rewards.get(i).getRewardName() + FOR + year);
                }
            }
        }
    }

    //set award status 2 where end date is passed everyday at 9 am
    @Scheduled(cron = "0 0 9 * * ?  ")
    //   @Scheduled(cron="0 * * ? * *")
    public void endDatePassed(){
        ArrayList<Rewards> rewards = (ArrayList<Rewards>) rewardsRepository.findAll();

        for(int i=0;i<rewards.size();i++) {

            Long rewardId = rewards.get(i).getRewardId();
            LocalDate endDate=rewards.get(i).getEndDate();
            if( endDate.isEqual(today)&& rewards.get(i).getAwardStatus()==ROLLED_OUT) {
                rewardsRepository.updateAwardStatus(END_DATE_PASSED, rewardId);
                notificationsService.endDatePassed(rewardId);
            }
        }
    }
}



