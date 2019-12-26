package com.rar.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="notifications")
@ApiModel(description = "All the details related to in-app notifications")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id",unique = true,nullable = false)
    @ApiModelProperty(notes = "The database generated notification ID")
    private long notificationId;

    @Column(name="user_id",nullable = false)
    @NotNull
    @ApiModelProperty(notes = "The user ID of particular employee")
    private Long userId;

    @Column(name="message",nullable = false)
    @NotEmpty
    @ApiModelProperty(notes = "The message displayed in notification")
    private String message;

    @Column(name="viewed",nullable = false)
    @ApiModelProperty(notes = "Shows if user has viewed message or not")
    private boolean viewed;

    @Column(name="date_created",nullable = false)
    @NotNull
    @ApiModelProperty(notes = "The date onn which row is created")
    private LocalDateTime dateCreated;

    public long getNotificationId() {
        return notificationId;
    }


    public Notifications() {
    }

    public Notifications(long notificationId, @NotNull Long userId, @NotEmpty String message, boolean viewed, @NotNull LocalDateTime dateCreated) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.viewed = viewed;
        this.dateCreated = dateCreated;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Notifications{" +
                "notificationId=" + notificationId +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", viewed=" + viewed +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
