package br.com.autenticacao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String GRANT_TYPE_PASSWORD = "password";
    private static final String GRANT_TYPE_CLIENT = "client_credentials";
    private static final String[] SCOPE = {"read", "write", "trust"};
    private static final int UM_DIA_EM_SEGUNDOS = 86400;

    @Value("${keys.private}")
    private String privateKey;
    @Value("${app-config.oauth-clients.front-apps.client}")
    private String appClient;
    @Value("${app-config.oauth-clients.front-apps.secret}")
    private String appClientPassword;
    @Value("${app-config.oauth-clients.external-api.client}")
    private String apiClient;
    @Value("${app-config.oauth-clients.external-api.secret}")
    private String apiClientPassword;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(privateKey);
        return tokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(appClient)
                .secret(appClientPassword)
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD)
                .scopes(SCOPE)
                .accessTokenValiditySeconds(UM_DIA_EM_SEGUNDOS)
                .and()
                .withClient(apiClient)
                .secret(apiClientPassword)
                .authorizedGrantTypes(GRANT_TYPE_CLIENT)
                .scopes(SCOPE)
                .accessTokenValiditySeconds(UM_DIA_EM_SEGUNDOS);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
    }
}
