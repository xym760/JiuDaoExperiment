package com.jiudao.experiment.config;

import com.jiudao.experiment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new SecurityUserService(userService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin() //启用默认登录页
                .loginPage("/login")//自定义登录页
                .and()
                .rememberMe()
                .tokenValiditySeconds(2419200)//四周有效
                .key("jiudaoKey")
                .and()
                .httpBasic()
                .realmName("jiudao")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/index/me").access("hasRole('ROLE_GUEST') and hasIpAddress('0:0:0:0:0:0:0:1')")
                .antMatchers(HttpMethod.POST, "/index").hasRole("GUEST")
                .anyRequest().permitAll()
                .and()
                .csrf()
                .disable()
                .requiresChannel()
                .antMatchers("index/form").requiresSecure()//需要HTTPS
                .antMatchers("/").requiresInsecure();//不需要HTTPS
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

}
