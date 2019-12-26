package com.rar.service;

import com.rar.model.Notifications;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NotificationsService {

    void newNominationReceived(String rewardName, String managerName);

    ResponseEntity<List<Notifications>> getNewNotifications(String email);

    void newMemberAdded(Long userId,Long projectId);

    void memberDeletedFromProject(Long userId,Long projectId);

    void endDatePassed(Long rewardId);

    void awardStatusChanged(Long rewardId, int awardStatus,String reason);

    void winnerPublished(String rewardName, String winnerName);

    ResponseEntity<List<Notifications>> getAllNotifications(String email);
}
