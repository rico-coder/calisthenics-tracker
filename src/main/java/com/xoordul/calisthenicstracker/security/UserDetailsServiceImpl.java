package com.xoordul.calisthenicstracker.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Author: Rico Krenn
 * Created: 6/3/2026
 * Version: 1.0
 * Description: Searches for the user (AI generated, but understood)
 * Project: 200_Abschlussprojekt
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminUsername)) {
            return User.withUsername(adminUsername)
                    .password("{noop}" + adminPassword)
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
