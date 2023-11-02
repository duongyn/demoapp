package com.duonghai.shoppingonline.oauth2authorizationserver.repository;

import com.duonghai.shoppingonline.oauth2authorizationserver.entity.RoleEntity;
import com.duonghai.shoppingonline.oauth2authorizationserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRole(String role);

}
