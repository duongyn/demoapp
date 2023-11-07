package com.duonghai.shoppingonline.oauth2authorizationserver.customclient.config;

import com.duonghai.shoppingonline.entity.RoleEntity;
import com.duonghai.shoppingonline.entity.UserEntity;
import com.duonghai.shoppingonline.model.ERole;
import com.duonghai.shoppingonline.model.Provider;
import com.duonghai.shoppingonline.repository.RoleRepository;
import com.duonghai.shoppingonline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OnStartupApplication {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void createData() {
        createDefaultRoles();

    }

    public void createDefaultRoles() {
        if(roleRepository.findAll().isEmpty() && userRepository.findAll().isEmpty()) {
            RoleEntity roleAdmin = new RoleEntity(ERole.ROLE_ADMIN.name());
            RoleEntity roleUser = new RoleEntity(ERole.ROLE_USER.name());
            RoleEntity roleSocial = new RoleEntity(ERole.ROLE_SOCIAL.name());
            List<RoleEntity> roles = new ArrayList<>();
            roles.add(roleAdmin);
            roles.add(roleUser);
            roles.add(roleSocial);
            roleRepository.saveAll(roles);
            createAdminUser();
        }
    }

    public List<RoleEntity> getDefaultRoles() {
        return roleRepository.findAll().subList(0,2);
    }

    public void createAdminUser() {
        UserEntity entity = UserEntity.builder()
                .username("admin")
                .password(new BCryptPasswordEncoder().encode("admin"))
                .firstName("Duong")
                .lastName("Hai")
                .emailAddress("admin@gmail.com")
                .provider(Provider.LOCAL)
                .authorities(getDefaultRoles())
                .build();
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCreatedBy("application");
        userRepository.save(entity);
    }
}
