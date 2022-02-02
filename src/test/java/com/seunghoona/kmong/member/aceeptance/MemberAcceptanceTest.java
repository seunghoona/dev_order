package com.seunghoona.kmong.member.aceeptance;

import com.seunghoona.kmong.AcceptanceTest;
import com.seunghoona.kmong.member.dto.JoinRequest;
import com.seunghoona.kmong.member.dto.JoinResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("회원 기능")
public class MemberAcceptanceTest extends AcceptanceTest {

    public static final String URL_MEMBERS = "/members";
    public static final String 회원_아이디 = "testEmail@gmail.com";
    public static final String 회원_패스워드 = "1234566";
    private JoinRequest 회원;

    @Test
    void 회원가입_한다() {

        // when
        ExtractableResponse<Response> response = 회원가입_요청();

        JoinResponse joinResponse = response.as(JoinResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(joinResponse.getId()).isNotZero();
        assertThat(joinResponse.getEmail()).isNotEmpty();
    }

    @Test
    void 중복회원_가입시_예외() {
        회원가입_요청();
        ExtractableResponse<Response> response = 회원가입_요청();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    public ExtractableResponse<Response> 회원가입_요청() {
        회원 = JoinRequest.builder()
                .email(회원_아이디)
                .password(회원_패스워드)
                .build();
        return post(URL_MEMBERS, 회원);
    }


    public ExtractableResponse<Response> 로그인_요청() {
        return post(URL_MEMBERS, 회원);
    }
}

