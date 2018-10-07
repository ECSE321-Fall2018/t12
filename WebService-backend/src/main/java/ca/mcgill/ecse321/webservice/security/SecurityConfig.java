package ca.mcgill.ecse321.webservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Configures OATH2
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ClientDetailsService clientDetailsService;
 
	/**
	 * Sets up an in-memory user store with two users and their roles
	 */
	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {		
        auth.inMemoryAuthentication()
        .withUser("admin").password("{noop}pass").roles("ADMIN","USER").and()
        .withUser("user").password("{noop}pass123").roles("USER");
    }
	
 
	/**
	 * Defines which URL paths should be secured.
	 * 
	 * NOTE: this method shall be executed first as it is annotated with HIGHEST_PRECEDENCE
	 */
    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected void configure(HttpSecurity http) throws Exception {
		http
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
	  	.authorizeRequests()
        .antMatchers("/signup").permitAll()			// "/signup" and "/oauth/token" will not be protected (publicly accessible)
	  	.antMatchers("/oauth/token").permitAll()
        .anyRequest().authenticated()				// All other end-points shall be authenticated
	  	.and()
	  	.httpBasic()
	  	.realmName(AuthorizationServerConfig.getRealm());
    }
 
	
    /**
     * 
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
    /**
     * Store the OAuth2 tokens in memory
     */
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
 
	/**
	 * Remembers the approval decisions by consulting existing tokens
	 */
	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}
	
	/**
	 * An ApprovalStore that works with an existing TokenStore
	 */
	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}
	
}