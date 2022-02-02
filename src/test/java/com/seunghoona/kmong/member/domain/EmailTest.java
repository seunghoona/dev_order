package com.seunghoona.kmong.member.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailTest {


    @Test
    void 이메일_검증() {
        // given
        Email email = new Email("test@gmail.com");

        // then
        assertThat(email).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcd", "123", "as127938@", "@", "!@#","test@gmail"})
    void 이메일_검증_예외(String email) {
        assertThatThrownBy(() -> {
            new Email(email);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}