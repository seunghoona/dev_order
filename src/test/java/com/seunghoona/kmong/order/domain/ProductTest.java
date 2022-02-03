package com.seunghoona.kmong.order.domain;

import com.seunghoona.kmong.order.ProductFixture;
import org.junit.jupiter.api.Test;

import static com.seunghoona.kmong.order.ProductFixture.블로그_상품;
import static com.seunghoona.kmong.order.ProductFixture.블로그_상품금액;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    void 상품생성() {
        // given
        Product 블로그배포상품 = 블로그_상품();

        // then
        assertThat(블로그배포상품.getName()).isEqualTo(Name.of(블로그_상품));
        assertThat(블로그배포상품.getAmount()).isEqualTo(Money.of(블로그_상품금액));
    }
}