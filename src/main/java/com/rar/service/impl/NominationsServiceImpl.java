package com.rar.service.impl;

import com.rar.DTO.History;
import com.rar.DTO.NominationPojo;
import com.rar.DTO.ProjectNominationHistory;
import com.rar.DTO.UserNominationDetails;
import com.rar.model.Evidences;
import com.rar.model.Nominations;
import com.rar.model.Rewards;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rar.utils.Constants.PUBLISHED;

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
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @Override
    public ResponseEntity<?> nominationSave(List<NominationPojo> nominationPojo, Long managerId) {

        List<HashMap<String, Object>> s = new ArrayList<>();
        for(int i=0;i<nominationPojo.size();i++) {
            Nominations nominations = new Nominations();

                nominations.setUserID(nominationPojo.get(i).getUserId());
                nominations.setRewardID(nominationPojo.get(i).getRewardId());
                nominations.setSelected(nominationPojo.get(i).isSelected());
                nominations.setHrSelected(nominationPojo.get(i).isHrSelected());
                nominations.setReason(nominationPojo.get(i).getReason());
                nominations.setProjectName(projectRepository.getProjectName(nominationPojo.get(i).getProjectId()));
                nominations.setRewardName(rewardsRepository.getRewardName(nominationPojo.get(i).getRewardId()));
                nominations.setUserName(userRepository.getNameById(nominationPojo.get(i).getUserId()));
                nominations.setManagerId(managerId);
                nominations.setManagerName(managerRepository.getManagerName(managerId));
                nominations.setProjectId(nominationPojo.get(i).getProjectId());
                nominationsRepository.save(nominations);

            long nominationID = nominations.getNominationID();

            for (int j = 0; j < nominationPojo.get(i).getEvidencesPojoList().size(); j++) {
               Evidences evidences = new Evidences();

                evidences.setNominationID(nominationID);
                evidences.setCriteriaDesc(nominationPojo.get(i).getEvidencesPojoList().get(j).getCriteriaDesc());
                evidences.setEvidences(nominationPojo.get(i).getEvidencesPojoList().get(j).getEvidences());
                evidences.setTextEvidence(nominationPojo.get(i).getEvidencesPojoList().get(j).getTextEvidence());

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

    @Override
    public void awardeeSelect(Map<String, Long[]> nominationId) throws IOException, MessagingException, TemplateException {
        Long[] nominationID= nominationId.get("nominationId");
        String[] emails=userRepository.getAllEmails();
        for (int i = 0; i < nominationID.length; i++) {
            System.out.println(nominationID[i]);
            nominationsRepository.awardeeSelect(nominationID[i]);
            System.out.println("test");
            rewardsRepository.updateAwardStatus(PUBLISHED,nominationsRepository.getRewardId(nominationID[i]));
            System.out.println("abc");
            for (int j = 0; j < emails.length; j++) {
                Map<String,Object> root = new HashMap();
                root.put("name",userRepository.getName(emails[j]));
                root.put("user_name", nominationsRepository.getUserName(nominationID[i]));
                root.put("reward_name",nominationsRepository.getRewardName(nominationID[i]));
                root.put("image",userRepository.getImage(nominationsRepository.userId(nominationID[i])));
                if(nominationsRepository.userId(nominationID[i])==userRepository.getIdByEmail(emails[j]))
                    sendEmail.sendEmailToWinner(root,emails[j],"You have been awarded");
                else
                    sendEmail.sendEmailWithAttachment(root,emails[j], "Employee awarded for the reward");
            }
        }
        nominationsService.rewardCoins(nominationID);
    }

    @Override
    public ResponseEntity<List<Map<String,String>>> getAwardedPeople() {
        return new ResponseEntity<>(nominationsRepository.getAwarded(),HttpStatus.OK);
    }

   @Override
    public ResponseEntity<List<Nominations>> getAllNominations() {
        return new ResponseEntity<>(nominationsRepository.getAllNominations(),HttpStatus.OK);
 }

    @Override
    public void managerSelect(Nominations[] nominations,Long managerId,String managerName)  {

        for(int i=0;i<nominations.length;i++){
            Long nominationId =nominations[i].getNominationID();
            String reason=nominations[i].getReason();
            boolean selected=nominations[i].isSelected();
            nominationsRepository.updateSelected(selected,reason,nominationId,managerId,managerName);
        }
    }

    @Override
    public ResponseEntity<List<Rewards>> nominatedRewards() {
        return new ResponseEntity<>(rewardsRepository.nominatedRewards(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List> getTopAwardee() {

        return new ResponseEntity<>(nominationsRepository.getTopAwardee(), HttpStatus.OK);
    }

    @Override
    public void rewardCoins(Long[] nominationID) {


       int count = nominationID.length;
       System.out.println(count);
       Long rewardId=nominationsRepository.getRewardId(nominationID[0]);
       System.out.println(rewardId);
       Long rewardCoinValue = rewardsRepository.getCoinValue(rewardId);
       System.out.println(rewardCoinValue)  ;
       double wonCoinValue = (rewardCoinValue/count);
       for(int i=0; i<nominationID.length;i++){
           Long userId = nominationsRepository.userId(nominationID[i]);
           double currentWalletBalance = userRepository.getWalletBalance(userId);
           double newWalletBalance = currentWalletBalance + wonCoinValue;
           userRepository.updateWalletBalance(newWalletBalance,userId);
       }
    }

    @Override
    public ResponseEntity<List<History>> history(String email) throws Exception{
        long managerId=managerRepository.findByEmail(email);
        List<History> histories= new ArrayList<>();
          long[] rewardId= nominationsRepository.rewardId(managerId);
        for(int i=0; i< rewardId.length; i++){
            List<ProjectNominationHistory> projectNominationHistoryList=new ArrayList<>();
            long[] projectId = nominationsRepository.getProjectIds(managerId,rewardId[i]);
            for(int k=0;k<projectId.length;k++){
            List<UserNominationDetails> userNominationDetailsList = new ArrayList<>();
                long[] userIds= nominationsRepository.userIds(managerId, rewardId[i], projectId[k]);
                for(int j=0; j< userIds.length; j++){
                    userNominationDetailsList.add(j, new UserNominationDetails(userIds[j],nominationsRepository.gettingReason(managerId,rewardId[i],userIds[j],projectId[k]),userRepository.getUserName(userIds[j]),nominationsRepository.gettingSelected(managerId,rewardId[i],userIds[j],projectId[k])));
                }
                projectNominationHistoryList.add(k,new ProjectNominationHistory(projectId[k],projectRepository.getProjectName(projectId[k]),userNominationDetailsList));
        }
            histories.add(i,new History(nominationsRepository.rewardName(rewardId[i]),projectNominationHistoryList));
        }
        if (rewardId.length== 0)
            histories= new ArrayList<>();

        return new ResponseEntity<>(histories,HttpStatus.OK);
    }
}
