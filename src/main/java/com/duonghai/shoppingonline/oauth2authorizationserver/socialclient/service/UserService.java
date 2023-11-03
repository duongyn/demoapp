package com.duonghai.shoppingonline.oauth2authorizationserver.socialclient.service;

import com.duonghai.shoppingonline.oauth2authorizationserver.entity.RoleEntity;
import com.duonghai.shoppingonline.oauth2authorizationserver.entity.UserEntity;
import com.duonghai.shoppingonline.oauth2authorizationserver.model.ERole;
import com.duonghai.shoppingonline.oauth2authorizationserver.model.Provider;
import com.duonghai.shoppingonline.oauth2authorizationserver.repository.RoleRepository;
import com.duonghai.shoppingonline.oauth2authorizationserver.repository.UserRepository;
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

    private String customUsernameFromEmail(String email) {
        return email != null ? email.split("@")[0] : "";
    }

    private UserEntity customInfo(Map<String, Object> attributes) {
        UserEntity user = new UserEntity();
        String attrs = attributes.toString();
        if(attrs.contains("github")) {
            user.setEmailAddress(attributes.get("email").toString());
            user.setFirstName(attributes.get("name").toString());
            user.setUsername(attributes.get("login").toString());
            user.setProvider(Provider.GITHUB);
        }
        else if(attrs.contains("google")){
            user.setEmailAddress(attributes.get("email").toString());
            user.setUsername(customUsernameFromEmail(user.getEmailAddress()));
            user.setFirstName(attributes.get("given_name").toString());
            user.setLastName(attributes.get("family_name").toString());
            user.setProvider(Provider.GOOGLE);
        }
        else {
            user.setProvider(Provider.LOCAL);
        }
        return user;
    }
}
