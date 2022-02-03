package com.seunghoona.kmong.member.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static com.seunghoona.kmong.member.MemberFixture.회원_이메일;
import static com.seunghoona.kmong.member.MemberFixture.회원_패스워드;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepoTest {

    @Autowired
    private MemberRepo memberRepo;

    @Test
    void 사용자_정보_조회_및_권한조회() {
        // given
        Member member = Member.of(회원_이메일, 회원_패스워드)
                .encode(new BCryptPasswordEncoder());
        Member savedMember = memberRepo.save(member);

        // when
        Member findMember = memberRepo.findByEmail(new Email(savedMember.getEmail())).get();

        // then
        assertAll(()->{
            assertThat(findMember.getId()).isNotNull();
            assertThat(findMember.getEmail()).isEqualTo(회원_이메일);
            assertThat(findMember.getRoles()).isEqualTo(Set.of(new SimpleGrantedAuthority(RoleStatus.ROLE_USER.name())));
        });
    }
}