package com.seunghoona.kmong.member.dto;

import com.seunghoona.kmong.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequest {
    private String email;
    private String password;

    public Member.MemberCreate toMemberCreate() {
        return Member.of(email, password);
    }
}
