package com.rar.utils;

import com.rar.model.Rewards;
import com.rar.repository.NominationsRepository;
import com.rar.repository.RewardsRepository;
import com.rar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CheckDisable {

    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private NominationsRepository nominationsRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Rewards> checkForDisable(String email) {


        String user_id = userRepository.getIdByEmail(email);

              nominationsRepository.setDisable(user_id);

              List<Rewards> list = nominationsRepository.getRewards();


        return list;
    }

}
