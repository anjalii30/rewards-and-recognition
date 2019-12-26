package com.rar.repository;

import com.rar.model.Notifications;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificationsRepository extends CrudRepository<Notifications, Long> {

    @Query(value="select * from notifications where user_id=?1  and viewed=false",nativeQuery = true)
    List<Notifications> getUnviewedNotifications(Long userId);

    @Query(value="select count(notification_id) from notifications where user_id=?1 and viewed=false",nativeQuery = true)
    Long getCountOfUnviewed(Long userId);

    @Query(value="select * from notifications where user_id=?1 ",nativeQuery = true)
    List<Notifications> getAllNotifications(Long userId);

    @Modifying
    @Transactional
    @Query(value="update notifications set viewed=true where notification_id=?1",nativeQuery = true)
    void updateViewed(Long notificationId);
}
