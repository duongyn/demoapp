package com.duonghai.shoppingonline.oauth2authorizationserver.customclient.service;

import com.duonghai.shoppingonline.oauth2authorizationserver.entity.UserEntity;
import com.duonghai.shoppingonline.oauth2authorizationserver.model.UserDetailsImpl;
import com.duonghai.shoppingonline.oauth2authorizationserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        return user.map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
