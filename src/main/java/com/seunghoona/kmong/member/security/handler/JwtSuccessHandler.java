package com.seunghoona.kmong.member.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seunghoona.kmong.member.domain.Token;
import com.seunghoona.kmong.member.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class JwtSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        TokenResponse tokenResponse = Token.create(authentication);
        String jsonToken = objectMapper.writeValueAsString(tokenResponse);
        response.getWriter().write(jsonToken);
    }
}
