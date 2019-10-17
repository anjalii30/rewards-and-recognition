package com.rar.service.impl;


import com.rar.model.Awarded;
import com.rar.repository.AwardedRepository;
import com.rar.service.AwardedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class AwardedServiceImpl implements AwardedService {


    @Autowired
    private AwardedRepository awardedRepository;



    /*@Override
    public HashMap<String, Long> getReport(Long year) {

        HashMap<String, Long> report = new HashMap<String, Long>();
        report.put("rewards", awardedRepository.findByRewards(year));
        report.put("week", awardedRepository.weeklyFrequency(year));
        report.put("month", awardedRepository.monthlyFrequency(year));
        report.put("quarter", awardedRepository.quarterlyFrequency(year));
        report.put("annual", awardedRepository.annuallyFrequency(year));
        report.put("spot", awardedRepository.spotFrequency(year));
        report.put("people", awardedRepository.findByPeople(year));

        return report;

    }*/

    @Override
    public Awarded Update(Long id, Awarded awarded) {
        Awarded awarded1=awardedRepository.findById(id).get();
        awarded1.setReward_id(awarded.getReward_id());
        awarded1.setEmp_id(awarded.getEmp_id());
        //awarded1.s(awarded.getEmployee_image());
        awarded1.setProject_id(awarded.getProject_id());
        awarded1.setSubjectDescription(awarded.getSubjectDescription());

        Awarded update=awardedRepository.save(awarded1);
        return update;
    }

    @Override
    public Awarded save(Awarded awarded) {
        return awardedRepository.save(awarded);
    }

    @Override
    public Object[] findAllAwarded() {
        return (Object[]) awardedRepository.findAllAwarded();
    }

    /*@Override
    public HashMap<Object,List> ehomepage(){
        HashMap<Object,List> awarded=new HashMap<>();
        awarded.put("awarded", (List) awardedRepository.ehomepage());
        return awarded;
    }*/
}