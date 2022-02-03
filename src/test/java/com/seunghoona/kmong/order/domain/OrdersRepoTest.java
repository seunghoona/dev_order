package com.seunghoona.kmong.order.domain;

import com.seunghoona.kmong.member.domain.Member;
import com.seunghoona.kmong.member.domain.MemberRepo;
import com.seunghoona.kmong.order.ProductFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.seunghoona.kmong.member.MemberFixture.회원;
import static com.seunghoona.kmong.order.ProductFixture.*;

@SpringBootTest
class OrdersRepoTest {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Test
    void 주문생성() {
        // given
        Product savedProduct = productRepo.save(블로그_상품());
        Member savedMember = memberRepo.save(회원());

        // when
        Orders orderNo = ordersRepo.save(Orders.create(savedProduct, savedMember));

        // then
        Assertions.assertThat(orderNo.getOrderNo()).isEqualTo("2022010100000000010000000001");
    }
}