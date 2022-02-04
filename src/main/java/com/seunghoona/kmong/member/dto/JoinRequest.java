package com.seunghoona.kmong.member.dto;

import com.seunghoona.kmong.member.domain.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequest {

    @Email(message = "형식이 올바르지 않습니다.")
    @NotNull(message = "필수입니다.")
    private String email;

    @NotNull(message = "필수 입니다.")
    private String password;

    public Member.MemberCreate toMemberCreate() {
        return Member.of(email, password);
    }
}
