package com.duonghai.shoppingonline.oauth2authorizationserver.socialclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource("classpath:application-oauth2-authorization-sso-server.properties")
@EnableJpaRepositories("com.duonghai.shoppingonline.repository")
//@ComponentScan(basePackages = { "com.duonghai.shoppingonline.oauth2authorizationserver.socialclient" })
@EntityScan("com.duonghai.shoppingonline.*")
public class OAuth2AuthorizationSsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2AuthorizationSsoServerApplication.class, args);
    }
}
