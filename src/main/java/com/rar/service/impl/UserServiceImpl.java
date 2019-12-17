package com.rar.service.impl;

import com.rar.entity.*;
import com.rar.DTO.DesignationSelected;
import com.rar.DTO.EditUserDetails;
import com.rar.DTO.ProjectDetailsUser;
import com.rar.repository.DesignationRepository;
import com.rar.repository.ProjectRepository;
import com.rar.repository.UserRepository;
import com.rar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public UserInfo save(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    public ResponseEntity userSave(EditUserDetails editUserDetails) {

        userRepository.insertUser(editUserDetails.getEmail(),editUserDetails.getName());



        long id = userRepository.getUserId(editUserDetails.getEmail());
        System.out.println(id);

        userRepository.insertUserRoles(id, (long) 1);
        for(int i=0;i<editUserDetails.getDesignationSelected().size();i++){
            userRepository.insertUserDesignation(id, editUserDetails.getDesignationSelected().get(i).getDid());
        }
       /* for (Iterator<DesignationSelected> designationIterator = editUserDetails.getDesignationSelected().iterator(); designationIterator.hasNext(); ) {
            DesignationSelected designation = designationIterator.next();
            userRepository.insertUserDesignation(id, designation.getDid());
        }*/
        int count=0;
        for(int j=0;j<editUserDetails.getProjectsList().size();j++){
            ProjectDetailsUser projects = editUserDetails.getProjectsList().get(j);
            if(projects.getManaging()){
                if (count==0){
                    userRepository.insertManager(editUserDetails.getEmail());
                    count++;
                }
                long mid = userRepository.findManagerId(editUserDetails.getEmail());
                userRepository.insertManagerProjects(mid, projects.getProject_id());
            }
            if(!projects.getManaging() && projects.getWorking()){
                userRepository.insertUserProjects(id,projects.getProject_id());
                for(int k=0;k< userRepository.getManagerIdFromProjectId(projects.getProject_id()).size();k++){
                    userRepository.insertUserManager(id, userRepository.getManagerIdFromProjectId(projects.getProject_id()).get(k));
                }

            }
        }

         /*  for(int i=0;i<userInfo.getProjectDetailsUsers().size();i++)
        {
            if(userInfo.getProjectDetailsUsers().get(i).getManaging()){
                if (count==0){
                    userRepository.insertManager(userInfo.getEmail());
                    count++;
                }
                long mid = userRepository.findManagerId(userInfo.getEmail());
                userRepository.insertManagerProjects(mid, userInfo.getProjectDetailsUsers().get(i).getProject_id());
            }
            if(!userInfo.getProjectDetailsUsers().get(i).getManaging() && userInfo.getProjectDetailsUsers().get(i).getWorking()){
                userRepository.insertUserProjects(id,userInfo.getProjectDetailsUsers().get(i).getProject_id());
                userRepository.insertUserManager(id, userRepository.getManagerIdFromProjectId(userInfo.getProjectDetailsUsers().get(i).getProject_id()));
            }

        }*/

//        int count=0;
//        for(int i=0;i<userInfo.getProjectDetails().size();i++)
//        {
//            if(userInfo.getProjectDetails().get(i).getManaging()){
//                if(count==0){
//                    userRepository.insertManager(userInfo.getEmail());
//                    count++;
//                }
//                long mid = userRepository.findManagerId(userInfo.getEmail());
//                userRepository.insertManagerProjects(mid, userInfo.getProjectDetails().get(i).getProject_id());
//            }
//            if(!userInfo.getProjectDetails().get(i).getManaging() && userInfo.getProjectDetails().get(i).getWorking()){
//                userRepository.insertUserProjects(id, userInfo.getProjectDetails().get(i).getProject_id());
//                userRepository.insertUserManager(id, userRepository.getManagerIdFromProjectId(userInfo.getProjectDetails().get(i).getProject_id()));
//            }
//        }
        /*boolean managingProject = userInfo.getManagingProject();
        if (managingProject = true) {
            userRepository.insertManager(userInfo.getEmail());
            long mid = userRepository.findManagerId(userInfo.getEmail());

            for (int i = 0; i < userInfo.getManagerProjects().size(); i++) {
                userRepository.insertManagerProjects(mid, userInfo.getManagerProjects().get(i).getProject_id());
            }
        }
        boolean workingOnProject = userInfo.getWorkingOnProject();
        if (workingOnProject = true) {
            for (int j = 0; j < userInfo.getUserProjects().size(); j++) {
                userRepository.insertUserProjects(id, userInfo.getUserProjects().get(j).getProject_id());
                userRepository.insertUserManager(id, userRepository.getManagerIdFromProjectId(userInfo.getUserProjects().get(j).getProject_id()));
            }
        }*/

        HashMap<String, Object> s = new HashMap<>();
        s.put("user", editUserDetails);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }


    @Override
    public EditUserDetails listById(long id) {
        Optional<UserInfo> userInfo= userRepository.findById(id);

        List<Designation> designations= (List<Designation>) designationRepository.findAll();
        List<DesignationSelected> designationSelected = new ArrayList<>();

        long designationId= userRepository.findDesignationId(id);

        for(int i=0;i<designations.size();i++){
            if(designations.get(i).getDid()==designationId){
                designationSelected.add(i, new DesignationSelected(designationId,designations.get(i).getDesignation(), true));
            }
            else
            {
                designationSelected.add(i,new DesignationSelected(designations.get(i).getDid(),designations.get(i).getDesignation(),false));
            }
        }

        List<Projects> projects = projectRepository.findAllData();

        List<ProjectDetailsUser> projectsList = new ArrayList<>();

        boolean managing, working;long mid=0;
        for(int i =0;i<projects.size();i++){
           projectRepository.userProjectPresent(id, projects.get(i).getProject_id());

           if(userRepository.isManager(userInfo.get().getEmail())>0)
               mid = userRepository.findManagerId(userInfo.get().getEmail());

            if(projectRepository.userProjectPresent(id, projects.get(i).getProject_id())>0){
                working= true;
                if(projectRepository.managerProjectPresent(mid, projects.get(i).getProject_id())>0)
                    managing=true;
                else
                    managing=false;
            }
            else{
                if(projectRepository.managerProjectPresent(mid, projects.get(i).getProject_id())>0){
                    managing=true;
                    working=true;}
                else{
                    managing=false;
                    working=false;
                }
            }
            projectsList.add(i, new ProjectDetailsUser(projects.get(i).getProject_id(),projects.get(i).getProject_name(),working,managing));
        }

        return new EditUserDetails(id,userInfo.get().getEmail(),userInfo.get().getName(),designationSelected,projectsList);
    }

    @Override
    public EditUserDetails update(long id, EditUserDetails editUserDetails) {

        for (int i = 0; i < editUserDetails.getDesignationSelected().size(); i++){
            userRepository.updateDesignation(id, editUserDetails.getDesignationSelected().get(i).getDid());}
        userRepository.deleteUserProjects(id);
        userRepository.deleteUserManagers(id);
        long mid=0;
        if(userRepository.isManager(editUserDetails.getEmail())>0){
            mid = userRepository.findManagerId(editUserDetails.getEmail());}

        userRepository.deleteManagerProjects(mid);


        for(int j=0; j < editUserDetails.getProjectsList().size(); j++){
            if(editUserDetails.getProjectsList().get(j).getManaging()){

                 mid = userRepository.findManagerId(editUserDetails.getEmail());
                userRepository.insertManagerProjects(mid, editUserDetails.getProjectsList().get(j).getProject_id());
            }
            if(!editUserDetails.getProjectsList().get(j).getManaging() && editUserDetails.getProjectsList().get(j).getWorking()){
                userRepository.insertUserProjects(id,editUserDetails.getProjectsList().get(j).getProject_id());
                for(int k=0;k< userRepository.getManagerIdFromProjectId(editUserDetails.getProjectsList().get(j).getProject_id()).size();k++){
                    userRepository.insertUserManager(id, userRepository.getManagerIdFromProjectId(editUserDetails.getProjectsList().get(j).getProject_id()).get(k));
                }
            }

         }

        return editUserDetails;

    }

}
