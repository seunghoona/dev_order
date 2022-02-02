package com.seunghoona.kmong.member.application;

import com.seunghoona.kmong.member.domain.Member;
import com.seunghoona.kmong.member.domain.MemberRepo;
import com.seunghoona.kmong.member.dto.JoinRequest;
import com.seunghoona.kmong.member.dto.JoinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepo memberRepo;

    public JoinResponse join(JoinRequest joinRequest) {
        Member member = joinRequest
                .toMemberCreate()
                .encode(passwordEncoder);
        return JoinResponse.of(memberRepo.save(member));
    }
}
