package com.rar.utils;

import com.rar.repository.*;
import com.rar.service.impl.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;

@Component
@Service
public class AskToNominate {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private NominationsRepository nominationsRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private UserRepository userRepository;

//Checking everyday if every manager has nominated someone for every reward and every project
    @Scheduled(cron = "0 0 12 * * ? ")
   // @Scheduled(cron="0 * * ? * *")
    public void SendEmailToManager() throws IOException, MessagingException {

        Long[] managers=managerRepository.getAllIds();
        Long[] rewards=rewardsRepository.getAllIds();
       LocalDate today=LocalDate.now();


            for(int j=0;j<rewards.length;j++) {

                String reward_name=rewardsRepository.getRewardName(rewards[j]);
                System.out.println(rewards[j]);
                LocalDate end_date = rewardsRepository.getEndDate(rewards[j]);
                System.out.println(end_date);

                if (end_date.minusDays(5).isBefore(today)) {

                    for (int i = 0; i < managers.length; i++) {

                        System.out.println(managers[i]);


                        String email=managerRepository.getEmail(managers[i]);
                        String name=userRepository.getName(email);
                        Long[] projects = managerRepository.getProjectsOfManager(managers[i]);

                        for(int k=0;k<projects.length;k++){
                            String project_name=projectRepository.getProjectName(projects[k]);

                            Long nomination=nominationsRepository.checkIfExists(projects[k],managers[i],rewards[j]);
                          if(nomination==0){
                              sendEmail.sendEmailWithoutAttachment(email,"Please nominate",
                                      "Hello, "+name.toUpperCase() +"You have not nominated any person from your team for project "+project_name+"for the reward "+reward_name+
                                      ". Kindly, nominate, as last date to nominate for this reward is "+end_date);
                          }
                        }


                    }
                }
            }
    }
}
