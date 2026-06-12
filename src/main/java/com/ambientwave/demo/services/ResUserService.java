package com.ambientwave.demo.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ambientwave.demo.models.ResUser;
import com.ambientwave.demo.repository.ResUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

@Service
public class ResUserService implements UserDetailsService {

    @Autowired
    private ResUserRepository resUserRepository; // dependency injection

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ResUser> user = resUserRepository.findByUsername(username);
        if (user.isPresent()) {
            ResUser resUser = user.get();
            return User.builder()
                    .username(resUser.getUsername())
                    .password(resUser.getPassword())
                    .roles(getRoles(resUser))
                    /*
                     * Note: Spring Security's .roles(...)
                     * automatically prepends ROLE_ to each entry,
                     * so "ADMIN" becomes the granted authority ROLE_ADMIN.
                     * Keep that in mind if you check roles elsewhere
                     * (e.g. hasRole("ADMIN") vs hasAuthority("ROLE_ADMIN"))
                     */
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }

    }

    private String[] getRoles(ResUser user) {
        if (user.getRole() == null) {
            return new String[] { "USER" };
        }
        return java.util.Arrays.stream(user.getRole().split(","))
                .map(String::trim)
                .toArray(String[]::new);
    }

}
