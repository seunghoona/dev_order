package com.seunghoona.kmong.member.dto;

import com.seunghoona.kmong.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinResponse {

    private Long id;
    private String email;

    public static JoinResponse of(Member savedMember) {
        return new JoinResponse(savedMember.getId(), savedMember.getEmail());
    }
}
