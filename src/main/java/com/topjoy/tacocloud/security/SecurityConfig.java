package com.topjoy.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder());
//            .inMemoryAuthentication()
//            .withUser("derek")
//            .password("{noop}123456")
//            .authorities("ROLE_USER")
//            .and()
//            .withUser("mirror")
//            .password("{noop}123456")
//            .authorities("ROLE_USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
//            .antMatchers("/register", "/login")
//            .permitAll()
//            .antMatchers("/", "/**")
//            .hasRole("USER");
            .antMatchers("/design", "/orders")
            .access("hasRole('USER')")
            .antMatchers("/", "/**")
            .permitAll()
            .and()
            .formLogin()
//            .loginPage("/login")
            .defaultSuccessUrl("/design", true)
            .and()
            .logout()
            .logoutSuccessUrl("/");
    }
}
