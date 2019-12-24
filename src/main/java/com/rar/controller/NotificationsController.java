package com.rar.controller;

import com.rar.model.Notifications;
import com.rar.model.Roles;
import com.rar.service.NotificationsService;
import com.rar.service.impl.CheckValidity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value="Notifications Management System")
public class NotificationsController {

    @Autowired
    private CheckValidity validity;

    @Autowired
    private NotificationsService notificationsService;

    /**
     *
     * @param token jwt token
     * @return list of unviewed notifications
     */
    @ApiOperation(value = "Get the list of unviewed notifications")
    @GetMapping("/getNewNotifications")
    public ResponseEntity<List<Notifications>> getNewNotifications(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return new ResponseEntity(notificationsService.getNewNotifications(email), HttpStatus.OK);
    }

    @ApiOperation(value = "Get the list of all notifications")
    @GetMapping("/getAllNotifications")
    public ResponseEntity<List<Notifications>> getAllNotifications(@RequestHeader(value = "Authorization") String token){
        String email=validity.check(token);
        return new ResponseEntity(notificationsService.getNAllNotifications(email),HttpStatus.OK);
    }
}
