package com.ambientwave.demo.web;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2) // Filters declared at the Dispatcher initializer should be registered first
public class SpringSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SpringSecurityWebApplicationInitializer() { // Just extending the default constructor
        super();
    }

    @Override
    protected boolean enableHttpSessionEventPublisher() { // Spring Security’s SessionRegistry to
                                                        // detect session timeouts.
        return true;
    }

    // Nothing else to implement. We will just use the defaults.
    // The extended initializer class will take care of registering the Spring
    // Security filter infrastructure.

}
