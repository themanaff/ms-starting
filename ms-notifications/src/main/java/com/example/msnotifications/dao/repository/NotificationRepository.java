package com.example.msnotifications.dao.repository;

import com.example.msnotifications.dao.entity.NotificationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationsEntity,Long> {

}
