package com.seunghoona.kmong.member.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
