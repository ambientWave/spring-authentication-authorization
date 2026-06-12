package com.ambientwave.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.ambientwave.demo.services.ResUserService;

import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;

@Configuration
// very important annotation to enable security and customize it
@EnableWebSecurity
public class SpringSecurityConfig {

        @Autowired
        private ResUserService resUserService; // dependency injection

        public SpringSecurityConfig() {
                super();
        }

        @Bean
        public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/", "/index.html", "/register", "/register/**",
                                                                "/login.html", "/login", "/error", "/404.html", "/403.html",
                                                                "/css/**",
                                                                "/favicon.ico")
                                                .permitAll()
                                                .requestMatchers("/admin/**").hasRole("ADMIN") // order matters. only
                                                                                               // users that have both
                                                                                               // roles can access this
                                                                                               // page
                                                .requestMatchers("/user/**").hasRole("USER")
                                                .requestMatchers("/shared/**").hasAnyRole("USER", "ADMIN")
                                                .anyRequest().authenticated()) // anything else needs to be logged in
                                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                                                .loginPage("/login.html")
                                                .loginProcessingUrl("/login")
                                                .successHandler(new AuthenticationSuccessHandler())
                                                .failureUrl("/login-error.html").permitAll()) // to
                                                                                              // customize
                                                                                              // the login
                                // page
                                .exceptionHandling(handling -> handling
                                                .accessDeniedPage("/403.html"))
                                .csrf(csrf -> csrf
                                                .ignoringRequestMatchers("/register")); // ignoringRequestMatchers("/register")
                                                                                        // tells the CSRF filter to skip
                                                                                        // CSRF token validation for
                                                                                        // that path
                return http.build(); // every chain should be built at the end
        }

        // @Bean
        // public InMemoryUserDetailsManager userDetailsService() {
        // return new InMemoryUserDetailsManager(
        // User.withUsername("jim").password("{noop}demo").roles("ADMIN").build(), //
        // {noop} is a
        // // prefix
        // // used to
        // // indicate that
        // // the password
        // // is not
        // // encoded
        // User.withUsername("bob").password(
        // "{bcrypt}$2a$10$Nj2EXf1.ydgATcY2mWzMaOZQ7K8c9WeM6W.7eT9y9ZoXBy8z1rUDG")
        // .roles("USER").build(),
        // User.withUsername("ted").password("{pbkdf2}B7wEiQ==").roles("USER",
        // "ADMIN").build());
        // }

        public UserDetailsService userDetailsService() {
                return resUserService;

        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService());
                provider.setPasswordEncoder(passwordEncoder());
                return provider;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}