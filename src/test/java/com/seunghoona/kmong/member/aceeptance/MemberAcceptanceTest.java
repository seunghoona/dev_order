package com.seunghoona.kmong.member.aceeptance;

import com.seunghoona.kmong.AcceptanceTest;
import com.seunghoona.kmong.member.dto.JoinRequest;
import com.seunghoona.kmong.member.dto.JoinResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.*;

@DisplayName("회원 기능")
public class MemberAcceptanceTest extends AcceptanceTest {

    public static final String URL_MEMBERS = "/members";
    private JoinRequest 회원;

    @Test
    void 회원가입_한다() {
        // given
        회원 = JoinRequest.builder()
                .email("testEmail@gmail.com")
                .password("1234566")
                .build();
        // when
        ExtractableResponse<Response> response = 회원가입_요청();

        JoinResponse joinResponse = response.as(JoinResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(joinResponse.getId()).isNotZero();
        assertThat(joinResponse.getEmail()).isNotEmpty();
    }

    public ExtractableResponse<Response> 회원가입_요청() {
        return post(URL_MEMBERS, 회원);
    }
}

