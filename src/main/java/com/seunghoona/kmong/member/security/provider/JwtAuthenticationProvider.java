package com.seunghoona.kmong.member.security.provider;

import com.seunghoona.kmong.member.application.MemberService;
import com.seunghoona.kmong.member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final String enterEmail = obtainEmail(authentication);
        final String enterPassword = obtainPassword(authentication);
        final Member findMember = (Member) memberService.loadUserByUsername(enterEmail);

        verifyPassword(enterPassword, findMember.getPassword());

        return new UsernamePasswordAuthenticationToken(findMember, enterPassword, findMember.getAuthorities());
    }

    private String obtainEmail(Authentication authentication) {
        return (String) authentication.getPrincipal();
    }

    private String obtainPassword(Authentication authentication) {

        return String.valueOf(authentication.getCredentials());
    }

    private void verifyPassword(String enterPassword, String password) {
        if (!passwordEncoder.matches(enterPassword, password)) {
            throw new BadCredentialsException("잘못된 패스워드입니다.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
