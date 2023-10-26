package com.duonghai.shoppingonline.oauth2client;

import com.duonghai.shoppingonline.oauth2authorizationserver.Oauth2AuthorizationServerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-oauth2-client.properties")
public class Oauth2ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ClientApplication.class, args);
    }
}