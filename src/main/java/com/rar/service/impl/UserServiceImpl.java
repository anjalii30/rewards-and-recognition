package com.rar.service.impl;

import com.rar.dto.DesignationSelected;
import com.rar.dto.EditUserDetails;
import com.rar.dto.ProjectDetailsUser;
import com.rar.model.Designation;
import com.rar.model.Projects;
import com.rar.model.UserInfo;
import com.rar.repository.*;
import com.rar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

import static com.rar.utils.Constants.ROLE_EMPLOYEE;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private NominationsRepository nominationsRepository;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public ResponseEntity userSave(EditUserDetails editUserDetails) {

        userRepository.insertUser(editUserDetails.getEmail(),editUserDetails.getName());
        long id = userRepository.getUserId(editUserDetails.getEmail());

        userRepository.makeWalletZero(id);
        userRepository.changeFirstSign(id);

        userRepository.insertUserRoles(id,ROLE_EMPLOYEE);
        for(int i=0;i<editUserDetails.getDesignationSelected().size();i++){
            userRepository.insertUserDesignation(id, editUserDetails.getDesignationSelected().get(i).getDid());
        }

        int count=0;
        for(int j=0;j<editUserDetails.getProjectsList().size();j++){
            ProjectDetailsUser projects = editUserDetails.getProjectsList().get(j);
            if(projects.getManaging()){
                if (count==0){
                    userRepository.insertManager(editUserDetails.getEmail());
                    count++;
                }
                long mid = userRepository.findManagerId(editUserDetails.getEmail());
                userRepository.insertManagerProjects(mid, projects.getProjectId());
            }
            if(!projects.getManaging() && projects.getWorking()){
                userRepository.insertUserProjects(id,projects.getProjectId());
                for(int k=0;k< userRepository.getManagerIdFromProjectId(projects.getProjectId()).size();k++){
             //       userRepository.insertUserManager(id, userRepository.getManagerIdFromProjectId(projects.getProjectId()).get(k));
                }

            }
        }

        HashMap<String, Object> s = new HashMap<>();
        s.put("user", editUserDetails);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<EditUserDetails> listById(Long id) {
        Optional<UserInfo> userInfo = userRepository.findById(id);

        List<Designation> designations = designationRepository.findAll();
        List<DesignationSelected> designationSelected = new ArrayList<>();

        long designationId = userRepository.findDesignationId(id);

        for (int i = 0; i < designations.size(); i++) {
            if (designations.get(i).getDesignationId() == designationId) {
                designationSelected.add(i, new DesignationSelected(designationId, designations.get(i).getDesignation(), true));
            } else {
                designationSelected.add(i, new DesignationSelected(designations.get(i).getDesignationId(), designations.get(i).getDesignation(), false));
            }
        }

        List<Projects> projects = projectRepository.findAllData();

        List<ProjectDetailsUser> projectsList = new ArrayList<>();

        boolean managing;
        boolean working;

        long mid = 0;
        for (int i = 0; i < projects.size(); i++) {
            projectRepository.userProjectPresent(id, projects.get(i).getProjectId());

            if (userRepository.isManager(userInfo.get().getEmail()) > 0)
                mid = userRepository.findManagerId(userInfo.get().getEmail());

            if (projectRepository.userProjectPresent(id, projects.get(i).getProjectId()) > 0) {
                working = true;
                if (projectRepository.managerProjectPresent(mid, projects.get(i).getProjectId()) > 0)
                    managing = true;
                else
                    managing = false;
            } else {
                if (projectRepository.managerProjectPresent(mid, projects.get(i).getProjectId()) > 0) {
                    managing = true;
                    working = true;
                } else {
                    managing = false;
                    working = false;
                }
            }
            projectsList.add(i, new ProjectDetailsUser(projects.get(i).getProjectId(), projects.get(i).getProjectName(), working, managing));
        }

        return new ResponseEntity<>(new EditUserDetails(id, userInfo.get().getEmail(), userInfo.get().getName(), designationSelected, projectsList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EditUserDetails> update(long id, EditUserDetails editUserDetails) {

        for (int i = 0; i < editUserDetails.getDesignationSelected().size(); i++){
            userRepository.updateDesignation(id, editUserDetails.getDesignationSelected().get(i).getDid());}
        userRepository.deleteUserProjects(id);
      //  userRepository.deleteUserManagers(id);
        long mid=0;
        if(userRepository.isManager(editUserDetails.getEmail())>0){
            mid = userRepository.findManagerId(editUserDetails.getEmail());}

        userRepository.deleteManagerProjects(mid);


        for(int j=0; j < editUserDetails.getProjectsList().size(); j++){
            if(editUserDetails.getProjectsList().get(j).getManaging()){

                mid = userRepository.findManagerId(editUserDetails.getEmail());
                userRepository.insertManagerProjects(mid, editUserDetails.getProjectsList().get(j).getProjectId());
            }
            if(!editUserDetails.getProjectsList().get(j).getManaging() && editUserDetails.getProjectsList().get(j).getWorking()){
                userRepository.insertUserProjects(id,editUserDetails.getProjectsList().get(j).getProjectId());
                for(int k=0;k< userRepository.getManagerIdFromProjectId(editUserDetails.getProjectsList().get(j).getProjectId()).size();k++){
                }
            }

        }

        return new ResponseEntity<>(editUserDetails,HttpStatus.OK);
    }

    @Override
    public ResponseEntity getCoinsDetails(String email) {
        Long userId=userRepository.getIdByEmail(email);
        Long[] rewardId=nominationsRepository.getRewardIdForUser(userId);


        List list=new ArrayList();
        for(int i=0;i<rewardId.length;i++){


            Long count=nominationsRepository.getCount(rewardId[i]);
            Long rewardCoinValue = rewardsRepository.getCoinValue(rewardId[i]);
            double wonCoinValue = rewardCoinValue/count;

            Map map=new HashMap();
            map.put("reward name",rewardsRepository.getRewardName(rewardId[i]));
            map.put("reward value",rewardCoinValue);
            map.put("coins earned",df2.format(wonCoinValue));


            list.add(map);

        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
}
