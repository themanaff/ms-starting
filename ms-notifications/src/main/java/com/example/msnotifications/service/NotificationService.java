package com.example.msnotifications.service;

import com.example.msnotifications.dao.entity.NotificationsEntity;
import com.example.msnotifications.dao.repository.NotificationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotificationService {
    NotificationRepository notificationRepository;

    public void sendNotification(NotificationsEntity notifications){
        notificationRepository.save(notifications);
        log.info("ActionLog.sendNotification.successful: {}",notifications);
    }
}
