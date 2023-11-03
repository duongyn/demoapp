package com.duonghai.shoppingonline.oauth2client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/social").hasAnyAuthority("SCOPE_email")
					.anyRequest().authenticated())
				.exceptionHandling((exceptionHandling) ->
						exceptionHandling
								.accessDeniedPage("/errors/access-denied"))
				.oauth2Login(withDefaults())
				.oauth2Client(withDefaults());
		return http.build();
	}

}
