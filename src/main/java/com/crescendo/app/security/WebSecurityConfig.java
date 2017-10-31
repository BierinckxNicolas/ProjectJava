package com.crescendo.app.security;


import org.springframework.beans.factory.annotation.Configurable;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Spring Web security configuratie
 *
 * @author Groep5
 *
 */
@Configurable
@EnableWebSecurity
// Modifying or overriding the default spring boot security.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Methode voor websecurity te overriden
     * ignore sommige request door verschillende patterns te specifieren
     * @param web websecurity
     * @throws Exception general exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {


        web.ignoring()
                .antMatchers("/static/app/**", "/", "/index.html", "/app/**", "/register", "/authenticate", "/favicon.ico", "/images/**", "/files/**");
    }

    /**
     * Deze methode dient om de httpconfiguratie van de applicatie te overriden
     * Authorisatie criteria kunnen hier gespecifieerd worden
     * @param http Httpsecurirty
     * @throws Exception general exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // starts authorizing configurations
                .authorizeRequests()
                // authenticate all remaining URLS
                .anyRequest().fullyAuthenticated().and()
                // adding JWT filter
                .addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
                // enabling the basic authentication
                .httpBasic().and()
                // configuring the session as state less. Which means there is
                // no session in the server
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // disabling the CSRF - Cross Site Request Forgery
                .csrf().disable();
    }

}