package com.seunghoona.kmong.common.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LocalDateTimeUtilsTest {


    @Test
    void 현재시간_기존포맷으로_생성() {

        // given
        String nowDateFormat = LocalDateTimeUtils.nowBasicFormat();

        // then
        assertAll(() -> {
            assertThat(nowDateFormat).isNotBlank();
            assertThat(nowDateFormat.length()).isEqualTo(8);
        });
    }
}