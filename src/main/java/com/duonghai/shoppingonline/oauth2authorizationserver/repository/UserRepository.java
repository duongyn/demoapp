package com.duonghai.shoppingonline.oauth2authorizationserver.repository;

import com.duonghai.shoppingonline.oauth2authorizationserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);
}
