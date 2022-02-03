package com.seunghoona.kmong.member.security.token;

import com.seunghoona.kmong.member.domain.MemberContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationToken extends UsernamePasswordAuthenticationToken {

    public AuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public AuthenticationToken(MemberContext memberContext) {
        this(memberContext.getUsername(), memberContext.getPassword(), memberContext.getAuthorities());
    }

    private AuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
