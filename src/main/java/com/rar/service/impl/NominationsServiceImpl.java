package com.rar.service.impl;

import com.rar.model.Evidences;
import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import com.rar.repository.EvidencesRepository;
import com.rar.repository.NominationsRepository;
import com.rar.service.NominationsService;
import com.rar.utils.CheckDisable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


@Service
@Transactional
public class NominationsServiceImpl implements NominationsService {

    @Autowired
    NominationsRepository nominationsRepository;
    @Autowired
    EvidencesRepository evidencesRepository;

    @Override
    public ResponseEntity<?> nominationSave(NominationPojo nominationPojo) {
        Nominations nominations = new Nominations();
        nominations.setUserID(nominationPojo.getUserId());
        nominations.setFrequency(nominationPojo.getFrequency());
        nominations.setRewardID(nominationPojo.getRewardId());
        nominations.setEnd_date(nominationPojo.getEnd_date());
        nominations.setStart_date(nominationPojo.getStart_date());
        nominations.setProject_name(nominationPojo.getProject_name());
        nominations.setSelected(nominationPojo.isSelected());
    //    nominations.setReward_name(nominationPojo.getReward_name());
        nominations.setEmployee_name(nominationPojo.getEmployee_name());
        nominations.setDisable(nominationPojo.isDisable());

        nominations = nominationsRepository.save(nominations);

        long nominationID = nominations.getNominationID();


        Evidences evidences = new Evidences();
        System.out.println(nominationPojo.getEvidencesPojoList().size());

        for (int i = 0; i < nominationPojo.getEvidencesPojoList().size(); i++) {
            evidences = new Evidences();

            evidences.setNominationID(nominationID);
            evidences.setCriteria_desc(nominationPojo.getEvidencesPojoList().get(i).getCriteria_desc());
            evidences.setEvidences(nominationPojo.getEvidencesPojoList().get(i).getEvidences());
            evidences.setText_evidence(nominationPojo.getEvidencesPojoList().get(i).getText_evidence());

            evidencesRepository.save(evidences);
        }


        HashMap<String, Object> s = new HashMap<>();
        s.put("evidences", evidences);
        s.put("nominations", nominations);
        Object returnValue = s;

        return ResponseEntity.ok(s);
    }

    @Override
    public List<Nominations> GetData(Long rewardID) {

        return  nominationsRepository.GetData(rewardID);
    }
}
