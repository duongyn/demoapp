package com.duonghai.shoppingonline.oauth2authorizationserver.socialclient.service;

import com.duonghai.shoppingonline.entity.UserEntity;
import com.duonghai.shoppingonline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user =  super.loadUser(userRequest);
        UserEntity entity = userRepository.findByUsername(user.getName()).orElse(null);

//        System.out.println(user.toString());
        return new CustomOAuth2User(user,entity);
    }
}
