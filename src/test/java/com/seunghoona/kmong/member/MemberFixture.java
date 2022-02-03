package com.seunghoona.kmong.member;

import com.seunghoona.kmong.member.domain.Email;
import com.seunghoona.kmong.member.domain.Member;
import com.seunghoona.kmong.member.domain.Password;

public class MemberFixture {
    public static final String 회원_이메일 = "test@gmail.com";
    public static final String 회원_패스워드 = "1234567";


    public static Member 회원() {
        return Member.builder()
                .id(1L)
                .email(new Email(회원_이메일))
                .password(new Password(회원_패스워드))
                .build();
    }
}
