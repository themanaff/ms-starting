package com.example.ms.payment.client;

import com.example.ms.payment.dto.client.request.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-notifications",url = "${urls.ms-notifications}")

public interface NotificationClient {
    @PostMapping("send")
    void sendNotification(@RequestBody NotificationRequest notifications);
}
