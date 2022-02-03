package com.seunghoona.kmong.order.domain;

import com.seunghoona.kmong.common.utils.LocalDateTimeUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static com.seunghoona.kmong.member.MemberFixture.회원;
import static com.seunghoona.kmong.order.ProductFixture.블로그_상품;
import static org.assertj.core.api.Assertions.assertThat;

class OrderNoGeneratorTest {

    @Test
    void 주문번호_생성확인() {
        // given
        OrderNoGenerator orderNoGenerator = new OrderNoGenerator();
        Orders orders = Orders.create(블로그_상품(), 회원());

        Serializable orderNo = orderNoGenerator.generate(null, orders);
        final String expectedOrderNo = String.valueOf(orderNo);
        assertThat(orderNo).isNotNull();
        assertThat(expectedOrderNo).startsWith(LocalDateTimeUtils.nowYear());
        assertThat(expectedOrderNo).contains("KM");
    }

    @Test
    void 데이터_객체가_Null이면_예외() {
        // given
        OrderNoGenerator orderNoGenerator = new OrderNoGenerator();
        Assertions.assertThatThrownBy(() -> {
            orderNoGenerator.generate(null, null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Order_아닌_다른_객체인경우_예외() {
        // given
        OrderNoGenerator orderNoGenerator = new OrderNoGenerator();
        Assertions.assertThatThrownBy(() -> {
            orderNoGenerator.generate(null, new Product());
        }).isInstanceOf(ClassCastException.class);
    }
}