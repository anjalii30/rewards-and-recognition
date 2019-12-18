package com.rar.service.impl;

import com.rar.model.*;
import com.rar.DTO.NominationPojo;

import com.rar.repository.*;
import com.rar.service.NominationsService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private SendEmail sendEmail;
    @Autowired
    private NominationsService nominationsService;


    @Override
    public ResponseEntity<?> nominationSave(List<NominationPojo> nominationPojo, Long manager_id) {

        List<HashMap<String, Object>> s = new ArrayList<>();
        for(int i=0;i<nominationPojo.size();i++) {
            Nominations nominations = new Nominations();


                nominations.setUserID(nominationPojo.get(i).getUserId());
                nominations.setRewardID(nominationPojo.get(i).getRewardId());
                nominations.setSelected(nominationPojo.get(i).isSelected());
                nominations.setHr_selected(nominationPojo.get(i).isHr_selected());
                nominations.setReason(nominationPojo.get(i).getReason());
                nominations.setProject_name(projectRepository.getProjectName(nominationPojo.get(i).getProject_id()));
                nominations.setReward_name(nominationPojo.get(i).getReward_name());
                nominations.setUsername(userRepository.getNameById(nominationPojo.get(i).getUserId()));
                nominations.setManagerId(manager_id);
                nominations.setProjectId(nominationPojo.get(i).getProject_id());

              /*  System.out.println("rewards"+nominationPojo.get(i).getRewardId());
                System.out.println("userid"+nominationPojo.get(i).getUserId());
                System.out.println("projetId"+nominationPojo.get(i).getProject_id());
               */ nominationsRepository.save(nominations);

            long nominationID = nominations.getNominationID();

            for (int j = 0; j < nominationPojo.get(i).getEvidencesPojoList().size(); j++) {
               Evidences evidences = new Evidences();

                evidences.setNominationID(nominationID);
                evidences.setCriteria_desc(nominationPojo.get(i).getEvidencesPojoList().get(j).getCriteria_desc());
                evidences.setEvidences(nominationPojo.get(i).getEvidencesPojoList().get(j).getEvidences());
                evidences.setText_evidence(nominationPojo.get(i).getEvidencesPojoList().get(j).getText_evidence());

                evidencesRepository.save(evidences);
            }

        }

       return ResponseEntity.ok(s);
    }


    @Override
    public ResponseEntity<List<Nominations>> GetData(Long rewardID) throws Exception {

        List<Nominations> nominations = null;

        nominations = nominationsRepository.GetData(rewardID);

        return new ResponseEntity<>(nominations,HttpStatus.OK);
    }


    //used for self-nomination
/*    @Override
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
    }*/

    @Override
    public void awardeeSelect(Map<String, Long[]> nomination1_id) throws IOException, MessagingException, TemplateException {

        Long[] nomination_id= nomination1_id.get("nomination_id");

        String[] emails=userRepository.getAllEmails();

        for (int i = 0; i < nomination_id.length; i++) {

            nominationsRepository.awardeeSelect(nomination_id[i]);


            for (int j = 0; j < emails.length; j++) {

                String name=userRepository.getName(emails[j]);
                String reward_name=nominationsRepository.getRewardName(nomination_id[i]);
                String user_name=nominationsRepository.getUserName(nomination_id[i]);
                String image =userRepository.getImage(nominationsRepository.userId(nomination_id[i]));

                Map<String,Object> root = new HashMap();
                root.put("name",name );
                root.put("user_name", user_name);
                root.put("reward_name",reward_name);
                root.put("image",image);
                if(nominationsRepository.userId(nomination_id[i])==userRepository.getIdByEmail(emails[j]))
                    sendEmail.sendEmailToWinner(root,emails[j],"You have been awarded");
                else
                sendEmail.sendEmailWithAttachment(root,emails[j], "Employee awarded for the reward");

            }
        }
        nominationsService.rewardCoins(nomination_id);
    }

    @Override
    public ResponseEntity<List<Map<String,String>>> getAwardedPeople() {
        return new ResponseEntity<>(nominationsRepository.getAwarded(),HttpStatus.OK);
    }

    //used for self-nominaiton
  /*  @Override
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
    }*/

   @Override
    public ResponseEntity<List<Nominations>> getAllNominations() {
        return new ResponseEntity<>(nominationsRepository.getAllNominations(),HttpStatus.OK);
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
    public ResponseEntity<List<Rewards>> nominated_rewards() {
        return new ResponseEntity<>(rewardsRepository.nominated_rewards(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List> getTopAwardee() {
        return new ResponseEntity<>(nominationsRepository.getTopAwardee(), HttpStatus.OK);
    }

    @Override
    public void rewardCoins(Long[] nomination_id) {

       int count = nomination_id.length;
       String reward_name = nominationsRepository.getRewardName(nomination_id[0]);
       Long rewardCoinValue = rewardsRepository.getCoinValue(reward_name);
       Long wonCoinValue = rewardCoinValue/count;
       for(int i=0; i<nomination_id.length;i++){
           Long user_id = nominationsRepository.userId(nomination_id[i]);
           Long currentWalletBalance = userRepository.getWalletBalance(user_id);
           Long newWalletBalance = currentWalletBalance + wonCoinValue;
           userRepository.updateWalletBalance(user_id,newWalletBalance);
       }
    }
}
