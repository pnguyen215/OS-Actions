package com.phuocnguyen.app.sivaosactions.Configurer.AuthenticationRemoteTokenServiceSIVAOS;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sivaos.Configurer.SIVAJDBCConnectAutomation.SIVAJDBCConnectConfigurer;
import com.sivaos.Service.SIVAOSServiceImplement.BaseSIVAOSServiceImplement;
import com.sivaos.Service.SIVAOSServiceImplement.SIVAOSAuthenticationServiceImplement;
import com.sivaos.Service.SIVAOSServiceImplement.SIVAOSHttpRequestServiceImplement;
import com.sivaos.Service.SIVAOSServiceImplement.SIVAOSProjectServiceImplement;
import com.sivaos.Utils.ConfigGlobalUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Profile(value = {"dev", "local", "prod"})
public class OAuth2GlobalMethodConfigure extends GlobalMethodSecurityConfiguration {

    @Value("${sivaos.geo.time-zone}")
    private String timezone;

    @Bean
    public ObjectMapper objectMapper() {
        return ConfigGlobalUtils.configureDateTimeGlobal(timezone);
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @Primary
    @Resource
    public SIVAJDBCConnectConfigurer sivajdbcConnectConfigurer() {
        return new SIVAJDBCConnectConfigurer();
    }

    @Bean
    @Primary
    @Resource(name = "baseService")
    public BaseSIVAOSServiceImplement baseSIVAOSService() {
        return new BaseSIVAOSServiceImplement();
    }

    @Bean
    @Primary
    @Resource(name = "sivaOsAuthenticationService")
    public SIVAOSAuthenticationServiceImplement sivaosAuthenticationServiceImplement() {
        return new SIVAOSAuthenticationServiceImplement();
    }

    @Bean
    @Primary
    @Resource(name = "sivaOsHttpService")
    public SIVAOSHttpRequestServiceImplement sivaosHttpRequestServiceImplement() {
        return new SIVAOSHttpRequestServiceImplement();
    }

    @Bean
    @Primary
    @Resource(name = "projectService")
    public SIVAOSProjectServiceImplement sivaosProjectServiceImplement() {
        return new SIVAOSProjectServiceImplement();
    }

}
