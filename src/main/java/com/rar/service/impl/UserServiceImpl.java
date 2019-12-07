package com.rar.service.impl;

import com.rar.model.*;
import com.rar.repository.UserRepository;
import com.rar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        save(userInfo);

        long id = userInfo.getId();
        for (Iterator<Designation> designationIterator = userInfo.getDesignation().iterator(); designationIterator.hasNext(); ) {
            Designation designation = designationIterator.next();
            userRepository.insertUserDesignation(id, designation.getDid());
        }

        boolean managingProject = userInfo.getManagingProject();
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
        }


    }
}
