//package com.blog.gateway.config;
//
//import com.blog.security.userDetails.CustomerUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
///**
// * @Date 2020/3/9 22:45
// * @Version 1.0
// **/
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private CustomerUserDetailsService customerUserDetailsService;
//
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Value("${oauth2.clientId}")
//    private String clientId;
//
//    @Value("${oauth2.secure}")
//    private String secure;
//
//    @Value("${oauth2.refreshTokenValiditySeconds}")
//    private Integer refreshTokenValiditySeconds;
//
//    @Value("${oauth2.accessTokenValiditySeconds}")
//    private Integer accessTokenValiditySeconds;
//
//
//    @Bean
//    public TokenStore tokenStore(){
//        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//        tokenStore.setPrefix("auth-token");
//        return tokenStore;
//    }
//
//    @Bean
//    public DefaultTokenServices tokenServices(){
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(tokenStore());
//        tokenServices.setSupportRefreshToken(true);
////        tokenServices.setReuseRefreshToken(true);
//        tokenServices.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
//        tokenServices.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
//        return tokenServices;
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.allowFormAuthenticationForClients();
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient(clientId)
//                .secret(passwordEncoder.encode(secure))
//                .authorizedGrantTypes("password","refresh_token");
//
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager)
//                .userDetailsService(customerUserDetailsService)
//                .tokenStore(tokenStore())
//                .tokenServices(tokenServices());
//    }
//}
