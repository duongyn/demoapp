package com.duonghai.shoppingonline.oauth2client.config;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/social").hasAnyAuthority("ROLE_SOCIAL")
						.requestMatchers("/users").hasAnyAuthority("ROLE_ADMIN")
					.anyRequest().authenticated())
				.exceptionHandling((exceptionHandling) ->
						exceptionHandling
								.accessDeniedPage("/errors/access-denied"))
				.oauth2Login(oauth2Login -> {
					oauth2Login.userInfoEndpoint(userInfo -> userInfo
							.oidcUserService(this.oidcUserService())); })
				.oauth2Client(withDefaults());
		return http.build();
	}

	private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
		final OidcUserService delegate = new OidcUserService();

		return (userRequest) -> {
			OidcUser oidcUser = delegate.loadUser(userRequest);
			OAuth2AccessToken accessToken = userRequest.getAccessToken();
			// OidcIdToken idToken = userRequest.getIdToken();
			Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
			try {
				JWT jwt = JWTParser.parse(accessToken.getTokenValue());
				JWTClaimsSet claimSet = jwt.getJWTClaimsSet();
				Collection<String> userAuthorities = claimSet.getStringListClaim("authorities");
				mappedAuthorities.addAll(userAuthorities.stream()
						.map(SimpleGrantedAuthority::new)
						.toList());
			} catch (ParseException e) {
				System.err.println("Error OAuth2UserService: " + e.getMessage());
			}
			oidcUser = new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
			return oidcUser;
		};
	}

}
