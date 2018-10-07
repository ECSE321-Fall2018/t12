package ca.mcgill.ecse321.webservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Desc: The server hosting the protected resources, capable of accepting and responding to 
 * protected resource requests using access tokens.
 **/
@Configuration 
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	private static String REALM = "12_REALM";
	private static final int ONE_DAY = 60 * 60 * 24;
	private static final int THIRTY_DAYS = 60 * 60 * 24 * 30; 
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private UserApprovalHandler userApprovalHandler;
 
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsrDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	    clients.inMemory()
	    .withClient("12Client1")
            .secret(passwordEncoder.encode("12SuperSecret"))
            .authorizedGrantTypes("password", "refresh_token")	// Authorized grant types
            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")  // Authorities
            .scopes("read", "write", "trust")					// Scope to which the client is limited
            //.accessTokenValiditySeconds(ONE_DAY)
            .accessTokenValiditySeconds(300)
            .refreshTokenValiditySeconds(THIRTY_DAYS);
	}
 
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
				 .userApprovalHandler(userApprovalHandler)
			     .userApprovalHandler(userApprovalHandler)
			     .authenticationManager(authenticationManager)
			     .userDetailsService(userDetailsService);
	}
 
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.realm(REALM);
	}

}


