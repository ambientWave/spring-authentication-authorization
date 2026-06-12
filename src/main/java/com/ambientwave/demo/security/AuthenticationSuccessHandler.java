package com.ambientwave.demo.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        // if user tries to access a protected page without being logged in, they're
        // redirected to the login page. When they successfully log in, they're
        // redirected to the protected page they were originally trying to access
        // This is an important annotation that tells the AuthenticationSuccessHandler
        // to be aware of the saved request that triggered the authentication. It
        // ensures that after successful authentication, the user is redirected to the
        // originally requested URL rather than the default target URL.
        if (isAdmin) {
            setDefaultTargetUrl("/admin/index.html");

        } else {
            setDefaultTargetUrl("/user/index.html");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
