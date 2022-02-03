package com.seunghoona.kmong.member.security.filter;

import com.seunghoona.kmong.member.application.MemberService;
import com.seunghoona.kmong.member.domain.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";

    @Autowired
    private Token token;

    @Autowired
    private MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String bearerToken = obtainToken(request);
            if (bearerToken != null && bearerToken.startsWith(BEARER)) {
                String email = token.payload(bearerToken);
                UserDetails userDetails = memberService.loadUserByUsername(email);
                AbstractAuthenticationToken authentication = createToken(userDetails, request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String obtainToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }

    private UsernamePasswordAuthenticationToken createToken(UserDetails userDetails, HttpServletRequest request) {
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
