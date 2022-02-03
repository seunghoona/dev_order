package com.seunghoona.kmong.member.aceeptance;

import com.seunghoona.kmong.AcceptanceTest;
import com.seunghoona.kmong.member.dto.JoinRequest;
import com.seunghoona.kmong.member.dto.JoinResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("회원 기능")
public class MemberAcceptanceTest extends AcceptanceTest {

    private static final String URL_MEMBERS = "/members";
    private static final String 회원_아이디 = "testEmail@gmail.com";
    private static final String 회원_패스워드 = "1234566";

    public static JoinRequest 회원;

    @Test
    void 회원가입() {

        // when
        ExtractableResponse<Response> response = 회원가입_요청();


        // then
        JoinResponse joinResponse = response.as(JoinResponse.class);
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(joinResponse.getId()).isNotZero();
        assertThat(joinResponse.getEmail()).isNotEmpty();
    }

    @Test
    void 중복계정_가입시_예외() {
        // given
        회원가입_됨();

        // when
        ExtractableResponse<Response> response = 회원가입된_계정으로_회원가입_요청();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void 로그인시_토큰발급() {
        // given
        회원가입_됨();

        // when
        ExtractableResponse<Response> response = 로그인_요청();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("token")).isNotBlank();
        assertThat(response.header("content-type")).startsWith(MediaType.APPLICATION_JSON_VALUE);
    }


/*    @Test
    void 로그아웃() {
        // given
        회원가입_요청();
        로그인_요청();

        // when
        ExtractableResponse<Response> response = get("members/logout");


        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
    }*/

    public static ExtractableResponse<Response> 회원가입_요청() {
        회원 = JoinRequest.builder()
                .email(회원_아이디)
                .password(회원_패스워드)
                .build();
        return post(URL_MEMBERS, 회원);
    }

    private static void 회원가입_됨() {
        회원가입_요청();
    }

    public static ExtractableResponse<Response> 회원가입된_계정으로_회원가입_요청() {
        return 회원가입_요청();
    }

    public static ExtractableResponse<Response> 로그인_요청() {
        return post(URL_MEMBERS + "/login", 회원);
    }

    public static String 토큰생성() {
        회원가입_됨();
        return 로그인_요청().jsonPath().getString("token");
    }
}

