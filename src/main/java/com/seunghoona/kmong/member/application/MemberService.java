package com.seunghoona.kmong.member.application;

import com.seunghoona.kmong.member.domain.Email;
import com.seunghoona.kmong.member.domain.Member;
import com.seunghoona.kmong.member.domain.MemberContext;
import com.seunghoona.kmong.member.domain.MemberRepo;
import com.seunghoona.kmong.member.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepo memberRepo;

    public JoinResponse join(JoinRequest joinRequest) {
        Member member = joinRequest
                .toMemberCreate()
                .encode(passwordEncoder);
        return JoinResponse.of(memberRepo.save(member));
    }

    @Override
    public MemberContext loadUserByUsername(String enterEmail) throws UsernameNotFoundException {
        Member member = memberRepo.findByEmail(new Email(enterEmail))
                .orElseThrow(() -> new UsernameNotFoundException(enterEmail));
        return new MemberContext(member, member.getRoles());
    }
}
