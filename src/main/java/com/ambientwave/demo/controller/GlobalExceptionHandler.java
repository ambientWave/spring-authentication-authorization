package com.ambientwave.demo.controller;

import org.springframework.boot.web.error.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * Configures the embedded servlet container to handle HTTP errors.
 * Instead of dealing with Spring Boot's default /error path and BasicErrorController,
 * we map specific HTTP status codes directly to the URLs already defined in MainController.
 */
@Configuration
public class GlobalExceptionHandler implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        // When a 404 happens, the server will forward the request to /404.html
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
        
        // When a 403 happens, the server will forward the request to /403.html
        factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403.html"));
    }

}
