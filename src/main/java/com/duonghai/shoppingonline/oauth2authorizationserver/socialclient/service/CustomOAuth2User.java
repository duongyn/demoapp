package com.duonghai.shoppingonline.oauth2authorizationserver.socialclient.service;

import com.duonghai.shoppingonline.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomOAuth2User implements OAuth2User {

    private OAuth2User oAuth2User;

    private UserEntity userEntity;

    public CustomOAuth2User(OAuth2User oAuth2User, UserEntity userEntity) {
        this.oAuth2User = oAuth2User;
        this.userEntity = userEntity;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.userEntity != null) {
            return userEntity.getAuthorities().stream()
                    .map(auth -> new SimpleGrantedAuthority(auth.getRole()))
                    .collect(Collectors.toSet());
        }
        else {
            return this.oAuth2User.getAuthorities();
        }
    }

    @Override
    public String getName() {
        return oAuth2User.getName();
    }

    public String getEmail() {
        return oAuth2User.<String>getAttribute("email");
    }
}
