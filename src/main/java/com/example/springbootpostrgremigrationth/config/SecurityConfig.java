package com.example.springbootpostrgremigrationth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .and()
                .formLogin()
//                .successForwardUrl("/authenticated/adminPanel")
                .and()
                .logout().logoutSuccessUrl("/");
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("site_admin")
                .password("{bcrypt}$2a$12$rju313pqH9J3fxe7pJPhEeNM9d1/mJ65WZoGwL2dA.AcJQjG9BNcS")//site_admin
                .roles("ADMIN", "USER")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$FvvNvORnfB8W6OPu7DPm/.n.XiK3UNiXwDq5S3sz8wVhs2mEV6WGO")//user
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}
