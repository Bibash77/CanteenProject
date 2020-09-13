package com.bibash.CanteenProject.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableResourceServer
public class ResourceConfigAdapter extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("resources.demo.com").stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
           .antMatchers("/oauth/token")
           .permitAll()
           .antMatchers(HttpMethod.POST ,"/v1/user/register").permitAll()
           .antMatchers("/v1/**")
           .authenticated()
           .and()
           .logout()
           .logoutRequestMatcher(new AntPathRequestMatcher("/oauth/logout"));
    }
}
