package com.seunghoona.kmong.member.security.config;

import com.seunghoona.kmong.member.security.filter.AuthenticationFilter;
import com.seunghoona.kmong.member.security.filter.JwtTokenAuthenticationFilter;
import com.seunghoona.kmong.member.security.handler.AuthSuccessHandler;
import com.seunghoona.kmong.member.security.provider.AuthenticationProvider;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations(),
                        new AntPathRequestMatcher("/static/**"))
                .antMatchers("/docs/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 세션, csrf off
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin().disable();
        http.headers().frameOptions().disable();

        // jwt 필터 설정
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtTokenAuthenticationFilter(), AuthenticationFilter.class);

        // 권한 설정
        http
                .authorizeRequests()
                .antMatchers("/members", "/members/login","/static/**").permitAll()
                .antMatchers("members/logout").hasRole("USER")
                .antMatchers("/orders/**").hasRole("USER")
                .antMatchers("/products/**").hasRole("USER")
                .anyRequest().authenticated();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"));

    }

    @Bean
    public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter() {
        return new JwtTokenAuthenticationFilter();
    }

    @Bean
    public AbstractAuthenticationProcessingFilter jwtAuthenticationFilter() throws Exception {
        AuthenticationFilter jwtAuthenticationFilter = new AuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(successHandler());
        return jwtAuthenticationFilter;
    }

    @Bean
    public org.springframework.security.authentication.AuthenticationProvider jwtAuthenticationProvider() {
        return new AuthenticationProvider();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider());
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new AuthSuccessHandler();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
