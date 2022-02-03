package com.seunghoona.kmong.order;

import com.seunghoona.kmong.order.domain.Money;
import com.seunghoona.kmong.order.domain.Name;
import com.seunghoona.kmong.order.domain.Product;

public class ProductFixture {

    public static final String 블로그_상품 = "블로그_상품";
    public static final int 블로그_상품금액 = 1000;

    public static final String 엑셀강의_상품 = "엑셀강의상품";
    public static final int 엑셀강의_상품금액 = 2000;

    public static Product 블로그_상품() {
        return Product.builder()
                .id(1L)
                .name(Name.of(블로그_상품))
                .amount(Money.of(블로그_상품금액))
                .build();
    }

    public static Product 엑셀강의_상품() {
        return Product.builder()
                .id(2L)
                .name(Name.of(엑셀강의_상품))
                .amount(Money.of(엑셀강의_상품금액))
                .build();
    }
}
