package com.rar.service.impl;

import com.rar.model.Designation;
import com.rar.model.Projects;
import com.rar.model.UserInfo;
import com.rar.repository.UserRepository;
import com.rar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
        for(Iterator<Projects> projectsIterator = userInfo.getProjects().iterator();projectsIterator.hasNext();){
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
        }

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



}
