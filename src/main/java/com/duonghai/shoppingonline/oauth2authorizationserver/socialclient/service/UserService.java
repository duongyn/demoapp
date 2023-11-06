package com.duonghai.shoppingonline.oauth2authorizationserver.socialclient.service;

import com.duonghai.shoppingonline.entity.RoleEntity;
import com.duonghai.shoppingonline.entity.UserEntity;
import com.duonghai.shoppingonline.model.ERole;
import com.duonghai.shoppingonline.model.Provider;
import com.duonghai.shoppingonline.repository.RoleRepository;
import com.duonghai.shoppingonline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserEntity createSocialUser(CustomOAuth2User oAuth2User) {
        UserEntity user = customInfo(oAuth2User.getAttributes());
        setRolesForUser(user);
        if(userRepository.findByUsername(user.getUsername()).isEmpty()) {
            return userRepository.save(user);
        }
        else {
            UserEntity existUser = new UserEntity();
            existUser.setUsername(user.getUsername());
            existUser.setId(0);
            return existUser;
        }
    }

    private void setRolesForUser(UserEntity user) {
        RoleEntity roleUser = roleRepository.findByRole(ERole.ROLE_USER.name()).orElseThrow( () -> new RuntimeException("Not found Role User in database"));
        RoleEntity roleSocial = roleRepository.findByRole(ERole.ROLE_SOCIAL.name()).orElseThrow( () -> new RuntimeException("Not found Role OAUTH2 Social in database"));
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleUser);
        roles.add(roleSocial);
        user.setAuthorities(roles);
    }

    private UserEntity customInfo(Map<String, Object> attributes) {
        UserEntity user = new UserEntity();
        String attrs = attributes.toString();
        if(attrs.contains("github")) {
            user = UserEntity.builder()
                    .emailAddress(attributes.get("email").toString())
                    .firstName(attributes.get("name").toString())
                    .username(attributes.get("id").toString())
                    .provider(Provider.GITHUB)
                    .build();
        }
        else if(attrs.contains("google")){
            user = UserEntity.builder()
                    .emailAddress(attributes.get("email").toString())
                    .firstName(attributes.get("given_name").toString())
                    .lastName(attributes.get("family_name").toString())
                    .username(attributes.get("sub").toString())
                    .provider(Provider.GOOGLE)
                    .build();
        }
        else {
            user.setProvider(Provider.LOCAL);
        }
        return user;
    }
}
