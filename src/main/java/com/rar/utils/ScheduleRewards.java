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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private Rewards rewards;

    String[] monthName = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};

    SimpleDateFormat df=new SimpleDateFormat("yyy-mm-dd ");


    //Checking for regeneration of  monthly reward whose end date has passed daily at 12 a.m.
    @Scheduled(cron = "0 0 0 1/1 * ? ")
    // @Scheduled(cron="0 * * ? * *")
    public void scheduleMonthly() throws ParseException {

        ArrayList<Rewards> list = (ArrayList<Rewards>) rewardsRepository.findAll();

        int i=0;

        while(i<list.size()){
            Rewards oldReward = list.get(i);

        /*    SimpleDateFormat sdf=new SimpleDateFormat("yyy-mm-dd ");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            // Get the two dates to be compared
            //Date d1 =utcDate.dateToday(oldReward.getEndDate());
            LocalDate d01 =oldReward.getEndDate();
            String d1=(sdf.format(d01));
            System.out.println(d1);*/
            LocalDate d1 =oldReward.getEndDate();
            LocalDate d2 = LocalDate.now();
/*

            LocalDate today = LocalDate.now();

            String d2=(sdf.format(today));
            System.out.println(d2);
*/

           // Date d222=d.parse(d2);
            Calendar cal = Calendar.getInstance();
            String month = monthName[cal.get(Calendar.MONTH)];
            String month1=monthName[cal.get(Calendar.MONTH)+1];
            String currentYear = String.valueOf(cal.get(Calendar.YEAR));
            String year =String.valueOf(cal.get(Calendar.YEAR)-1);

            if (oldReward.getFrequency() == FrequencyEnum.Monthly && d2.isAfter(d1) && oldReward.isRegenerated()) {

                rewardsRepository.updateToNull(oldReward.getRewardId());

                Rewards newReward = new Rewards();

                String rName1 = oldReward.getRewardName();

                String replaceString = rName1.replaceFirst(month,month1).replaceFirst(year,currentYear);

                newReward.setRewardName(replaceString);
                newReward.setNominationsAllowed(oldReward.getNominationsAllowed());
                newReward.setSelfNominate(oldReward.isSelfNominate());
                newReward.setFrequency(oldReward.getFrequency());
                newReward.setDescription(oldReward.getDescription());
                newReward.setCategory(oldReward.getCategory());
                newReward.setRegenerated(true);
                newReward.setStartDate(oldReward.getEndDate());
                LocalDate d3 = newReward.getStartDate();
                LocalDate ed=d3.plusMonths(1);
                newReward.setEndDate(ed);
                newReward.setAwardStatus(CREATED);

                rewardsService.save(newReward);

                Set<RewardsCriteria> criteria= rewardsCriteriaRepository.findByRewardId(oldReward.getId());

                for (Iterator<RewardsCriteria> it = criteria.iterator(); it.hasNext(); ) {
                    RewardsCriteria f = it.next();

                    RewardsCriteria rewardsCriteria = new RewardsCriteria();
                    rewardsCriteria.setRewardId(newReward.getId());
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
    public void scheduleQuarterly() {

        ArrayList<Rewards> list = (ArrayList<Rewards>) rewardsRepository.findAll();

        int i=0;

        while(i<list.size()){
            Rewards oldReward = list.get(i);

            // Get the two dates to be compared
            LocalDate d1 =oldReward.getEndDate();
            LocalDate d2 = LocalDate.now();

            Calendar cal = Calendar.getInstance();
            String month = monthName[cal.get(Calendar.MONTH)];
            String month1=monthName[cal.get(Calendar.MONTH)+4];
            String currentYear = String.valueOf(cal.get(Calendar.YEAR));
            String year =String.valueOf(cal.get(Calendar.YEAR)-1);

            if (oldReward.getFrequency() == FrequencyEnum.Quarterly && d2.isAfter(d1) && oldReward.isRegenerated()) {

                rewardsRepository.updateToNull(oldReward.getRewardId());

                Rewards newReward = new Rewards();

                String rName1 = oldReward.getRewardName();

                String replaceString = rName1.replaceFirst(month,month1).replaceFirst(year,currentYear);

                newReward.setRewardName(replaceString);
                newReward.setNominationsAllowed(oldReward.getNominationsAllowed());
                newReward.setSelfNominate(oldReward.isSelfNominate());
                newReward.setFrequency(oldReward.getFrequency());
                newReward.setDescription(oldReward.getDescription());
                newReward.setCategory(oldReward.getCategory());
                newReward.setRegenerated(true);
                newReward.setStartDate(oldReward.getEndDate());
                LocalDate d3 = newReward.getStartDate();
                LocalDate ed=d3.plusMonths(4);
                newReward.setEndDate(ed);
                newReward.setAwardStatus(CREATED);

                rewardsService.save(newReward);

                Set<RewardsCriteria> criterias= rewardsCriteriaRepository.findByRewardId(oldReward.getId());

                for (Iterator<RewardsCriteria> it = criterias.iterator(); it.hasNext(); ) {
                    RewardsCriteria f = it.next();

                    RewardsCriteria rewardsCriteria = new RewardsCriteria();
                    rewardsCriteria.setRewardId(newReward.getId());
                    rewardsCriteria.setCriteriaId(f.getCriteriaId());
                    rewardsCriteria.setCompulsory(f.getCompulsory());

                    rewardsCriteriaRepository.save(rewardsCriteria);

                }
            }

            i++;
        }
    }


    //Regenerating yearly rewards starting from the 1st of every year 12 a.m.
    //Checking for regeneration of  monthly reward whose end date has passed every 1st of month at 12 a.m.
    @Scheduled(cron = "0 0 12 1 1/1 ? ")

    public void scheduleYearly(){

        ArrayList<Rewards> list = (ArrayList<Rewards>) rewardsRepository.findAll();

        int i=0;

        while(i<list.size()){
            Rewards oldReward = list.get(i);

            // Get the two dates to be compared
            LocalDate d1 =oldReward.getEndDate();
            LocalDate d2 = LocalDate.now();

            Calendar cal = Calendar.getInstance();
            String year = String.valueOf(cal.get(Calendar.YEAR));
            String year1=String.valueOf(cal.get(Calendar.YEAR)+1);

            if (oldReward.getFrequency() == FrequencyEnum.Annually && d2.isAfter(d1) && oldReward.isRegenerated()==true) {

                rewardsRepository.updateToNull(oldReward.getRewardId());

                Rewards newReward = new Rewards();

                String rName1 = oldReward.getRewardName();

                String replaceString=rName1.replace(year,year1);

                newReward.setRewardName(replaceString);
                newReward.setNominationsAllowed(oldReward.getNominationsAllowed());
                newReward.setSelfNominate(oldReward.isSelfNominate());
                newReward.setFrequency(oldReward.getFrequency());
                newReward.setDescription(oldReward.getDescription());
                newReward.setCategory(oldReward.getCategory());
                newReward.setRegenerated(true);
                newReward.setStartDate(oldReward.getEndDate());
                LocalDate d3 = newReward.getStartDate();
                LocalDate ed=d3.plusYears(1);
                newReward.setEndDate(ed);
                newReward.setAwardStatus(CREATED);

                rewardsService.save(newReward);

                Set<RewardsCriteria> criteria= rewardsCriteriaRepository.findByRewardId(oldReward.getId());

                for (Iterator<RewardsCriteria> it = criteria.iterator(); it.hasNext(); ) {
                    RewardsCriteria f = it.next();

                    RewardsCriteria rewardsCriteria = new RewardsCriteria();
                    rewardsCriteria.setRewardId(newReward.getId());
                    rewardsCriteria.setCriteriaId(f.getCriteriaId());
                    rewardsCriteria.setCompulsory(f.getCompulsory());

                    rewardsCriteriaRepository.save(rewardsCriteria);

                }
            }

            i++;
        }

    }

    //Checking everyday at 9 a.m. to Roll out after reward has been edited after roll out
  @Scheduled(cron = "0 0 9 * * ?  ")
//    @Scheduled(cron = "0 * * ? * * ")
    public void editAfterRollOut(){
        ArrayList<Rewards> rewards = (ArrayList<Rewards>) rewardsRepository.findAll();
        LocalDate today = LocalDate.now();
        for(int i=0;i<rewards.size();i++){
            Long rewardId=rewards.get(i).getRewardId();
            int awardStatus=rewards.get(i).getAwardStatus();
            LocalDate StartDate=rewards.get(i).getStartDate();
            if(rewardsRepository.checkingRewardInRolledOut(rewardId)>0 && awardStatus==EDITED_AFTER_ROLLOUT
                    && StartDate.equals(today)){
                rewardsRepository.updateAwardStatus(ROLLED_OUT,rewardId);

                if(rewards.get(i).getFrequency()==FrequencyEnum.Monthly)
                    rewardsRepository.updateEndDateRolledOutEdit(rewardId,today.plusMonths(1));
                else
                if(rewards.get(i).getFrequency()==FrequencyEnum.Quarterly)
                    rewardsRepository.updateEndDateRolledOutEdit(rewardId,today.plusMonths(4));
                else
                if(rewards.get(i).getFrequency()==FrequencyEnum.Annually)
                    rewardsRepository.updateEndDateRolledOutEdit(rewardId,today.plusYears(1));

            }
        }
    }
//set award status 2 where end date is passed everyday at 9 am
  @Scheduled(cron = "0 0 9 * * ?  ")
    public void endDatePassed(){

        ArrayList<Rewards> rewards = (ArrayList<Rewards>) rewardsRepository.findAll();
        LocalDate today = LocalDate.now();
        for(int i=0;i<rewards.size();i++) {
            Long rewardId = rewards.get(i).getRewardId();
            LocalDate endDate=rewards.get(i).getEndDate();
            if( endDate.isEqual(today)&& rewards.get(i).getAwardStatus()==ROLLED_OUT)
                rewardsRepository.updateAwardStatus(END_DATE_PASSED,rewardId);
        }
        }
}



