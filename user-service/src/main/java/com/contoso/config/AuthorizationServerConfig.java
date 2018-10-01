package com.contoso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
    @Autowired
    private TokenStore tokenStore;
    	
    @Autowired
	@Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
        		.tokenKeyAccess("permitAll()")
        		.checkTokenAccess("isAuthenticated()")
        		.allowFormAuthenticationForClients();
    }
	
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
	    	   .inMemory()
	           .withClient("acme")
	           .secret(passwordEncoder.encode("acmesecret"))
	           .authorizedGrantTypes("authorization_code", "refresh_token", "password")
	           .scopes("openid").autoApprove(true)
	           .redirectUris("http://localhost:8787/login")
	           .accessTokenValiditySeconds(7200)
	           .refreshTokenValiditySeconds(50000);
   }
        
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
	            .tokenStore(tokenStore)
	            .authenticationManager(authenticationManager);
    }
       
}