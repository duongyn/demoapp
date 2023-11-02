package com.duonghai.shoppingonline.oauth2authorizationserver.customclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource("classpath:application-oauth2-authorization-custom-server.properties")
@EnableJpaRepositories("com.duonghai.shoppingonline.oauth2authorizationserver.repository")
//@ComponentScan(basePackages = { "com.duonghai.shoppingonline.oauth2authorizationserver.customclient" })
@EntityScan("com.duonghai.shoppingonline.oauth2authorizationserver.*")
public class Oauth2AuthorizationCustomServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthorizationCustomServerApplication.class, args);

    }
}
