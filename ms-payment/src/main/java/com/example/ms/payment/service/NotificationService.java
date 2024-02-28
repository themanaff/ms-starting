package com.example.ms.payment.service;

import com.example.ms.payment.client.NotificationClient;
import com.example.ms.payment.dto.client.request.NotificationRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotificationService {
    NotificationClient notificationClient;

    public void sendNotification( NotificationRequest notifications){
        notificationClient.sendNotification(notifications);
    }

}
