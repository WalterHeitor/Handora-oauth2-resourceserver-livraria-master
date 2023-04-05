package br.com.zup.edu.livrariaclient;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class ClientSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.cors()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .rememberMe().disable()
                .formLogin().disable()
                .logout().disable()
                .requestCache().disable()
                .headers().frameOptions().deny()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().permitAll()
        ;
        // @formatter:on
    }
}
