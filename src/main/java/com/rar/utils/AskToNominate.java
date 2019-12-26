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

//Checking everyday if every manager has nominated someone for every reward and every project (4 days earlier it starts mailing)
    @Scheduled(cron = "0 0 12 * * ? ")
   // @Scheduled(cron="0 * * ? * *")
    public void sendEmailToManager() throws IOException, MessagingException {

        Long[] managers=managerRepository.getAllIds();
        Long[] rewards=rewardsRepository.getAllIds();
       LocalDate today=LocalDate.now();


            for(int j=0;j<rewards.length;j++) {

                String rewardName=rewardsRepository.getRewardName(rewards[j]);
                LocalDate endDate = rewardsRepository.getEndDate(rewards[j]);

                if (endDate.minusDays(5).isBefore(today) && endDate.isAfter(today)) {

                    for (Long manager : managers) {


                        String email = managerRepository.getEmail(manager);
                        String name = userRepository.getName(email);
                        Long[] projects = managerRepository.getProjectsOfManager(manager);

                        for (Long project : projects) {
                            String projectName = projectRepository.getProjectName(project);

                            Long nomination = nominationsRepository.checkIfExists(project, manager, rewards[j]);
                            if (nomination == 0) {
                                sendEmail.sendEmailWithoutAttachment(email, "Please nominate",
                                        "Hello, " + name.toUpperCase() + "You have not nominated any person from your team for project " + projectName + "for the reward " + rewardName +
                                                ". Kindly, nominate, as last date to nominate for this reward is " + endDate);
                            }
                        }


                    }
                }
            }
    }
}
