package com.seunghoona.kmong.order.acceptance;


import com.seunghoona.kmong.AcceptanceTest;
import com.seunghoona.kmong.member.aceeptance.MemberAcceptanceTest;
import com.seunghoona.kmong.member.domain.Member;
import com.seunghoona.kmong.order.dto.ProductRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.RestDocumentationContextProvider;

import static com.seunghoona.kmong.order.domain.ProductTest.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품 기능")
public class ProductAcceptance extends AcceptanceTest {

    public static final String URL_PRODUCTS = "/products";
    private ProductRequest 블로그상품;
    private ProductRequest 엑셀상품;
    private String 토큰생성;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        super.setUp(restDocumentation);
        블로그상품 = ProductRequest.builder()
                .name(블로그_상품)
                .amount(블로그_상품금액)
                .build();

        엑셀상품 = ProductRequest.builder()
                .name(엑셀강의_상품)
                .amount(엑셀강의_상품금액)
                .build();

        // 토큰 생성
        토큰생성 = MemberAcceptanceTest.토큰생성();
    }

    @Test
    void 상품_생성한다() {

        // when
        ExtractableResponse<Response> response = 상품_생성요청(블로그상품);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 상품_조회한다() {
        상품_생성요청(블로그상품);
        상품_생성요청(엑셀상품);

        // when
        ExtractableResponse<Response> response = 상품_전체_조회요청();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public ExtractableResponse<Response> 상품_생성요청(ProductRequest createProduct) {
        return postAuth(URL_PRODUCTS, createProduct, 토큰생성);
    }

    public ExtractableResponse<Response> 상품_전체_조회요청() {
        return getAuth(URL_PRODUCTS, 토큰생성);
    }
}
