package com.seunghoona.kmong.order.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    public static final String 블로그_상품 = "블로그_상품";
    public static final int 블로그_상품금액 = 1000;

    public static final String 엑셀강의_상품 = "엑셀강의상품";
    public static final int 엑셀강의_상품금액 = 2000;

    @Test
    void 상품생성() {
        // given
        Product 블로그배포상품 = Product.create(블로그_상품, 블로그_상품금액);

        // then
        assertThat(블로그배포상품.getName()).isEqualTo(Name.of(블로그_상품));
        assertThat(블로그배포상품.getAmount()).isEqualTo(Money.of(블로그_상품금액));
    }
}