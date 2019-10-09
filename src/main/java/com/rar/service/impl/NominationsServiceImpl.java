package com.rar.service.impl;

import com.rar.model.Evidences;
import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import com.rar.repository.EvidencesRepository;
import com.rar.repository.NominationsRepository;
import com.rar.service.NominationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        nominations.setEndingDate(nominationPojo.getEndingDate());
        nominations.setStartingDate(nominationPojo.getStartDate());
        nominations.setProjectName(nominationPojo.getProjectName());

        nominations = nominationsRepository.save(nominations);

        long nominationID = nominations.getNominationID();


        Evidences evidences = new Evidences();
        System.out.println(nominationPojo.getEvidencesPojoList().size());

        for (int i = 0; i < nominationPojo.getEvidencesPojoList().size(); i++) {
            evidences = new Evidences();

            evidences.setNominationID(nominationID);
            evidences.setCriteriaName(nominationPojo.getEvidencesPojoList().get(i).getCriteriaName());
            evidences.setEvidences(nominationPojo.getEvidencesPojoList().get(i).getEvidences());

            evidencesRepository.save(evidences);
        }


        HashMap<String, Object> s = new HashMap<>();
        s.put("evidences", evidences);
        s.put("nominations", nominations);
        Object returnValue = s;

        return ResponseEntity.ok(s);
    }

    @Override
    public List<Nominations> GetData(Map<String, Long> rewardID) {
        return  nominationsRepository.GetData(rewardID);
    }
}
