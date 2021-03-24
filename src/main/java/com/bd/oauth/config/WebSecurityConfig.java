package com.bd.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //resources 하위 정적자원들에 대해 보안구성에서 제외한다.
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //HttpSecurity - 특정 http 요청에 대해 웹 기반 보안을 구성할 수 있다.
        //기본적으로 모든 요청에 적용되니만 requestMatcher또는 유사한 방법을 사용하여 제한 할 수 있다.

        //authorizeRequests - RequestMatcher 구현(url 패턴)을 사용하여 HttpServletReqeust를 기반으로 접근을 제한 할 수 있다.
        //antMatchers - 제공된 ant pattern과 일치할때만 호출되도록 HttpSecurity를 호출할 수 있다.
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                //.antMatchers("/api/**").authenticated()
                //.anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().permitAll();

        http.csrf().disable();
    }
}
