package com.seunghoona.kmong.member.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PasswordTest {

    @Test
    void 패워워드_사이즈_범위인_경우_정상() {
        // given
        Password password = new Password("password");

        // then
        assertThat(password).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcde", "abcdefghijklnmopqr"})
    void 패스워드_사이즈_크기보다_크거나_작으면_예외(String enterPassword) {
        assertThatThrownBy(() -> {
            new Password(enterPassword);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}