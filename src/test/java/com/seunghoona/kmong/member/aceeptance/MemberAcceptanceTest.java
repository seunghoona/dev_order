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
    public static final String 회원_아이디 = "testEmail@gmail.com";
    public static final String 회원_패스워드 = "1234566";
    private JoinRequest 회원;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        super.setUp(restDocumentation);
        회원 = JoinRequest.builder()
                .email(회원_아이디)
                .password(회원_패스워드)
                .build();
    }

    @Test
    void 회원가입() {

        // when
        ExtractableResponse<Response> response = 회원가입_요청(회원);

        // then
        JoinResponse joinResponse = response.as(JoinResponse.class);
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(joinResponse.getId()).isNotZero();
        assertThat(joinResponse.getEmail()).isNotEmpty();
    }

    @Test
    void 중복계정_가입시_예외() {
        // given
        회원가입_됨(회원);

        // when
        ExtractableResponse<Response> response = 회원가입된_계정으로_회원가입_요청(회원);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CONFLICT.value());
    }

    @Test
    void 회원가입한경우_토큰발급() {
        // given
        회원가입_됨(회원);

        // when
        ExtractableResponse<Response> response = 로그인_요청(회원);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("token")).isNotBlank();
        assertThat(response.header("content-type")).startsWith(MediaType.APPLICATION_JSON_VALUE);
    }


    @Test
    void 로그인_필수값_누락시예외() {
        JoinRequest 누락된_회원정보 = JoinRequest.builder()
                .email(null)
                .password(null)
                .build();
        ExtractableResponse<Response> response = 회원가입_요청(누락된_회원정보);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

/*    @Test
    void 로그아웃() {
        // given
        회원가입_요청(회원);
        로그인_요청(회원);

        // when
        ExtractableResponse<Response> response = 로그아웃_요청();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
    }*/


    public static ExtractableResponse<Response> 회원가입_요청(JoinRequest joinRequest) {
        return postDocs(URL_MEMBERS, joinRequest);
    }

    private static void 회원가입_됨(JoinRequest joinRequest) {
        회원가입_요청(joinRequest);
    }

    public static ExtractableResponse<Response> 회원가입된_계정으로_회원가입_요청(JoinRequest joinRequest) {
        return 회원가입_요청(joinRequest);
    }

    public static ExtractableResponse<Response> 로그인_요청(JoinRequest joinRequest) {
        return postDocs(URL_MEMBERS + "/login", joinRequest);
    }

    public static String 토큰요청(JoinRequest joinRequest) {
        회원가입_됨(joinRequest);
        return 로그인_요청(joinRequest).jsonPath().getString("token");
    }
}

