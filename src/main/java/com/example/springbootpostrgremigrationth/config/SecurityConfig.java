package com.example.springbootpostrgremigrationth.config;

import com.example.springbootpostrgremigrationth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/onlyAdmin/**").hasRole("ADMIN")
                .and()
                .formLogin()
//                .successForwardUrl("/authenticated/adminPanel")
                .and()
                .logout().logoutSuccessUrl("/");
    }

//    @Bean//in memory
//    public UserDetailsService users() {
//        UserDetails admin = User.builder()
//                .username("site_admin")
//                .password("{bcrypt}$2a$12$rju313pqH9J3fxe7pJPhEeNM9d1/mJ65WZoGwL2dA.AcJQjG9BNcS")//site_admin
//                .roles("ADMIN", "USER")
//                .build();
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$FvvNvORnfB8W6OPu7DPm/.n.XiK3UNiXwDq5S3sz8wVhs2mEV6WGO")//user
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }
//
//    @Bean//in db
//    public JdbcUserDetailsManager users(DataSource dataSource){
//        UserDetails admin = User.builder()
//                .username("site_admin")
//                .password("{bcrypt}$2a$12$rju313pqH9J3fxe7pJPhEeNM9d1/mJ65WZoGwL2dA.AcJQjG9BNcS")//site_admin
//                .roles("ADMIN", "USER")
//                .build();
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$FvvNvORnfB8W6OPu7DPm/.n.XiK3UNiXwDq5S3sz8wVhs2mEV6WGO")//user
//                .roles("USER")
//                .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        if(jdbcUserDetailsManager.userExists(admin.getUsername())){
//            jdbcUserDetailsManager.deleteUser(admin.getUsername());
//        }
//        if(jdbcUserDetailsManager.userExists(user.getUsername())){
//            jdbcUserDetailsManager.deleteUser(user.getUsername());
//        }
//        jdbcUserDetailsManager.createUser(admin);
//        jdbcUserDetailsManager.createUser(user);
//        return jdbcUserDetailsManager;
//    }
//    ByDao

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}
