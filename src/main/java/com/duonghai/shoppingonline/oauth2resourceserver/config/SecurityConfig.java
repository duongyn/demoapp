package com.duonghai.shoppingonline.oauth2resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		Map<String, AuthenticationManager> authenticationManagers = new HashMap<>();
		List<String> issuers = new ArrayList<>();
		issuers.add("http://localhost:9000");
		issuers.add("http://localhost:9001");
		issuers.forEach(issuer -> addManager(authenticationManagers, issuer));
		JwtIssuerAuthenticationManagerResolver authenticationManagerResolver = new JwtIssuerAuthenticationManagerResolver(authenticationManagers::get);

		return http.csrf().disable()
				.authorizeHttpRequests(auth -> auth
						.anyRequest().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2
						.authenticationManagerResolver(authenticationManagerResolver))
				.build();
	}


	private void addManager(Map<String, AuthenticationManager> authenticationManagers, String issuer) {
		JwtAuthenticationProvider authenticationProvider = new JwtAuthenticationProvider
				(JwtDecoders.fromIssuerLocation(issuer));
		authenticationProvider.setJwtAuthenticationConverter(jwtAuthenticationConverter());
		authenticationManagers.put(issuer, authenticationProvider::authenticate);
	}

	@Bean
	JwtAuthenticationConverter jwtAuthenticationConverter(){
		JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
		grantedAuthoritiesConverter.setAuthorityPrefix("");

		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
		return jwtAuthenticationConverter;
	}
}
