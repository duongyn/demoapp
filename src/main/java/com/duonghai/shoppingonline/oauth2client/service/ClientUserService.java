package com.duonghai.shoppingonline.oauth2client.service;

import com.duonghai.shoppingonline.dto.UserDTO;
import com.duonghai.shoppingonline.entity.RoleEntity;
import com.duonghai.shoppingonline.entity.UserEntity;
import com.duonghai.shoppingonline.model.ERole;
import com.duonghai.shoppingonline.model.Provider;
import com.duonghai.shoppingonline.repository.RoleRepository;
import com.duonghai.shoppingonline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class ClientUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserDTO convertToDTO(UserEntity user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .provider(user.getProvider())
                .username(user.getUsername())
                .emailAddress(user.getEmailAddress())
                .authorities(user.getAuthorities().stream().map(RoleEntity::getRole).toList())
                .build();
    }

    public UserEntity convertToEntity(UserDTO dto) {
        return UserEntity.builder()
                .username(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .provider(Provider.LOCAL)
                .emailAddress(dto.getEmailAddress())
                .password(new BCryptPasswordEncoder().encode(dto.getPassword()))
                .build();
    }

    public List<UserDTO> findActiveUsers() {
        return userRepository.findByEnabled(true).stream().map(this::convertToDTO).toList();
    }

    public UserDTO saveUser(UserDTO dto) {
        UserEntity entity = convertToEntity(dto);
        roleRepository.findByRole(ERole.ROLE_USER.name()).ifPresent(roleUser -> entity.setAuthorities(Collections.singleton(roleUser)));
        entity.setCreatedDate(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            entity.setCreatedBy(authentication.getName());
        }
        return convertToDTO(userRepository.save(entity));
    }
}
