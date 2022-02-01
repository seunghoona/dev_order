package com.seunghoona.kmong.order.acceptance;


import com.seunghoona.kmong.AcceptanceTest;
import com.seunghoona.kmong.order.dto.CreateProduct;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.seunghoona.kmong.order.domain.ProductTest.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품 기능")
public class ProductAcceptance extends AcceptanceTest {

    public static final String URL_PRODUCTS = "products";
    private CreateProduct 블로그상품;
    private CreateProduct 엑셀상품;

    @Test
    void 상품_생성한다() {
        블로그상품 = CreateProduct.builder()
                .name(블로그_상품)
                .amount(블로그_상품금액)
                .build();

        엑셀상품 = CreateProduct.builder()
                .name(엑셀강의_상품)
                .amount(엑셀강의_상품금액)
                .build();

        // when
        ExtractableResponse<Response> response = 상품_생성요청(블로그상품);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 상품_조회한다() {
        블로그상품 = CreateProduct.builder()
                .name(블로그_상품)
                .amount(블로그_상품금액)
                .build();

        엑셀상품 = CreateProduct.builder()
                .name(엑셀강의_상품)
                .amount(엑셀강의_상품금액)
                .build();

        // given
        상품_생성요청(블로그상품);
        상품_생성요청(엑셀상품);

        // when
        ExtractableResponse<Response> response = 상품_전체_조회요청();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public ExtractableResponse<Response> 상품_생성요청(CreateProduct createProduct) {
        return post(URL_PRODUCTS, createProduct, "products", spec);
    }

    public ExtractableResponse<Response> 상품_전체_조회요청() {
        return get(URL_PRODUCTS, "products", spec);
    }
}
