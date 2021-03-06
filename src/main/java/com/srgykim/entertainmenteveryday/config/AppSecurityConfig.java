package com.srgykim.entertainmenteveryday.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import javax.sql.DataSource;

@Configuration
@EnableWebMvcSecurity
@ComponentScan({ "com.srgykim.entertainmenteveryday.*" })
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;  // see credentials from AppMvcConfig class

    /**
     * This method authenticates anyone who tries to visit "/author" URI.
     * See the notes from "fordata.sql" to clarify the SQL select statement.
     *
     * @param auth - authentication object
     * @throws Exception - exception object is thrown if authentication is not passed
     */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT author_id, password, enabled FROM AUTHORS WHERE author_id=?")
                .authoritiesByUsernameQuery(
                        "SELECT author_id, role from AUTHORS WHERE author_id=?");
    }

    /**
     * This method requests an author to login in order
     * to visit author admin panel ("/author" URI) to add articles, etc.
     *
     * @param http - http object that can restrict access to some URIs
     * @throws Exception - exception object is thrown if authentication is not passed
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/author").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                    .formLogin().loginPage("/author/login")
                    .usernameParameter("author_id").passwordParameter("password")
                .and()
                    .logout().logoutSuccessUrl("/author/login?logout")
                .and()
                .csrf();
    }
}
