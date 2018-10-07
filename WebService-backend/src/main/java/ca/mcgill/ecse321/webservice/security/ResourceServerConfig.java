package ca.mcgill.ecse321.webservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * Server hosting the protected resources.
 * Capable of accepting and responding to protected resources requets using access tokens
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/**
	 * Configures the access rules and request matchers for protected
	 * resources using HttpSecurity class
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		// Allow only ADMIN or USER to access any /api/** endpoints
		http.
		anonymous().disable()
		.requestMatchers().antMatchers("/api/**")
		.and().authorizeRequests()
		.antMatchers("/api/**").access("hasRole('ADMIN') or hasRole('USER')")
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
	
}
