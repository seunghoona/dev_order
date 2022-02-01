package com.seunghoona.kmong.order.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {

    @Test
    void 상품금액_생성() {
        // given
        Money amount = Money.of(1000);

        // then
        assertThat(amount).isEqualTo(Money.of(1000));
        assertThat(amount).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(longs = {-1, 0})
    void 상품의_금액은_0보다_작을수_없다(long amount) {
        // then
        assertThatThrownBy(() -> {
            Money.of(amount);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}