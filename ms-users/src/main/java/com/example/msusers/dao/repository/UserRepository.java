package com.example.msusers.dao.repository;


import com.example.msusers.dao.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UsersEntity,Long>{
    Optional<UsersEntity> findByIdAndStatus(Long id,Integer status);
}
