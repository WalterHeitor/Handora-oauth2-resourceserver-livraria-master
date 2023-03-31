package br.com.zup.edu.livraria;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServeConfig extends WebSecurityConfigurerAdapter {
    private static final String READ = "SCOPE_livraria:read";
    private static final String WRITE = "SCOPE_livraria:write";

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
                .antMatchers(HttpMethod.GET, "/api/autores/{id}").hasAuthority(READ)
                .antMatchers(HttpMethod.GET, "/api/autores/{id}**").hasAuthority(READ)
                .antMatchers(HttpMethod.POST, "/api/autores/**").hasAuthority(WRITE)
                .antMatchers(HttpMethod.GET, "//api/livros/{id}").hasAuthority(READ)
                .antMatchers(HttpMethod.GET, "/api/livros/{id}**").hasAuthority(READ)
                .antMatchers(HttpMethod.POST, "/api/livros/**").hasAuthority(WRITE)
                .antMatchers(DELETE, "/api/autores/**").hasAuthority(WRITE)
                .anyRequest()
                .hasAuthority(WRITE)
            .and()
                .oauth2ResourceServer()
                .jwt();
    }
}
