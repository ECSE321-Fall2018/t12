package ca.mcgill.ecse321.webservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Ensures the springSecurityFilterChain gets registered for every URL in the application
 */
public class SpringSecurityFilterChain extends AbstractSecurityWebApplicationInitializer {

	@Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange().anyExchange().permitAll();
        return http.build();
    }
	
}
