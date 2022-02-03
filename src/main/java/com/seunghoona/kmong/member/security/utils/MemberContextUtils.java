package com.seunghoona.kmong.member.security.utils;

import com.seunghoona.kmong.member.domain.Member;
import com.seunghoona.kmong.member.domain.MemberContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class MemberContextUtils {

    public static Member getEntity() {
        final MemberContext memberContext = getPrincipal();
        return memberContext.getMember();
    }

    public static Long getMemberId() {
        final MemberContext memberContext = getPrincipal();
        final Member member = memberContext.getMember();
        return member.getId();
    }

    private static MemberContext getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return (MemberContext) authentication.getPrincipal();
    }
}
