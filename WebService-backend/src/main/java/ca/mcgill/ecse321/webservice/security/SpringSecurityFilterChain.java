package ca.mcgill.ecse321.webservice.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Ensures the springSecurityFilterChain gets registered for every URL in the application
 */
public class SpringSecurityFilterChain extends AbstractSecurityWebApplicationInitializer {

}
