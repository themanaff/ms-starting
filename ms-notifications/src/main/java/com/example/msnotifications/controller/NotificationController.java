package com.example.msnotifications.controller;

import com.example.msnotifications.dao.entity.NotificationsEntity;
import com.example.msnotifications.dao.repository.NotificationRepository;
import com.example.msnotifications.service.NotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("notifications")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotificationController {
    NotificationService notificationService;

    @PostMapping("send")
    public void sendNotification(@RequestBody NotificationsEntity notifications){
        notificationService.sendNotification(notifications);
    }

}
