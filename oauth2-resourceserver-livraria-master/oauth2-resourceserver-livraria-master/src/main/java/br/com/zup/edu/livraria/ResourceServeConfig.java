package br.com.zup.edu.livraria;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServeConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()
            .and()
                .csrf().disable()
                .formLogin().disable()
                .logout().disable()
                .rememberMe().disable()
                .httpBasic().disable()
                .requestCache().disable()
                .headers().frameOptions().deny()
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/autores/{id}").hasAuthority("SCOPE_livraria:read")
                .antMatchers(HttpMethod.GET, "/api/autores/{id}**").hasAuthority("SCOPE_livraria:read")
                .antMatchers(HttpMethod.POST, "/api/autores/**").hasAuthority("SCOPE_livraria:write")
                .antMatchers(HttpMethod.GET, "//api/livros/{id}").hasAuthority("SCOPE_livraria:read")
                .antMatchers(HttpMethod.GET, "/api/livros/{id}**").hasAuthority("SCOPE_livraria:read")
                .antMatchers(HttpMethod.POST, "/api/livros/**").hasAuthority("SCOPE_livraria:write")
                .anyRequest()
                .hasAuthority("SCOPE_livraria:write")
            .and()
                .oauth2ResourceServer()
                .jwt();
    }
}
