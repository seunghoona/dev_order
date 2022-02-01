package com.seunghoona.kmong.order.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @Test
    void 상품정상등록() {
        // given
        final String 블로그배포 = "블로그배포";

        // then
        assertThat(Name.of(블로그배포)).isEqualTo(Name.of(블로그배포));
    }


    @NullAndEmptySource
    @ParameterizedTest
    void 상품명이_없거나_Null이면_예외(String name) {
        assertThatThrownBy(() -> {
            Name.of(name);
        }).isInstanceOf(IllegalArgumentException.class);
    }


}