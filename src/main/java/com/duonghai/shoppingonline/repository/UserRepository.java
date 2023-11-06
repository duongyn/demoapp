package com.duonghai.shoppingonline.repository;

import com.duonghai.shoppingonline.dto.UserDTO;
import com.duonghai.shoppingonline.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    List<UserDTO> findBy(Class<UserDTO> userDTOClass);

    List<UserEntity> findByEnabled(boolean enable);
}
