package com.seunghoona.kmong.member.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seunghoona.kmong.member.dto.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private ObjectMapper objectMapper;

    private final boolean postOnly = true;

    public AuthenticationFilter() {
        super(new AntPathRequestMatcher("/members/login", HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        validIsNotPost(request);
        LoginRequest loginRequest = fromMember(request);
        Authentication token = createToken(loginRequest);
        return getAuthenticationManager().authenticate(token);
    }

    private void validIsNotPost(HttpServletRequest request) {
        if (this.postOnly && !request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
    }

    private LoginRequest fromMember(HttpServletRequest request) throws IOException {
        return objectMapper.readValue(request.getReader(), LoginRequest.class);
    }

    private AbstractAuthenticationToken createToken(LoginRequest loginRequest) {
        return new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
    }

}
