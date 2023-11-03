package com.duonghai.shoppingonline.oauth2authorizationserver.socialclient.config;

import com.duonghai.shoppingonline.oauth2authorizationserver.entity.UserEntity;
import com.duonghai.shoppingonline.oauth2authorizationserver.socialclient.service.CustomOAuth2User;
import com.duonghai.shoppingonline.oauth2authorizationserver.socialclient.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SuccessAuthenticateHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
        UserEntity user = userService.createSocialUser(oauthUser);
        response.sendRedirect("http://127.0.0.1:8080/oauth2/authorization/social-google");
//        if(user.getId() == 0) {
//            response.sendRedirect("/error?username="+user.getUsername());
//        }
//        else {
//
//        }
    }

}
