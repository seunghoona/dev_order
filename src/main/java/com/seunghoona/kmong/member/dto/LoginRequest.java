package com.seunghoona.kmong.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Email(message = "형식이 올바르지 않습니다.")
    @NotNull(message = "필수입니다.")
    private String email;

    @NotNull(message = "필수 입니다.")
    private String password;
}
