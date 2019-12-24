package com.rar.service;

import com.rar.model.Notifications;
import com.rar.model.Roles;

import java.util.List;

public interface NotificationsService {

    void newNominationReceived(String rewardName, String managerName);

    List<Notifications> getNewNotifications(String email);

    void newMemberAdded(Long userId,Long projectId);

    void MemberDeletedFromProject(Long userId,Long projectId);

    void endDatePassed(Long rewardId);

    void awardStatusChanged(Long rewardId, int awardStatus,String reason);

    void winnerPublished(String rewardName, String winnerName);
}
