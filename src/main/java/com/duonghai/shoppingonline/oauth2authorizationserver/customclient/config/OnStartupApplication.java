package com.duonghai.shoppingonline.oauth2authorizationserver.customclient.config;

import com.duonghai.shoppingonline.oauth2authorizationserver.entity.RoleEntity;
import com.duonghai.shoppingonline.oauth2authorizationserver.model.ERole;
import com.duonghai.shoppingonline.oauth2authorizationserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OnStartupApplication {

    @Autowired
    private RoleRepository roleRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadRoleData() {
        if(roleRepository.findAll().isEmpty()) {
            RoleEntity roleAdmin = new RoleEntity(ERole.ROLE_ADMIN.name());
            RoleEntity roleUser = new RoleEntity(ERole.ROLE_USER.name());
            List<RoleEntity> roles = new ArrayList<>();
            roles.add(roleAdmin);
            roles.add(roleUser);
            roleRepository.saveAll(roles);
        }
    }
}
