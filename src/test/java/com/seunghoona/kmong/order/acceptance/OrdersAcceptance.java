package com.seunghoona.kmong.order.acceptance;

import com.seunghoona.kmong.AcceptanceTest;
import com.seunghoona.kmong.member.aceeptance.MemberAcceptanceTest;
import com.seunghoona.kmong.order.dto.OrdersRequest;
import com.seunghoona.kmong.order.dto.ProductRequest;
import com.seunghoona.kmong.order.dto.ProductResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.web.client.HttpClientErrorException;

import static com.seunghoona.kmong.order.ProductFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;

@DisplayName("주문 기능")
public class OrdersAcceptance extends AcceptanceTest {

    public static final String URL_ORDERS = "/orders";
    private String 토큰;
    private OrdersRequest 블로그_주문;
    private OrdersRequest 엑셀강의_주문;
    private OrdersRequest 존재하지_않는_상품_주문;

    private ProductResponse 블로그상품;
    private ProductResponse 엑셀강의상품;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        super.setUp(restDocumentation);
        // 토큰 생성
        토큰 = MemberAcceptanceTest.토큰생성();

        // 상품 생성 
        ProductRequest 블로그상품_요청 = ProductRequest.builder()
                .name(블로그_상품)
                .amount(블로그_상품금액)
                .build();
        블로그상품 = ProductAcceptance.상품_생성요청(블로그상품_요청, 토큰).as(ProductResponse.class);

        ProductRequest 엑셀상품_요청 = ProductRequest.builder()
                .name(엑셀강의_상품)
                .amount(엑셀강의_상품금액)
                .build();
        엑셀강의상품 = ProductAcceptance.상품_생성요청(엑셀상품_요청, 토큰).as(ProductResponse.class);

        // 주문 생성
        블로그_주문 = OrdersRequest.builder()
                .productId(블로그상품.getId())
                .build();
        엑셀강의_주문 = OrdersRequest.builder()
                .productId(엑셀강의상품.getId())
                .build();

        존재하지_않는_상품_주문 = OrdersRequest.builder()
                .productId(999999L)
                .build();
    }

    @Test
    void 주문하기() {
        // when
        ExtractableResponse<Response> response = postAuth(URL_ORDERS, 블로그_주문, 토큰);

        // then
        assertThat(response.statusCode()).isEqualTo(CREATED.value());
    }

    @Test
    void 주문하는_상품이_존재하는_않는경우_예외() {
        // then
        ExtractableResponse<Response> response = postAuth(URL_ORDERS, 존재하지_않는_상품_주문, 토큰);

        // when
        assertThat(response.statusCode()).isEqualTo(BAD_REQUEST.value());
    }

    @Test
    void 내가주문한_전체_내역조회() {

        // given
        postAuth(URL_ORDERS, 블로그_주문, 토큰);
        postAuth(URL_ORDERS, 엑셀강의_주문, 토큰);

        // when
        ExtractableResponse<Response> response = getAuth(URL_ORDERS, 토큰);

        // then
        assertThat(response.statusCode()).isEqualTo(OK.value());
    }
}
