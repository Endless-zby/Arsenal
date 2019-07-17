package com.zby.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity //开启加密功能
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    //设置加密的细节
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers( "/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable() ; //将全部加密功能禁止
    }

}
