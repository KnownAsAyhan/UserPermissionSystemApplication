package com.example.userpermissionsystem.config;

import com.example.userpermissionsystem.user.entity.User;
import com.example.userpermissionsystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getPermissions().stream()
                        .map(p -> "ROLE_" + p.getName())  // example: ROLE_ADMIN
                        .toArray(String[]::new))
                .build();
    }
}
