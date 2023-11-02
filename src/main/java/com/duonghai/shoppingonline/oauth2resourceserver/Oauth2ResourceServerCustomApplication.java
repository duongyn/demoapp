package com.duonghai.shoppingonline.oauth2resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@PropertySource("classpath:application-oauth2-resource-custom-server.properties")
public class Oauth2ResourceServerCustomApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ResourceServerCustomApplication.class, args);
    }
}
