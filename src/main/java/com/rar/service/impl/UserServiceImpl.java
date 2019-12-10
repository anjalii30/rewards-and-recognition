package com.rar.service.impl;

import com.rar.model.*;
import com.rar.repository.DesignationRepository;
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

    @Override
    public UserInfo save(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    public ResponseEntity userSave(UserInfo userInfo) {

       userRepository.insertUser(userInfo.getEmail(),userInfo.getName());



        long id = userRepository.getUserId(userInfo.getEmail());
        System.out.println(id);
        userRepository.insertUserRoles(id, (long) 1);
        for (Iterator<Designation> designationIterator = userInfo.getDesignation().iterator(); designationIterator.hasNext(); ) {
            Designation designation = designationIterator.next();
            userRepository.insertUserDesignation(id, designation.getDid());
        }
        int count=0;
        for(int i=0;i<userInfo.getProjectDetailsUsers().size();i++)
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

        }


  /*      for(Iterator<Projects> projectsIterator = userInfo.getProjects().iterator();projectsIterator.hasNext();){
            Projects projects = projectsIterator.next();
            if(projects.getManaging()){
                if (count==0){
                    userRepository.insertManager(userInfo.getEmail());
                    count++;
                }
                long mid = userRepository.findManagerId(userInfo.getEmail());
                userRepository.insertManagerProjects(mid, projects.getProject_id());
            }
            if(!projects.getManaging() && projects.getWorking()){
                userRepository.insertUserProjects(id,projects.getProject_id());
                userRepository.insertUserManager(id, userRepository.getManagerIdFromProjectId(projects.getProject_id()));
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
        s.put("user", userInfo);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }


   /* @Override
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

      *//*  List<Projects> projects =*//*
    }*/
}
