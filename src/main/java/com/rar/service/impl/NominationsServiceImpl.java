package com.rar.service.impl;

import com.rar.exception.InvalidUserException;
import com.rar.model.Evidences;
import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import com.rar.repository.EvidencesRepository;
import com.rar.repository.ManagerRepository;
import com.rar.repository.NominationsRepository;
import com.rar.service.NominationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//import com.rar.utils.CheckDisable;


@Service
@Transactional
public class NominationsServiceImpl implements NominationsService {

    @Autowired
    NominationsRepository nominationsRepository;
    @Autowired
    EvidencesRepository evidencesRepository;

    @Autowired
    private ManagerRepository managerRepository;
    @Override
    public ResponseEntity<?> nominationSave(NominationPojo nominationPojo) {
        Nominations nominations = new Nominations();
        nominations.setUserID(nominationPojo.getUserId());
        nominations.setRewardID(nominationPojo.getRewardId());
        nominations.setProject_name(nominationPojo.getProject_name());
        nominations.setSelected(nominationPojo.isSelected());
        nominations.setReward_name(nominationPojo.getReward_name());
        nominations.setEmployee_name(nominationPojo.getEmployee_name());
        nominations.setHr_selected(nominationPojo.isHr_selected());
        nominations.setReason(nominationPojo.getReason());

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
    public List<Nominations> GetData(Long rewardID) throws Exception {

        try {
           /* Optional<Nominations> rId=nominationsRepository.findByRewardId(rewardID);
            if(rId.isPresent())*/
            return nominationsRepository.GetData(rewardID);
        }catch(Exception e){
            throw new Exception("No nominations for this reward");
        }
    }

    @Override
    public  List<List<Nominations>> showToManager(String manager_email,Long reward_id) throws Exception {

        try {
            Long manager_id = managerRepository.findByEmail(manager_email);
            Long[] members = nominationsRepository.getMembers(manager_id);

            List<List<Nominations>> getNominations = new ArrayList<>();

            for (int i = 0; i < members.length; i++) {
                if(nominationsRepository.getNominations((members[i]),reward_id).isEmpty())
                    continue;
                getNominations.add (nominationsRepository.getNominations(members[i],reward_id));
            }
            return getNominations;
        }catch (Exception e) {

            throw new InvalidUserException("you are not a manager");

        }
    }

    @Override
    public void awardeeSelect(Map<String, Long[]> nomination1_id) {

        Long[] nomination_id= nomination1_id.get("nomination_id");

        for(int i=0;i<nomination_id.length;i++){

            nominationsRepository.awardeeSelect(nomination_id[i]);
        }

    }

    @Override
    public List<Map<String,String>> getAwardedPeople() {
        return nominationsRepository.getAwarded();
    }

    @Override
    public List<List<Nominations>> showAllToManager(String email) throws Exception {
        try {
            System.out.println(email);
            Long manager_id = managerRepository.findByEmail(email);
            System.out.println(manager_id);
            Long[] members = nominationsRepository.getMembers(manager_id);
            System.out.println(Arrays.toString(members));
            List<List<Nominations>> getNominations = new ArrayList<>();
            ;
          //  List<Nominations> getNominations = null;
            for (int i = 0; i < members.length; i++) {
                System.out.println(members.length);
                System.out.println(members[i]);
               // getNominations = (nominationsRepository.getAllNominations(members[i]));
                System.out.println("test"+nominationsRepository.getAllNominations(members[i]));
                if(nominationsRepository.getAllNominations(members[i]).isEmpty())
                    continue;
                getNominations.add(nominationsRepository.getAllNominations(members[i]));
                System.out.println(getNominations);
            }
            System.out.println(getNominations);
            return getNominations;
        }catch (Exception e) {
             System.out.println(e);
            throw new InvalidUserException("you are not a manager");

        }
    }

    @Override
    public void managerNominate(Object[] nominationsList) {

        for(int i=0; i<nominationsList.length;i++) {
            Nominations nominations= (Nominations) nominationsList[i];

            Nominations nominations1 = new Nominations();

            nominations1.setSelected(true);
            nominations1.setEmployee_name(nominations.getEmployee_name());
            nominations1.setProject_name(nominations.getProject_name());
            nominations1.setRewardID(nominations.getRewardID());
            nominations1.setReward_name(nominations.getReward_name());
            nominations1.setUserID(nominations.getUserID());
            nominations1.setReason(nominations.getReason());
            nominations1.setEvidencesList(nominations.getEvidencesList());
            nominations1.setHr_selected(false);


            Nominations n=nominationsRepository.save(nominations1);

        }
    }

  /*  @Override
    public List<Nominations> getAllNominations() {
        return nominationsRepository.getAllNominations();
    }*/

}
