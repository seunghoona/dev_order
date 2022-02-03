package com.seunghoona.kmong.member.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Getter
public class MemberContext extends User {

    public MemberContext(@NotNull Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getEmail(), member.getPassword(), authorities);
    }
}
