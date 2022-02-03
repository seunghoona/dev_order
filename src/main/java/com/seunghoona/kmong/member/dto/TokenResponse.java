package com.seunghoona.kmong.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class TokenResponse implements Serializable {
    @JsonProperty
    private String token;
}
