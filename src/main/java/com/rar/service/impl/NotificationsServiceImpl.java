package com.rar.service.impl;

import com.rar.model.Notifications;
import com.rar.model.Roles;
import com.rar.repository.*;
import com.rar.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.rar.utils.Constants.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class NotificationsServiceImpl implements NotificationsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public void newNominationReceived(String reward,String manager){

        Long[] adminId=userRepository.getAdmins();
        for(int i=0;i<adminId.length;i++){
            Notifications notifications=new Notifications();

            notifications.setUserId(adminId[i]);
            notifications.setDateCreated(LocalDateTime.now());
            notifications.setViewed(false);
            notifications.setMessage(reward+" has received new nomination by "+manager);
            notificationsRepository.save(notifications);
        }
    }

    @Override
    public void endDatePassed(Long rewardId) {
        String reward=rewardsRepository.getRewardName(rewardId);
        Long[] adminId=userRepository.getAdmins();
        for(int i=0;i<adminId.length;i++){
            Notifications notifications=new Notifications();

            notifications.setUserId(adminId[i]);
            notifications.setDateCreated(LocalDateTime.now());
            notifications.setViewed(false);
            notifications.setMessage(reward+" end date has passed. Publish a winner.");
            notificationsRepository.save(notifications);
        }
    }

    @Override
    public void awardStatusChanged(Long rewardId, int awardStatus,String reason) {
        String reward=rewardsRepository.getRewardName(rewardId);
        Long[] managers=managerRepository.getAllIds();
        for(int i=0;i<managers.length;i++){
            Notifications notifications=new Notifications();

            notifications.setUserId(managers[i]);
            notifications.setDateCreated(LocalDateTime.now());
            notifications.setViewed(false);
            if(awardStatus==ROLLED_OUT)
                notifications.setMessage("A new reward "+reward+" has been rolled out.");
            else
                if(awardStatus==DISCONTINUED)
                    notifications.setMessage("Reward "+reward+" has been discontinued because "+reason);
            notificationsRepository.save(notifications);
        }

    }

    @Override
    public void winnerPublished(String rewardName, String winnerName) {
        String[] allEmails=userRepository.getAllEmails();
        for(int i=0;i<allEmails.length;i++){
            Long userId=userRepository.getIdByEmail(allEmails[i]);
            Notifications notifications=new Notifications();

            notifications.setUserId(userId);
            notifications.setDateCreated(LocalDateTime.now());
            notifications.setViewed(false);
            notifications.setMessage(winnerName+" has been awarded for the reward "+rewardName);
            notificationsRepository.save(notifications);

        }

    }

    @Override
    public void newMemberAdded(Long userId,Long projectId){
        String project=projectRepository.getProjectName(projectId);
        String userName=userRepository.getUserName(userId);
        Long[] all=projectRepository.getEveryoneInProject(projectId);
        for(int i=0;i<all.length;i++){
            Notifications notifications=new Notifications();

            notifications.setUserId(all[i]);
            notifications.setDateCreated(LocalDateTime.now());
            notifications.setViewed(false);
            notifications.setMessage(userName+" has been added in project "+project);
            notificationsRepository.save(notifications);

        }
    }

    @Override
    public void MemberDeletedFromProject(Long userId,Long projectId){
        String project=projectRepository.getProjectName(projectId);
        String userName=userRepository.getUserName(userId);
        Long[] all=projectRepository.getEveryoneInProject(projectId);
        for(int i=0;i<all.length;i++){
            Notifications notifications=new Notifications();
            notifications.setUserId(all[i]);
            notifications.setDateCreated(LocalDateTime.now());
            notifications.setViewed(false);
            notifications.setMessage(userName+" has been removed from project "+project);
            notificationsRepository.save(notifications);

        }
    }

    @Override
    public List<Notifications> getNewNotifications(String email) {

        List<Notifications> notifications=notificationsRepository.getUnviewedNotifications(userRepository.getIdByEmail(email));
        for(int i=0;i<notifications.size();i++){
            Long notificationId=notifications.get(i).getNotificationId();
            notificationsRepository.updateViewed(notificationId);
            System.out.println("viewed updated true for "+notificationId);
        }
        return notifications;

    }


}
