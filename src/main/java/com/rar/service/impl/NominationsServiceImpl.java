package com.rar.service.impl;

import com.rar.exception.InvalidUserException;
import com.rar.model.Evidences;
import com.rar.model.NominationPojo;
import com.rar.model.Nominations;
import com.rar.model.Rewards;
import com.rar.repository.EvidencesRepository;
import com.rar.repository.ManagerRepository;
import com.rar.repository.NominationsRepository;
import com.rar.repository.RewardsRepository;
import com.rar.service.NominationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//import com.rar.utils.CheckDisable;


@Service
@Transactional
public class NominationsServiceImpl implements NominationsService {

    @Autowired
    private NominationsRepository nominationsRepository;
    @Autowired
    private EvidencesRepository evidencesRepository;
    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private ManagerRepository managerRepository;
    @Override
    public ResponseEntity<?> nominationSave(List<NominationPojo> nominationPojo) {

        List<HashMap<String, Object>> s = new ArrayList<>();
        for(int i=0;i<nominationPojo.size();i++) {
            Nominations nominations = new Nominations();

                nominations.setUserID(nominationPojo.get(i).getUserId());
                nominations.setRewardID(nominationPojo.get(i).getRewardId());
               // nominations.setProject_id(nominationPojo.get(i).getProject_id());
                nominations.setSelected(nominationPojo.get(i).isSelected());
                nominations.setHr_selected(nominationPojo.get(i).isHr_selected());
                nominations.setReason(nominationPojo.get(i).getReason());
                nominations.setProject_name(nominationPojo.get(i).getProject_name());
                nominations.setReward_name(nominationPojo.get(i).getReward_name());
                nominations.setUsername(nominationPojo.get(i).getUser_name());

                nominationsRepository.save(nominations);

            long nominationID = nominations.getNominationID();

            for (int j = 0; j < nominationPojo.get(i).getEvidencesPojoList().size(); j++) {
               Evidences evidences = new Evidences();

                evidences.setNominationID(nominationID);
                System.out.println("test"+nominationID);
                evidences.setCriteria_desc(nominationPojo.get(i).getEvidencesPojoList().get(j).getCriteria_desc());
                evidences.setEvidences(nominationPojo.get(i).getEvidencesPojoList().get(j).getEvidences());
                evidences.setText_evidence(nominationPojo.get(i).getEvidencesPojoList().get(j).getText_evidence());

                evidencesRepository.save(evidences);
            }

        }

       return ResponseEntity.ok(s);
    }

/*    @Override
    public ResponseEntity<?> GetData(Long rewardID) throws Exception {

            List<Nominations> nominations = null;

                nominations = nominationsRepository.GetData(rewardID);

                return ResponseEntity.ok(nominations);
    }*/

    @Override
    public List<Nominations> GetData(Long rewardID) throws Exception {

        List<Nominations> nominations = null;

        nominations = nominationsRepository.GetData(rewardID);

        return nominations;
    }

    @Override
    public  List<List<Nominations>> showToManager(String manager_email,Long reward_id) throws Exception {

        try {
            Long manager_id = managerRepository.findByEmail(manager_email);
            Long[] members = managerRepository.getMembers(manager_id);

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
            Long manager_id = managerRepository.findByEmail(email);
            Long[] members = managerRepository.getMembers(manager_id);
            List<List<Nominations>> getNominations = new ArrayList<>();
            for (int i = 0; i < members.length; i++) {
           if(nominationsRepository.getAllNominations(members[i]).isEmpty())
                    continue;
                getNominations.add(nominationsRepository.getAllNominations(members[i]));
            }
            return getNominations;
        }catch (Exception e) {
            throw new InvalidUserException("you are not a manager");

        }
    }

//    @Override
//    public void managerNominate(List<NominationPojo> nominationsList) {
//
//        System.out.println(nominationsList.size());
//        Nominations nominations=new Nominations();
//        for(int i=0; i<nominationsList.size();i++) {
//            nominations.setUserID(nominationsList.);
//
//            System.out.println(nominationsList.get(i));
//
//        }
//    }

   @Override
    public List<Nominations> getAllNominations() {
        return nominationsRepository.getAllNominations();
 }

    @Override
    public void managerSelect(Nominations[] nominations,Long manager_id,String manager_name)  {

        for(int i=0;i<nominations.length;i++){

            Long nomination_id =nominations[i].getNominationID();

            String reason=nominations[i].getReason();

            boolean selected=nominations[i].isSelected();

            nominationsRepository.updateSelected(selected,reason,nomination_id,manager_id,manager_name);

        }

    }

    @Override
    public List<Rewards> nominated_rewards() throws Exception {
        return rewardsRepository.nominated_rewards();
    }

    @Override
    public List<Map<String, String>> getTopAwardee() {
        return nominationsRepository.getTopAwardee();
    }
}
